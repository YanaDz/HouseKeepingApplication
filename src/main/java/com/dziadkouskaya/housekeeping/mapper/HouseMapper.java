package com.dziadkouskaya.housekeeping.mapper;

import com.dziadkouskaya.housekeeping.entity.House;
import com.dziadkouskaya.housekeeping.entity.dto.HouseDto;
import com.dziadkouskaya.housekeeping.entity.dto.HouseDtoRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface HouseMapper {
    @Mapping(target = "name", source = "name")
    @Mapping(target = "address", source = "address")
    @Mapping(target = "entranceNumber", source = "entranceNumber")
    House toEntity(HouseDtoRequest dto);

    @Mapping(target = "id", source = "house.id")
    @Mapping(target = "name", source = "house.name")
    @Mapping(target = "address", source = "house.address")
    @Mapping(target = "entranceNumber", source = "house.entranceNumber")
    HouseDto toDto(House house);
}
