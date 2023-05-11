package com.dziadkouskaya.housekeeping.service.impl;

import com.dziadkouskaya.housekeeping.entity.House;
import com.dziadkouskaya.housekeeping.entity.filters.SearchRequest;
import com.dziadkouskaya.housekeeping.exception.EntityExistedExeption;
import com.dziadkouskaya.housekeeping.repository.HouseRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
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
        var house = createTestHouse();
        var savedHouse = createTestHouse(12L);
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

    @Test
    public void testGetHouseById() {
        var id = 123L;
        var  expectedHouse = createTestHouse(id);
        when(houseRepo.findById(anyLong())).thenReturn(Optional.of(expectedHouse));

        var actualHouse = houseService.getById(id);
        assertEquals(Optional.of(expectedHouse), actualHouse);
        assertEquals(id, actualHouse.get().getId());
        verify(houseRepo, times(1)).findById(id);
    }

    @Test
    public void testGetByNameOrAddress() {
        String searchQuery = "example";
        var searchRequest = SearchRequest.builder().search(searchQuery).build();

        List<House> expectedHouses = List.of(createTestHouse(125L),
            House.builder().id(147L).name("Test2").address("Address2").build());
        when(houseRepo.findAll(any(), any(Pageable.class))).thenReturn(new PageImpl<>(expectedHouses));

        var result = houseService.getByNameOrAddress(searchRequest);

        assertEquals(expectedHouses.size(), result.getContent().size());
        assertEquals(expectedHouses.get(0).getName(), result.getContent().get(0).getName());
        assertEquals(expectedHouses.get(1).getAddress(), result.getContent().get(1).getAddress());
        verify(houseRepo, times(1)).findAll(any(Specification.class), any(Pageable.class));
    }

    private House createTestHouse() {
        return House.builder().name("Test House").address("Test Address").entranceNumber(1).build();
    }

    private House createTestHouse(Long id) {
        return House.builder().id(id).name("Test House").address("Test Address").entranceNumber(1).build();
    }
}
