package com.capstone.startmap.domain.location_result.controller;

import com.capstone.startmap.domain.location_result.api.dto.LocationResultResDto;
import com.capstone.startmap.domain.location_result.service.LocationResultService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class LocationResultController {
    private final LocationResultService locationResultService;
    public LocationResultController(LocationResultService locationResultService) { this.locationResultService = locationResultService; }

//    @Operation(summary = "위치 검색 상세 결과",
//            description = "searchId를 이용해 검색 결과 중 위치 검색 결과의 상세를 조회합니다.")
//    @GetMapping("/locationResult/{searchId}")
//    public ResponseEntity<LocationResultResDto> getLocationResult(
//            @Parameter(description = "검색 결과의 ID")
//            @PathVariable Long searchId) {
//        LocationResultResDto locationResultResDto = locationResultService.showLocationResult(searchId);
//
//        return new ResponseEntity<>(locationResultResDto, HttpStatus.OK);
//    }
}
