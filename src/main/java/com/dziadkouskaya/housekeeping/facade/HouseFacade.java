package com.dziadkouskaya.housekeeping.facade;

import com.dziadkouskaya.housekeeping.entity.dto.HouseDto;
import com.dziadkouskaya.housekeeping.entity.dto.HouseDtoRequest;

import java.util.List;

public interface HouseFacade {
    HouseDto createHouse(HouseDtoRequest dto);

    List<HouseDto> getAll();
}
