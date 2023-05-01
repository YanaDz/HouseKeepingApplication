package com.dziadkouskaya.housekeeping.facade.impl;

import com.dziadkouskaya.housekeeping.entity.dto.HouseDto;
import com.dziadkouskaya.housekeeping.entity.dto.HouseDtoRequest;
import com.dziadkouskaya.housekeeping.facade.HouseFacade;
import com.dziadkouskaya.housekeeping.mapper.HouseMapper;
import com.dziadkouskaya.housekeeping.service.HouseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
@Transactional
@RequiredArgsConstructor
public class HouseFacadeImpl implements HouseFacade {
    private final HouseService houseService;
    protected final HouseMapper houseMapper;

    @Override
    public HouseDto createHouse(HouseDtoRequest dto) {
        var entity = houseService.persistHouse(houseMapper.toEntity(dto));
        return houseMapper.toDto(entity);
    }

    @Override
    public List<HouseDto> getAll() {
        return houseService.getAll().stream()
            .map(houseMapper::toDto)
            .collect(Collectors.toList());
    }
}
