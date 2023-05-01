package com.dziadkouskaya.housekeeping.service.impl;

import com.dziadkouskaya.housekeeping.entity.House;
import com.dziadkouskaya.housekeeping.exception.EntityExistedExeption;
import com.dziadkouskaya.housekeeping.repository.HouseRepo;
import com.dziadkouskaya.housekeeping.service.HouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.dziadkouskaya.housekeeping.utils.Constants.HOUSE_EXISTED;

@Service
@RequiredArgsConstructor
public class HouseServiceImpl implements HouseService {
    private final HouseRepo houseRepo;

    @Override
    public House persistHouse(House house) {
        var existedHouse = getByNameOrAddress(house.getAddress(), house.getName());
        if (existedHouse.isPresent()) {
            throw new EntityExistedExeption(String.format(HOUSE_EXISTED, house.getName(), house.getAddress()));
        }
        return houseRepo.save(house);
    }

    @Override
    public Optional<House> getById(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<House> getByNameOrAddress(String... request) {
        return Optional.empty();
    }

    @Override
    public List<House> getAll() {
        return houseRepo.findAll();
    }
}
