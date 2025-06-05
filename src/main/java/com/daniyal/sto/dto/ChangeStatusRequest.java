package com.daniyal.sto.dto;

import com.daniyal.sto.model.RepairRequestStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChangeStatusRequest {

    @NotNull(message = "New status is required")
    @Schema(description = "New status of the repair request", example = "IN_PROCESSING")
    private RepairRequestStatus newStatus;

    @NotBlank(message = "Changed by is required")
    @Schema(description = "Who changed the status", example = "mechanic123")
    private String changedBy;

    @NotBlank(message = "Change reason is required")
    @Schema(description = "Reason for status change", example = "Diagnostics completed")
    private String changeReason;

}
