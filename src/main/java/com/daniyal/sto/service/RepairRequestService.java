package com.daniyal.sto.service;

import com.daniyal.sto.dto.ChangeStatusRequest;
import com.daniyal.sto.dto.CreateRepairRequest;
import com.daniyal.sto.dto.RepairRequestDto;
import com.daniyal.sto.dto.StatusHistoryDto;
import com.daniyal.sto.model.RepairRequestStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RepairRequestService {

    RepairRequestDto createRequest(CreateRepairRequest request);

    RepairRequestDto changeStatus(Long requestId, ChangeStatusRequest request);

    Page<RepairRequestDto> getRequestsByClient(String clientPhone, Pageable pageable);

    Page<RepairRequestDto> getRequestsByStatus(RepairRequestStatus status, Pageable pageable);

    List<StatusHistoryDto> getStatusHistory(Long requestId);
}
