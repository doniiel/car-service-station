package com.daniyal.sto.mapper;

import com.daniyal.sto.dto.CreateRepairRequest;
import com.daniyal.sto.dto.RepairRequestDto;
import com.daniyal.sto.model.RepairRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface RepairRequestMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "status", constant = "CREATED")
    @Mapping(target = "statusHistory", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    RepairRequest toEntity(CreateRepairRequest req);

    RepairRequestDto toDto(RepairRequest entity);
}
