package com.capstone.startmap.domain.ai.service;

import com.capstone.startmap.domain.ai.dto.PredictFranchiseResponseDto;
import com.capstone.startmap.domain.ai.dto.PredictRequestDto;
import com.capstone.startmap.domain.ai.dto.PredictLocationResponseDto;
import com.capstone.startmap.domain.building.Building;
import com.capstone.startmap.domain.building.service.BuildingService;
import com.capstone.startmap.domain.franchise.Franchise;
import com.capstone.startmap.domain.franchise.api.dto.FranchiseDto;
import com.capstone.startmap.domain.franchise.service.FranchiseService;
import com.capstone.startmap.domain.franchise_result.api.dto.FranchiseResultCreateDto;
import com.capstone.startmap.domain.franchise_result.service.FranchiseResultService;
import com.capstone.startmap.domain.location_result.api.dto.LocationResultCreateDto;
import com.capstone.startmap.domain.location_result.service.LocationResultService;
import com.capstone.startmap.domain.result.Result;
import com.capstone.startmap.domain.result.ResultType;
import com.capstone.startmap.domain.result.api.dto.ResultCreateDto;
import com.capstone.startmap.domain.result.service.ResultService;
import com.capstone.startmap.domain.user.User;
import com.capstone.startmap.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AiService {
    private final BuildingService buildingService;
    private final FranchiseService franchiseService;
    private final UserService userService;
    private final ResultService resultService;
    private final LocationResultService locationResultService;
    private final FranchiseResultService franchResultService;
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
    public void saveLocationResult(Long userId, Long franchiseId, List<PredictLocationResponseDto> response){
        User user = userService.findUserById(userId);
        Franchise franchise = franchiseService.findById(franchiseId);
        //1순위 상가의 id를 넣음
        Building building = buildingService.findById(response.get(0).getBuilding_id());
        Result result = resultService.createResult(ResultCreateDto.builder()
                .type(ResultType.LOCATION)
                .user(user)
                .franchise(franchise)
                .building(building)
                .date(LocalDate.now())
                .build());
        for (PredictLocationResponseDto dto : response) {
            Building location = buildingService.findById(dto.getBuilding_id());
            LocationResultCreateDto createDto = LocationResultCreateDto.builder().building(location).result(result).build();
            locationResultService.createLocationResult(dto, createDto);
        }
    }

    public void saveFranchiseResult(Long userId, Long buildingId, List<PredictFranchiseResponseDto> response){
        User user = userService.findUserById(userId);
        Building building = buildingService.findById(buildingId);
        //1순위 상가의 id를 넣음
        Franchise franchise = franchiseService.findById(response.get(0).getFranchise_id());
        Result result = resultService.createResult(ResultCreateDto.builder()
                .type(ResultType.FRANCHISE)
                .user(user)
                .franchise(franchise)
                .building(building)
                .date(LocalDate.now())
                .build());
        for (PredictFranchiseResponseDto dto : response) {
            Franchise fran = franchiseService.findById(dto.getFranchise_id());
            FranchiseResultCreateDto createDto = FranchiseResultCreateDto.builder().franchise(fran).result(result).build();
            franchResultService.createFranchiseResult(dto, createDto);
        }
    }
}
