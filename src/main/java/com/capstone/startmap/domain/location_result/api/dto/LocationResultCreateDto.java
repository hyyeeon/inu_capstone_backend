package com.capstone.startmap.domain.location_result.api.dto;

import com.capstone.startmap.domain.building.Building;
import com.capstone.startmap.domain.result.Result;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class LocationResultCreateDto {
    private Building building;
    private Result result;
}
