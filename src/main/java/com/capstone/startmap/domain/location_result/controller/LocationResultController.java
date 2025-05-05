package com.capstone.startmap.domain.location_result.controller;

import com.capstone.startmap.domain.location_result.api.dto.LocationResultResDto;
import com.capstone.startmap.domain.location_result.service.LocationResultService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class LocationResultController {
    private final LocationResultService locationResultService;
    public LocationResultController(LocationResultService locationResultService) { this.locationResultService = locationResultService; }

    @GetMapping("/locationResult/{searchId}")
    public ResponseEntity<LocationResultResDto> getLocationResult(@PathVariable Long searchId) {
        LocationResultResDto locationResultResDto = locationResultService.showLocationResult(searchId);

        return new ResponseEntity<>(locationResultResDto, HttpStatus.OK);
    }
}
