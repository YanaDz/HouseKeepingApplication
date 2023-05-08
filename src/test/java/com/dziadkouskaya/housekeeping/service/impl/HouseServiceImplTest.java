package com.dziadkouskaya.housekeeping.service.impl;

import com.dziadkouskaya.housekeeping.entity.House;
import com.dziadkouskaya.housekeeping.exception.EntityExistedExeption;
import com.dziadkouskaya.housekeeping.repository.HouseRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.jpa.domain.Specification;

import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.params.provider.Arguments.arguments;
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

    static Stream<Arguments> argsForGetByNameAddress() {
        return Stream.of(
            arguments("sweet home", "Sweet Home on Abbey Road"),
            arguments("", null),
            arguments("null", null),
            arguments("    ", null)
        );
    }

    @ParameterizedTest
    @MethodSource("argsForGetByNameAddress")
    void testGetByNameOrAddress(String request, String expectedName) {
        var existedHouseName =
            switch (request) {
                case "" -> Optional.empty();
                case "    " -> Optional.empty();
                case "null" -> Optional.empty();
                default -> Optional.of(House.builder().id(1L).name(expectedName).address("any").build());
            };
        when(houseRepo.findOne(any(Specification.class))).thenReturn(existedHouseName);
        var result = houseService.getByNameOrAddress(request);
        if (request.equals("null") || request.isBlank() || request.isEmpty()) {
            assertEquals(Optional.empty(), result);
        } else {
            assertEquals(expectedName, result.get().getName());
        }
    }

}