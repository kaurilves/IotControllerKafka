package com.internship.iotcontrollerkafka.mapper;


import com.internship.iotcontrollerkafka.dto.AssetDto;
import com.internship.iotcontrollerkafka.mapper.entity.Asset;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;


@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.WARN)
public interface AssetMapper extends EntityMapper<Asset, AssetDto> {

}