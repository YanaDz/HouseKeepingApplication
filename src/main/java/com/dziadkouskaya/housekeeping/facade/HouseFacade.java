package com.dziadkouskaya.housekeeping.facade;

import com.dziadkouskaya.housekeeping.entity.dto.CreatedHouseDto;
import com.dziadkouskaya.housekeeping.entity.dto.HouseDtoRequest;

public interface HouseFacade {
    CreatedHouseDto createHouse(HouseDtoRequest dto);
}
