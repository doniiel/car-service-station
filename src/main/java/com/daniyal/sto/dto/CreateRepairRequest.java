package com.daniyal.sto.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CreateRepairRequest {

    @NotBlank(message = "Client name is required")
    @Size(max = 100, message = "Client name must be less than 100 characters")
    @Schema(description = "Full name of the client", example = "John Doe")
    private String clientName;

    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^\\+?[0-9\\-\\s]+$", message = "Invalid phone number format")
    @Schema(description = "Client's phone number", example = "+1234567890")
    private String clientPhone;

    @NotBlank(message = "Car model is required")
    @Size(max = 50, message = "Car model must be less than 50 characters")
    @Schema(description = "Model of the car", example = "Toyota Camry")
    private String carModel;

    @NotBlank(message = "Car number is required")
    @Size(max = 20, message = "Car number must be less than 20 characters")
    @Schema(description = "License plate number", example = "ABC123")
    private String carNumber;

    @Size(max = 500, message = "Description must be less than 500 characters")
    @Schema(description = "Description of the repair needed", example = "Oil change and filter replacement")
    private String description;

}
