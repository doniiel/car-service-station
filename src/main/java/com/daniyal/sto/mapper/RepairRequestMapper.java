package com.daniyal.sto.mapper;

import com.daniyal.sto.dto.CreateRepairRequest;
import com.daniyal.sto.dto.RepairRequestDto;
import com.daniyal.sto.model.RepairRequest;
import org.springframework.stereotype.Component;

@Component
public interface RepairRequestMapper {
    RepairRequest toEntity(CreateRepairRequest req);
    RepairRequestDto toDto(RepairRequest entity);

}
