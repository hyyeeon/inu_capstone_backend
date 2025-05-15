package com.capstone.startmap.domain.location_result.api.dto;

import com.capstone.startmap.domain.location_result.LocationResult;
import io.swagger.v3.oas.annotations.media.Schema;

public class LocationIndResultResDto {

    @Schema(description = "예상 매출액")
    private Long expected_id;

    @Schema(description = "순위")
    private Integer rank;

    @Schema(description = "상가 아이디")
    private Long building_id;

    public LocationIndResultResDto(LocationResult location_result) {

    }
    public static LocationIndResultResDto fromLocationResult(LocationResult location_result) {
        return new LocationIndResultResDto(location_result);
    }
}
