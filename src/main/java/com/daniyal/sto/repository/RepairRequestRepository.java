package com.daniyal.sto.repository;

import com.daniyal.sto.model.RepairRequest;
import com.daniyal.sto.model.RepairRequestStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepairRequestRepository extends JpaRepository<RepairRequest, Long> {

    Page<RepairRequest> findByClientPhone(String clientPhone, Pageable pageable);

    Page<RepairRequest> findAllByStatus(RepairRequestStatus status, Pageable pageable);
}
