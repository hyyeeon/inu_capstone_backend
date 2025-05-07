package com.capstone.startmap.domain.result.location_search.controller;

import com.capstone.startmap.domain.result.franchise_search.api.dto.FranchiseSearchResDto;
import com.capstone.startmap.domain.result.location_search.api.dto.LocationSearchResDto;
import com.capstone.startmap.domain.result.location_search.service.LocationSearchService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class LocationSearchController {
    private final LocationSearchService locationSearchService;
    public LocationSearchController(LocationSearchService locationSearchService) {
        this.locationSearchService = locationSearchService;
    }
    @Operation(summary = "위치 검색 결과 조회",
            description = "userId를 이용해 사용자의 위치 검색 결과 전체를 조회합니다.")
    @GetMapping("/mypage/location/{userId}")
    public ResponseEntity<List<LocationSearchResDto>> getLocation(@PathVariable long userId) {
        List<LocationSearchResDto> locationSearchList = locationSearchService.showLocationSearch(userId);

        return new ResponseEntity<>(locationSearchList, HttpStatus.OK);
    }
}
