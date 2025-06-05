package com.daniyal.sto.mapper;

import com.daniyal.sto.dto.StatusHistoryDto;
import com.daniyal.sto.model.StatusHistory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface StatusHistoryMapper {
    @Mapping(target = "request", ignore = true)
    StatusHistory toEntity(StatusHistoryDto dto);

    @Mapping(target = "requestId", source = "request.id")
    StatusHistoryDto toDto(StatusHistory entity);
}
