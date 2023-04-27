package com.dziadkouskaya.housekeeping.entity.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import static com.dziadkouskaya.housekeeping.utils.Constants.NOT_EMPTY_ERROR_MESSAGE;
import static com.dziadkouskaya.housekeeping.utils.Constants.NOT_NULL_ERROR_MESSAGE;

@Data
@Builder
public class HouseDtoRequest {
    @NotNull(message = NOT_NULL_ERROR_MESSAGE)
    @NotBlank(message = NOT_EMPTY_ERROR_MESSAGE)
    private String name;

    @NotNull(message = NOT_NULL_ERROR_MESSAGE)
    @NotBlank(message = NOT_EMPTY_ERROR_MESSAGE)
    private String address;

    @Min(1)
    private int entranceNumber;
}
