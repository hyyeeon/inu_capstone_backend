package com.capstone.startmap.domain.location_result.api.dto;

import com.capstone.startmap.domain.location_result.LocationResult;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LocationResultResDto {

    @Schema(description = "위치 검색 결과 개별 dto 리스트")
    private List<LocationIndResultResDto> locationResult_list;

    public LocationResultResDto(List<LocationResult> locationResults) {
        this.locationResult_list = locationResults.stream()
                .map(LocationIndResultResDto::fromLocationResult)
                .toList();
    }

    public static LocationResultResDto fromLocationResultList(List<LocationResult> locationResults) {
        return new LocationResultResDto(locationResults); }
}
