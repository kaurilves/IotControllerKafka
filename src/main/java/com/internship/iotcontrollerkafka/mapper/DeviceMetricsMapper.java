package com.internship.iotcontrollerkafka.mapper;

import com.internship.iotcontrollerkafka.dto.DeviceMetricsDto;
import com.internship.iotcontrollerkafka.entity.DeviceMetrics;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.WARN)
public interface DeviceMetricsMapper extends EntityMapper<DeviceMetrics, DeviceMetricsDto> {

}