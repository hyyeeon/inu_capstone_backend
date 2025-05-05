package com.capstone.startmap.domain.building.api.dto;

import com.capstone.startmap.domain.building.Building;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BuildingResDto {
    private String building_name;
    private String building_sales;
    private String address;
    private List<Long> building_shop;

    public BuildingResDto(Building building) {

    }

    public static BuildingResDto fromBuilding(Building building) {
        return new BuildingResDto(building);
    }
}
