package com.dziadkouskaya.housekeeping.controller;

import com.dziadkouskaya.housekeeping.entity.dto.HouseDto;
import com.dziadkouskaya.housekeeping.entity.dto.HouseDtoRequest;
import com.dziadkouskaya.housekeeping.facade.HouseFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = HouseController.PATH)
public class HouseController {
    public static final String PATH = "/house";

    private final HouseFacade houseFacade;

    @PostMapping(value = "", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public HouseDto createHouse(@RequestBody @Valid HouseDtoRequest dto) {
        return houseFacade.createHouse(dto);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<HouseDto> getHouses() {
        return houseFacade.getAll();
    }


}
