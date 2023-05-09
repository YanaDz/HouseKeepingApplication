package com.dziadkouskaya.housekeeping.service.impl;

import com.dziadkouskaya.housekeeping.entity.House;
import com.dziadkouskaya.housekeeping.exception.EntityExistedExeption;
import com.dziadkouskaya.housekeeping.repository.HouseRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
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
    void testPersistHouse() {
        var house = House.builder().name("Test House").address("Test Address").build();
        var savedHouse = House.builder().id(12L).name("Test House").address("Test Address").build();
        var numberOfCallingFindAll = 1;
        var numberOfCallingSave = 1;

        when(houseRepo.findAll(any(Specification.class))).thenReturn(List.of());
        when(houseRepo.save(any(House.class))).thenReturn(savedHouse);

        var result = houseService.persistHouse(house);
        verify(houseRepo, times(numberOfCallingFindAll)).findAll(any(Specification.class));
        verify(houseRepo, times(numberOfCallingSave)).save(house);
        assertEquals(savedHouse, result);

        when(houseRepo.findAll(any(Specification.class))).thenReturn(List.of(savedHouse));
        assertThrows(EntityExistedExeption.class, () -> houseService.persistHouse(house));
        verify(houseRepo, times( ++ numberOfCallingFindAll)).findAll(any(Specification.class));
        verify(houseRepo, times(numberOfCallingSave)).save(any(House.class));

        when(houseRepo.findAll(any(Specification.class))).thenReturn(List.of(savedHouse, savedHouse));
        assertThrows(EntityExistedExeption.class, () -> houseService.persistHouse(house));
        verify(houseRepo, times(++ numberOfCallingFindAll)).findAll(any(Specification.class));
        verify(houseRepo, times(numberOfCallingSave)).save(any(House.class));
    }

}