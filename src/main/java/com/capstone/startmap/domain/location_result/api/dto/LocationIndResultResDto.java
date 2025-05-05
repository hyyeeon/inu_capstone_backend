package com.capstone.startmap.domain.location_result.api.dto;

import com.capstone.startmap.domain.location_result.Location_result;

public class LocationIndResultResDto {
    private Long expected_id;
    private Integer rank;
    private Long building_id;

    public LocationIndResultResDto(Location_result location_result) {

    }
    public static LocationIndResultResDto fromLocationResult(Location_result location_result) {
        return new LocationIndResultResDto(location_result);
    }
}
