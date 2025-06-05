package com.daniyal.sto.service.impl;

import com.daniyal.sto.model.RepairRequestStatus;
import com.daniyal.sto.model.StatusHistory;
import com.daniyal.sto.repository.RepairRequestRepository;
import com.daniyal.sto.repository.StatusHistoryRepository;
import com.daniyal.sto.service.StatusHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class StatusHistoryServiceImpl implements StatusHistoryService {
    private final StatusHistoryRepository statusHistoryRepository;
    private final RepairRequestRepository repairRequestRepository;

    @Override
    public void addStatusHistory(Long requestId, RepairRequestStatus oldStatus, RepairRequestStatus newStatus, String changedBy, String reason) {
        var repairRequest = repairRequestRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Request with id: " + requestId + " not found"));

        var history = new StatusHistory();
        history.setRequest(repairRequest);
        history.setOldStatus(oldStatus);
        history.setNewStatus(newStatus);
        history.setChangedAt(LocalDateTime.now());
        history.setChangedBy(changedBy);
        history.setChangeReason(reason);
        statusHistoryRepository.save(history);
    }
}
