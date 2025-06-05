package com.daniyal.sto.service.impl;

import com.daniyal.sto.dto.ChangeStatusRequest;
import com.daniyal.sto.dto.CreateRepairRequest;
import com.daniyal.sto.dto.RepairRequestDto;
import com.daniyal.sto.dto.StatusHistoryDto;
import com.daniyal.sto.event.StatusChangeEvent;
import com.daniyal.sto.mapper.RepairRequestMapper;
import com.daniyal.sto.mapper.StatusHistoryMapper;
import com.daniyal.sto.model.RepairRequestStatus;
import com.daniyal.sto.repository.RepairRequestRepository;
import com.daniyal.sto.repository.StatusHistoryRepository;
import com.daniyal.sto.service.NotificationService;
import com.daniyal.sto.service.RepairRequestService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RepairRequestServiceImpl implements RepairRequestService {
    private final RepairRequestRepository requestRepository;
    private final StatusHistoryRepository statusHistoryRepository;
    private final NotificationService notificationService;
    private final RepairRequestMapper requestMapper;
    private final StatusHistoryMapper statusMapper;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Value("${topic.status}")
    private String statusTopic;

    @Transactional
    public RepairRequestDto createRequest(CreateRepairRequest req) {
        var request = requestMapper.toEntity(req);
        request.setStatus(RepairRequestStatus.CREATED);
        request.setCreatedAt(LocalDateTime.now());

        var savedRequest = requestRepository.save(request);

        var event = StatusChangeEvent.fromRequest(savedRequest.getId(), null, RepairRequestStatus.CREATED, "System", "Request created");
        kafkaTemplate.send(statusTopic, event);

        return requestMapper.toDto(savedRequest);
    }

    @Transactional
    public RepairRequestDto changeStatus(Long requestId, ChangeStatusRequest changeRequest) {
        var request = requestRepository.findById(requestId)
                .orElseThrow(() -> new EntityNotFoundException("Request with id: " + requestId + " not found"));

        var oldStatus = request.getStatus();
        var newStatus = changeRequest.getNewStatus();

        validateStatusTransition(oldStatus, newStatus);

        request.setStatus(newStatus);
        request.setUpdatedAt(LocalDateTime.now());

        var statusEvent = StatusChangeEvent.fromRequest(requestId, oldStatus, newStatus, changeRequest.getChangedBy(), changeRequest.getChangeReason());
        kafkaTemplate.send(statusTopic, statusEvent);

        if (newStatus == RepairRequestStatus.CLIENT_NOTIFIED) {
            notificationService.notifyClient(request);
        }

        return requestMapper.toDto(requestRepository.save(request));
    }

    @Transactional
    public Page<RepairRequestDto> getRequestsByClient(String clientPhone, Pageable pageable) {
        var requestsPage = requestRepository.findByClientPhone(clientPhone, pageable);
        return requestsPage.map(requestMapper::toDto);
    }

    @Transactional
    public Page<RepairRequestDto> getRequestsByStatus(RepairRequestStatus status, Pageable pageable) {
        var requestsPage = requestRepository.findAllByStatus(status, pageable);
        return requestsPage.map(requestMapper::toDto);
    }

    @Transactional
    public List<StatusHistoryDto> getStatusHistory(Long requestId) {
        var statusList = statusHistoryRepository.findAllByRequestId(requestId);
        return statusList.stream()
                .map(statusMapper::toDto)
                .toList();
    }

    private void validateStatusTransition(RepairRequestStatus oldStatus, RepairRequestStatus newStatus) {
        // Реализовать логику валидации переходов между статусами
    }
}
