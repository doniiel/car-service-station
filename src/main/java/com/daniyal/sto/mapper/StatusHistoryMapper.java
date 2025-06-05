package com.daniyal.sto.mapper;

import com.daniyal.sto.dto.StatusHistoryDto;
import com.daniyal.sto.model.StatusHistory;
import org.springframework.stereotype.Component;

@Component
public interface StatusHistoryMapper {
    StatusHistory toEntity(StatusHistoryDto dto);
    StatusHistoryDto toDto(StatusHistory entity);
}
