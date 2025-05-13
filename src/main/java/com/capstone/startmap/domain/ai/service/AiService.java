package com.capstone.startmap.domain.ai.service;

import com.capstone.startmap.domain.ai.dto.PredictRequestDto;
import com.capstone.startmap.domain.building.service.BuildingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AiService {
    private final BuildingService buildingService;
    public List<PredictRequestDto> getBuildingsInfo(List<Long> buildings_id, String model_name){
        List<PredictRequestDto> buildings = buildingService.getBuildings(buildings_id, model_name);
        return buildings;
    }
}
