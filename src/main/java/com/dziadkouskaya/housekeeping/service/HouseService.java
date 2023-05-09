package com.dziadkouskaya.housekeeping.service;

import com.dziadkouskaya.housekeeping.entity.House;
import com.dziadkouskaya.housekeeping.entity.filters.SearchRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface HouseService {
    House persistHouse(House house);

    Optional<House> getById(Long id);

    Page<House> getByNameOrAddress(SearchRequest request);

    List<House> getByName(String name);

    List<House> getAll();
}
