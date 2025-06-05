package com.daniyal.sto.service;

import com.daniyal.sto.model.RepairRequestStatus;

public interface StatusHistoryService {
    void addStatusHistory(Long requestId, RepairRequestStatus oldStatus, RepairRequestStatus newStatus, String changedBy, String reason);

}
