package com.dziadkouskaya.housekeeping.facade;

import com.dziadkouskaya.housekeeping.entity.House;
import com.dziadkouskaya.housekeeping.entity.dto.HouseDto;
import com.dziadkouskaya.housekeeping.entity.dto.HouseDtoRequest;
import com.dziadkouskaya.housekeeping.entity.filters.SearchRequest;
import org.springframework.data.domain.Page;

import java.util.List;

public interface HouseFacade {
    HouseDto createHouse(HouseDtoRequest dto);

    List<HouseDto> getAll();

    HouseDto getById(Long id);

    Page<House> getByNameOrAddress(SearchRequest request);
}
