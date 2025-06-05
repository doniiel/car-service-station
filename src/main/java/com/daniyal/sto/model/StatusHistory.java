package com.daniyal.sto.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "status_history")
public class StatusHistory {

    @Id
    @SequenceGenerator(
            name = "status_history_seq_gen",
            sequenceName = "status_history_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "status_history_seq_gen"
    )
    private Long id;

    @ManyToOne
    @JoinColumn(name = "request_id", nullable = false)
    private RepairRequest request;

    @Enumerated(EnumType.STRING)
    private RepairRequestStatus oldStatus;

    @Enumerated(EnumType.STRING)
    private RepairRequestStatus newStatus;

    private LocalDateTime changedAt;
    private String changedBy;
    private String changeReason;

}

