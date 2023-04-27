package com.dziadkouskaya.housekeeping.facade.impl;

import com.dziadkouskaya.housekeeping.entity.House;
import com.dziadkouskaya.housekeeping.entity.dto.CreatedHouseDto;
import com.dziadkouskaya.housekeeping.entity.dto.HouseDtoRequest;
import com.dziadkouskaya.housekeeping.facade.HouseFacade;
import com.dziadkouskaya.housekeeping.mapper.HouseMapper;
import com.dziadkouskaya.housekeeping.service.HouseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
@Transactional
@RequiredArgsConstructor
public class HouseFacadeImpl implements HouseFacade {
    protected final HouseService houseService;
    protected final HouseMapper houseMapper;

    @Override
    public CreatedHouseDto createHouse(HouseDtoRequest dto) {
        var entity = houseService.persistHouse(houseMapper.toEntity(dto));
        return houseMapper.toDto(entity);
    }
}
