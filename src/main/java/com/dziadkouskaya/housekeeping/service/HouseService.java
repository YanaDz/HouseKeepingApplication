package com.dziadkouskaya.housekeeping.service;

import com.dziadkouskaya.housekeeping.entity.House;

import java.util.List;
import java.util.Optional;

public interface HouseService {
    House persistHouse(House house);

    Optional<House> getById(Long id);

    Optional<House> getByNameOrAddress(String... request);

    List<House> getAll();
}
