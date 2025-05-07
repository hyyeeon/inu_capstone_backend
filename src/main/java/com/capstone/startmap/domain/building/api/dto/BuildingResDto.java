package com.capstone.startmap.domain.building.api.dto;

import com.capstone.startmap.domain.building.Building;
import com.capstone.startmap.domain.shop.api.dto.ShopResDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BuildingResDto {

    @Schema(description = "상가 이름")
    private String building_name;

    @Schema(description = "상가 매출액")
    private Integer building_sales;

    @Schema(description = "상가 주소")
    private String address;

    @Schema(description = "상가에 속한 매장 목록")
    private List<ShopResDto> building_shop;

    public BuildingResDto(Building building) {

    }

    public static BuildingResDto fromBuilding(Building building, List<ShopResDto> shops) {
        BuildingResDto dto = new BuildingResDto();
        dto.building_name = "상가 이름";
        dto.building_sales = building.getBuilding_sales();
        dto.address = building.getAddress();
        dto.building_shop = shops;
        return dto;
    }
}
