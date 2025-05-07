package com.capstone.startmap.domain.town.controller;

import com.capstone.startmap.domain.town.api.dto.TownResDto;
import com.capstone.startmap.domain.town.service.TownService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class TownController {
    private final TownService townService;
    public TownController(TownService townService) { this.townService = townService; }

    @Operation(summary = "동 정보 조회",
            description = "townId를 이용해 특정 동의 상세 정보를 조회합니다.")
    @GetMapping("/town/{townId}")
    public ResponseEntity<TownResDto> getResult(
            @Parameter(description = "조회할 동의 ID")
            @PathVariable Long townId) {
        TownResDto townResDto = townService.showTown(townId);
        return new ResponseEntity<>(townResDto, HttpStatus.OK);
    }
}
