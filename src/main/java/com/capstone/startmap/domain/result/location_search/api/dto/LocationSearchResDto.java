package com.capstone.startmap.domain.result.location_search.api.dto;

import com.capstone.startmap.domain.result.franchise_search.Franchise_search;
import com.capstone.startmap.domain.result.location_search.Location_search;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LocationSearchResDto {
    private Long franchise_id;
    private Long result_id;
    private Date date;

    public LocationSearchResDto(Location_search location_search) {

    }
    public static LocationSearchResDto fromLocationSearch(Location_search location_search) {
        return new LocationSearchResDto(location_search);
    }
}
