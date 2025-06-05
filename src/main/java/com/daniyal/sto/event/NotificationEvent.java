package com.daniyal.sto.event;

import com.daniyal.sto.model.RepairRequest;

import java.time.LocalDateTime;

public record NotificationEvent(
    Long requestId,
    String clientPhone,
    String clientName,
    String carModel,
    String message,
    LocalDateTime createdAt
) {
    public static NotificationEvent fromRequest(RepairRequest request, String message) {
        return new NotificationEvent(
                request.getId(),
                request.getClientPhone(),
                request.getClientName(),
                request.getCarModel(),
                message,
                LocalDateTime.now()
        );
    }
}
