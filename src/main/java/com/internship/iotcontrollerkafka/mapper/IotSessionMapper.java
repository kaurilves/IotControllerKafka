package com.internship.iotcontrollerkafka.mapper;


import com.internship.iotcontrollerkafka.dto.DeviceConnectionResponse;
import com.internship.iotcontrollerkafka.entity.IotSession;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;


@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.WARN)
public interface IotSessionMapper extends EntityMapper<IotSession, DeviceConnectionResponse> {

}