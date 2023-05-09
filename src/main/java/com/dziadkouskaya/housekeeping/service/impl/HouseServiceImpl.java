package com.dziadkouskaya.housekeeping.service.impl;

import com.dziadkouskaya.housekeeping.entity.House;
import com.dziadkouskaya.housekeeping.entity.filters.SearchRequest;
import com.dziadkouskaya.housekeeping.exception.EntityExistedExeption;
import com.dziadkouskaya.housekeeping.repository.HouseRepo;
import com.dziadkouskaya.housekeeping.service.HouseService;
import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.dziadkouskaya.housekeeping.entity.enumerations.SearchType.COMPLETE_MATCH;
import static com.dziadkouskaya.housekeeping.repository.specification.HouseSpecification.search;
import static com.dziadkouskaya.housekeeping.repository.specification.HouseSpecification.searchByName;
import static com.dziadkouskaya.housekeeping.utils.Constants.HOUSE_EXISTED;

@Slf4j
@Service
@RequiredArgsConstructor
public class HouseServiceImpl implements HouseService {
    private final HouseRepo houseRepo;

    @Override
    public House persistHouse(House house) {
        var existedHouse = getByName(house.getName());
        if (!existedHouse.isEmpty()) {
            throw new EntityExistedExeption(String.format(HOUSE_EXISTED, house.getName(), house.getAddress()));
        }
        return houseRepo.save(house);
    }

    @Override
    public Optional<House> getById(Long id) {
        return houseRepo.findById(id);
    }

    @Override
    public Page<House> getByNameOrAddress(SearchRequest request) {
        return houseRepo.findAll(search(request.getSearch()), request.getPageRequest());
    }

    @Override
    public List<House> getByName(String name) {
        return houseRepo.findAll(searchByName(name, COMPLETE_MATCH));
    }

    @Override
    public List<House> getAll() {
        return Lists.newArrayList(houseRepo.findAll());
    }

}
