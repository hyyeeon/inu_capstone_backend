package com.capstone.startmap.domain.building.service;

import com.capstone.startmap.domain.ai.dto.PredictRequestDto;
import com.capstone.startmap.domain.building.Building;
import com.capstone.startmap.domain.building.api.dto.BuildingResDto;
import com.capstone.startmap.domain.building.dto.BuildingDtoCSV;
import com.capstone.startmap.domain.building.repository.BuildingRepository;
import com.capstone.startmap.domain.franchise.api.dto.FranchiseResDto;
import com.capstone.startmap.domain.shop.Shop;
import com.capstone.startmap.domain.shop.api.dto.ShopResDto;
import com.capstone.startmap.domain.shop.repository.ShopRepository;
import com.capstone.startmap.exception.building.NotFoundBuildingException;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.info.ProjectInfoProperties;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor

public class BuildingService {
    private final BuildingRepository buildingRepository;
    private final ShopRepository shopRepository;

    public BuildingResDto showBuilding(Long building_id) {
        Building building = buildingRepository.findById(building_id).orElseThrow(()->
                new NotFoundBuildingException("존재하지 않는 건물입니다."));
        List<Shop> shops = shopRepository.findByBuildingId(building);
        List <ShopResDto> shopdtos = shops.stream()
                .map(ShopResDto::fromShop)  // fromFranchise 메서드를 그대로 활용
                .toList();
        return BuildingResDto.fromBuilding(building, shopdtos);
    }

    public List<PredictRequestDto> getBuildings(List<Long> buildings_id, String model_name){
        List<PredictRequestDto> buildings = new ArrayList<>();
        for (Long id :buildings_id) {
            Building building = buildingRepository.findById(id).orElseThrow(()->
                    new NotFoundBuildingException("존재하지 않는 건물입니다."));
            PredictRequestDto predictDto = building.toDto(model_name);
            buildings.add(predictDto);
        }
        return buildings;
    }
    public PredictRequestDto getBuilding(Long building_id, String model_name){
        Building building = buildingRepository.findById(building_id).orElseThrow(()->
                new NotFoundBuildingException("존재하지 않는 건물입니다."));
        return building.toDto(model_name);
    }
    public Building findById(Long building_id){
        return buildingRepository.findById(building_id).orElseThrow(()->
                new NotFoundBuildingException("존재하지 않는 건물입니다."));
    }
}
