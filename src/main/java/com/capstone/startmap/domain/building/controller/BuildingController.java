package com.capstone.startmap.domain.building.controller;

import com.capstone.startmap.domain.building.api.dto.BuildingResDto;
import com.capstone.startmap.domain.building.service.BuildingService;
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

    @GetMapping("/building/{buildingId}")
    public ResponseEntity<BuildingResDto> getBuilding(@PathVariable long buildingId) {
        BuildingResDto buildingpresdto = buildingService.showBuilding(buildingId);

        return new ResponseEntity<>(buildingpresdto, HttpStatus.OK);

    }
}
