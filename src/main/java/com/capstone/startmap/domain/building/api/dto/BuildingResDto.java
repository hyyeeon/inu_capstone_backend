package com.capstone.startmap.domain.building.api.dto;

import com.capstone.startmap.domain.building.Building;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BuildingResDto {
    private Long building_id;
    private String building_name;
    private String building_sales;
    private String address;
    // 각 건물 별 매장 수가 필요

    public BuildingResDto(Building building) {

    }

    public static BuildingResDto fromBuilding(Building building) {
        return new BuildingResDto(building);
    }
}
