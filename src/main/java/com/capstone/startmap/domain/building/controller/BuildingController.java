package com.capstone.startmap.domain.building.controller;

import com.capstone.startmap.domain.building.api.dto.BuildingResDto;
import com.capstone.startmap.domain.building.service.BuildingService;
import io.swagger.v3.oas.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class BuildingController {
    private final BuildingService buildingService;
    public BuildingController(BuildingService buildingService) { this.buildingService = buildingService; }

    @Operation(summary = "상가 정보 조회",
            description = "buildingId를 이용해 특정 상가의 상세 정보를 조회합니다.")
    @GetMapping("/building/{buildingId}")
    public ResponseEntity<BuildingResDto> getBuilding(
            @Parameter(description = "조회할 상가의 ID")
            @PathVariable long buildingId) {
        BuildingResDto buildingpresdto = buildingService.showBuilding(buildingId);

        return new ResponseEntity<>(buildingpresdto, HttpStatus.OK);

    }
}
