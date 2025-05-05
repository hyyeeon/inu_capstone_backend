package com.capstone.startmap.domain.location_result.api.dto;

import com.capstone.startmap.domain.location_result.Location_result;
import com.capstone.startmap.domain.result.api.dto.ResultResDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LocationResultResDto {
    private List<ResultResDto> locationResult_list;

    public LocationResultResDto(Location_result location_result) {

    }

    public static LocationResultResDto fromLocationResult(Location_result location_result) { return new LocationResultResDto(location_result); }
}
