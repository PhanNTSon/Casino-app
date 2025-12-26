package io.phanson.casino_backend.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import io.phanson.casino_backend.dto.response.SystemLogViewResponse;
import io.phanson.casino_backend.entity.SystemLog;

@Component
@Mapper(componentModel = "spring")
public interface SystemLogsMapper {

    public SystemLogViewResponse toSystemLogViewResponse(SystemLog systemLog);
}
