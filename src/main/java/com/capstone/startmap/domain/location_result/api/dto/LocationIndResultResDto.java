package com.capstone.startmap.domain.location_result.api.dto;

import com.capstone.startmap.domain.location_result.Location_result;
import io.swagger.v3.oas.annotations.media.Schema;

public class LocationIndResultResDto {

    @Schema(description = "예상 매출액")
    private Long expected_id;

    @Schema(description = "순위")
    private Integer rank;

    @Schema(description = "상가 아이디")
    private Long building_id;

    public LocationIndResultResDto(Location_result location_result) {

    }
    public static LocationIndResultResDto fromLocationResult(Location_result location_result) {
        return new LocationIndResultResDto(location_result);
    }
}
