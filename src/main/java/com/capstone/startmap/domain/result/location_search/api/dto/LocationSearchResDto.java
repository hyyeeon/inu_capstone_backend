package com.capstone.startmap.domain.result.location_search.api.dto;

import com.capstone.startmap.domain.result.franchise_search.Franchise_search;
import com.capstone.startmap.domain.result.location_search.Location_search;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LocationSearchResDto {

    @Schema(description = "프랜차이즈 ID")
    private Long franchise_id;

    @Schema(description = "검색 ID, 후에 상세 조회할 때 필요")
    private Long result_id;

    @Schema(description = "검색 날짜")
    private Date date;

    public LocationSearchResDto(Location_search location_search) {

    }
    public static LocationSearchResDto fromLocationSearch(Location_search location_search) {
        return new LocationSearchResDto(location_search);
    }
}
