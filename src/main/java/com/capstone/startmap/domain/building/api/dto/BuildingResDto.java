package com.capstone.startmap.domain.building.api.dto;

import com.capstone.startmap.domain.building.Building;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BuildingResDto {

    @Schema(description = "상가 이름")
    private String building_name;

    @Schema(description = "상가 매출액")
    private String building_sales;

    @Schema(description = "상가 주소")
    private String address;

    @Schema(description = "상가에 속한 매장 목록")
    private List<Long> building_shop;

    public BuildingResDto(Building building) {

    }

    public static BuildingResDto fromBuilding(Building building) {
        return new BuildingResDto(building);
    }
}
