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
    private List<LocationIndResultResDto> locationResult_list;

    public LocationResultResDto(List<Location_result> locationResults) {
        this.locationResult_list = locationResults.stream()
                .map(LocationIndResultResDto::fromLocationResult)
                .toList();
    }

    public static LocationResultResDto fromLocationResultList(List<Location_result> locationResults) {
        return new LocationResultResDto(locationResults); }
}
