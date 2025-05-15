package com.capstone.startmap.domain.ai.service;

import com.capstone.startmap.domain.ai.dto.PredictFranchiseRequestDto;
import com.capstone.startmap.domain.ai.dto.PredictRequestDto;
import com.capstone.startmap.domain.building.service.BuildingService;
import com.capstone.startmap.domain.franchise.api.dto.FranchiseDto;
import com.capstone.startmap.domain.franchise.service.FranchiseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AiService {
    private final BuildingService buildingService;
    private final FranchiseService franchiseService;
    public List<PredictRequestDto> getBuildingsInfo(List<Long> buildings_id, String model_name){
        List<PredictRequestDto> buildings = buildingService.getBuildings(buildings_id, model_name);
        return buildings;
    }
    public PredictRequestDto getBuildingInfo(Long building_id, String model_name){
        PredictRequestDto building = buildingService.getBuilding(building_id, model_name);
        return building;
    }
    public List<FranchiseDto> getFranchisesNames(List<Long> franchise_ids){
        List<FranchiseDto> result = franchiseService.getFranchisesname(franchise_ids);
        return result;
    }
    public FranchiseDto getFranchiseName(Long franchise_id){
        FranchiseDto result = franchiseService.getFranchisename(franchise_id);
        return result;
    }
}
