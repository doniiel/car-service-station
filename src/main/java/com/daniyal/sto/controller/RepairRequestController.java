package com.daniyal.sto.controller;

import com.daniyal.sto.dto.*;
import com.daniyal.sto.model.RepairRequestStatus;
import com.daniyal.sto.service.RepairRequestService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/repair-requests")
@RequiredArgsConstructor
@Tag(name = "Repair Requests", description = "Manage car repair requests")
public class RepairRequestController {
    private final RepairRequestService requestService;


    @Operation(summary = "Create a new repair request",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Request created successfully"),
                    @ApiResponse(responseCode = "400", description = "Invalid input data")
            })
    @PostMapping
    public ResponseEntity<RepairRequestDto> createRequest(@Valid @RequestBody CreateRepairRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(requestService.createRequest(request));
    }

    @Operation(summary = "Change request status",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Status changed successfully"),
                    @ApiResponse(responseCode = "400", description = "Invalid status transition"),
                    @ApiResponse(responseCode = "404", description = "Request not found")
            })
    @PatchMapping("/{id}/status")
    public ResponseEntity<RepairRequestDto> changeStatus(
            @Parameter(description = "ID of the repair request", example = "1")
            @PathVariable Long id,
            @Valid @RequestBody ChangeStatusRequest request) {
        return ResponseEntity.ok(requestService.changeStatus(id, request));
    }

    @Operation(summary = "Get requests by client phone",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Requests found"),
                    @ApiResponse(responseCode = "400", description = "Invalid phone format")
            })
    @GetMapping("/by-client")
    public ResponseEntity<Page<RepairRequestDto>> getByClient(
            @Parameter(description = "Client's phone number", example = "87053725698")
            @RequestParam String phone,
            @Parameter(description = "Page number (starts from 0)", example = "0")
            @RequestParam(defaultValue = "0") int page,

            @Parameter(description = "Page size", example = "10")
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(requestService.getRequestsByClient(phone, pageable));
    }


    @Operation(summary = "Get requests by status",
            responses = @ApiResponse(responseCode = "200", description = "Requests found"))
    @GetMapping("/by-status")
    public ResponseEntity<Page<RepairRequestDto>> getByStatus(
            @Parameter(description = "Status to filter by", example = "CREATED")
            @RequestParam RepairRequestStatus status,

            @Parameter(description = "Page number (starts from 0)", example = "0")
            @RequestParam(defaultValue = "0") int page,

            @Parameter(description = "Page size", example = "10")
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(requestService.getRequestsByStatus(status, pageable));
    }


    @Operation(summary = "Get status history for a request",
            responses = {
                    @ApiResponse(responseCode = "200", description = "History retrieved"),
                    @ApiResponse(responseCode = "404", description = "Request not found")
            })
    @GetMapping("/{id}/history")
    public ResponseEntity<List<StatusHistoryDto>> getStatusHistory(
            @Parameter(description = "ID of the repair request", example = "1")
            @PathVariable Long id) {
        return ResponseEntity.ok(requestService.getStatusHistory(id));
    }
}