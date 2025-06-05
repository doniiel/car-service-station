package com.daniyal.sto.event;

import com.daniyal.sto.model.RepairRequestStatus;

public record StatusChangeEvent(
    Long requestId,
    RepairRequestStatus oldStatus,
    RepairRequestStatus newStatus,
    String changeBy,
    String changeReason
) {
    public static StatusChangeEvent fromRequest(Long requestId, RepairRequestStatus oldStatus, RepairRequestStatus newStatus, String changeBy, String changeReason) {
        return new StatusChangeEvent(
                requestId,
                oldStatus,
                newStatus,
                changeBy,
                changeReason
        );
    }
}
