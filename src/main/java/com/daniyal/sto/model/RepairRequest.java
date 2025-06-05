package com.daniyal.sto.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "repair_request")
public class RepairRequest {

    @Id
    @SequenceGenerator(
            name = "repair_request_seq_gen",
            sequenceName = "repair_request_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "repair_request_seq_gen"
    )
    private Long id;
    private String clientName;
    private String clientPhone;
    private String carModel;
    private String carNumber;
    private String description;

    @Enumerated(EnumType.STRING)
    private RepairRequestStatus status;

    @OneToMany(mappedBy = "request", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<StatusHistory> statusHistory;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
