package com.dziadkouskaya.housekeeping.service.impl;

import com.dziadkouskaya.housekeeping.entity.House;
import com.dziadkouskaya.housekeeping.exception.EntityExistedExeption;
import com.dziadkouskaya.housekeeping.repository.HouseRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.jpa.domain.Specification;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class HouseServiceTest {
    @Mock
    private HouseRepo houseRepo;

    @InjectMocks
    private HouseServiceImpl houseService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void persistHousePositive() {
        var house = House.builder()
            .name("house1")
            .address("house address 1")
            .entranceNumber(10)
            .build();
        var savedHouse = House.builder()
            .id(1L)
            .name("house1")
            .address("house address 1")
            .entranceNumber(10)
            .build();
        when(houseRepo.save(any())).thenReturn(savedHouse);
        var result = houseService.persistHouse(house);
        assertEquals(savedHouse.getId(), result.getId());
        assertEquals(house.getName(), result.getName());
        assertEquals(house.getAddress(), result.getAddress());
        assertEquals(house.getEntranceNumber(), result.getEntranceNumber());
    }

    @Test
    void testPersistHouseNegative() {
        var house = House.builder()
            .name("house1")
            .address("house address 1")
            .build();
        var existedHouse = House.builder()
            .id(1L)
            .name("house1")
            .address("house address 1")
            .build();
        when(houseRepo.findOne(any(Specification.class))).thenReturn(Optional.of(existedHouse));
        assertThrows(EntityExistedExeption.class, () -> houseService.persistHouse(house));
    }


}