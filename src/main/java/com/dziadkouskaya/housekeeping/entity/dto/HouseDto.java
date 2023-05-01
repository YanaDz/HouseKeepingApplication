package com.dziadkouskaya.housekeeping.entity.dto;

import com.dziadkouskaya.housekeeping.entity.Entrance;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class HouseDto {
    private Long id;
    private String name;
    private String address;
    private int entranceNumber;
    private Set<Entrance> entrance;
}
