package com.daniyal.sto.dto;

import com.daniyal.sto.model.RepairRequestStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Schema(description = "Repair request details")
public class RepairRequestDto {
    @Schema(description = "Unique identifier of the repair request", example = "1")
    private Long id;

    @Schema(description = "Client's full name", example = "John Doe")
    private String clientName;

    @Schema(description = "Client's phone number", example = "+1234567890")
    private String clientPhone;

    @Schema(description = "Car model", example = "Toyota Camry")
    private String carModel;

    @Schema(description = "License plate number", example = "ABC123")
    private String carNumber;

    @Schema(description = "Repair description", example = "Oil change and filter replacement")
    private String description;

    @Schema(description = "Current status of the request", example = "CREATED")
    private RepairRequestStatus status;

    @Schema(description = "When the request was created", example = "2023-12-01T10:15:30")
    private LocalDateTime createdAt;
}
