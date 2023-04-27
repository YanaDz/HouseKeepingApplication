package com.dziadkouskaya.housekeeping.controller.handler;

import com.dziadkouskaya.housekeeping.entity.dto.CreatedHouseDto;
import com.dziadkouskaya.housekeeping.entity.dto.HouseDtoRequest;
import com.dziadkouskaya.housekeeping.facade.HouseFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = HouseController.PATH)
public class HouseController {
    public static final String PATH = "/house";

    private final HouseFacade houseFacade;

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedHouseDto createHouse(@RequestBody @Valid HouseDtoRequest dto) {
        return houseFacade.createHouse(dto);
    }


}
