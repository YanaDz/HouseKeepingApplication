package com.dziadkouskaya.housekeeping.service.impl;

import com.dziadkouskaya.housekeeping.entity.House;
import com.dziadkouskaya.housekeeping.exception.EntityExistedExeption;
import com.dziadkouskaya.housekeeping.repository.HouseRepo;
import com.dziadkouskaya.housekeeping.service.HouseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.dziadkouskaya.housekeeping.repository.specification.HouseSpecification.fullSearch;
import static com.dziadkouskaya.housekeeping.repository.specification.HouseSpecification.search;
import static com.dziadkouskaya.housekeeping.utils.Constants.HOUSE_EXISTED;

@Slf4j
@Service
@RequiredArgsConstructor
public class HouseServiceImpl implements HouseService {
    private final HouseRepo houseRepo;

    @Override
    public House persistHouse(House house) {
        var existedHouse = getByNameOrAddress(house.getAddress());
        if (existedHouse.isPresent()) {
            throw new EntityExistedExeption(String.format(HOUSE_EXISTED, house.getName(), house.getAddress()));
        }
        return houseRepo.save(house);
    }

    @Override
    public House getById(Long id) {
        return houseRepo.getReferenceById(id);
    }

    @Override
    public Optional<House> getByNameOrAddress(String request) {
        return houseRepo.findOne(search(request));
    }

    @Override
    public List<House> getAll() {
        return houseRepo.findAll();
    }
}
