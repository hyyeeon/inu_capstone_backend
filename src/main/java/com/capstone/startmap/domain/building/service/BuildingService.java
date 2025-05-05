package com.capstone.startmap.domain.building.service;

import com.capstone.startmap.domain.building.Building;
import com.capstone.startmap.domain.building.api.dto.BuildingResDto;
import com.capstone.startmap.domain.building.repository.BuildingRepository;
import com.capstone.startmap.exception.building.NotFoundBuildingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class BuildingService {
    private final BuildingRepository buildingRepository;

    public BuildingResDto showBuilding(Long building_id) {
        Building building = buildingRepository.findById(building_id).orElseThrow(()->
                new NotFoundBuildingException("존재하지 않는 건물입니다."));
        return BuildingResDto.fromBuilding(building);
    }
}
