package com.daniyal.sto.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Schema(description = "Status change history record")
public class StatusHistoryDto {
    @Schema(description = "History record ID", example = "1")
    private Long id;

    @Schema(description = "Repair request ID", example = "5")
    private Long requestId;

    @Schema(description = "Previous status", example = "CREATED")
    private String oldStatus;

    @Schema(description = "New status", example = "IN_PROCESSING")
    private String newStatus;

    @Schema(description = "When the change occurred", example = "2023-12-01T11:30:45")
    private LocalDateTime changedAt;

    @Schema(description = "Who changed the status", example = "mechanic123")
    private String changedBy;

    @Schema(description = "Reason for change", example = "Diagnostics started")
    private String changeReason;
}