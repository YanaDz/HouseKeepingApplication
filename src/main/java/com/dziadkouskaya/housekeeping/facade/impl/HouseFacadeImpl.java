package com.dziadkouskaya.housekeeping.facade.impl;

import com.dziadkouskaya.housekeeping.entity.House;
import com.dziadkouskaya.housekeeping.entity.dto.HouseDto;
import com.dziadkouskaya.housekeeping.entity.dto.HouseDtoRequest;
import com.dziadkouskaya.housekeeping.entity.filters.SearchRequest;
import com.dziadkouskaya.housekeeping.exception.ResourceNotFoundException;
import com.dziadkouskaya.housekeeping.facade.HouseFacade;
import com.dziadkouskaya.housekeeping.mapper.HouseMapper;
import com.dziadkouskaya.housekeeping.service.HouseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.dziadkouskaya.housekeeping.utils.Constants.HOUSE_NOT_FOUND;
import static java.util.Objects.isNull;

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

    @Override
    public HouseDto getById(Long id) {
        var house = houseService.getById(id);
        if (house.isEmpty()) {
            throw new ResourceNotFoundException(String.format(HOUSE_NOT_FOUND, id));
        }
        return houseMapper.toDto(house.get());
    }

    @Override
    public Page<House> getByNameOrAddress(SearchRequest request) {
        return houseService.getByNameOrAddress(request);
    }
}
