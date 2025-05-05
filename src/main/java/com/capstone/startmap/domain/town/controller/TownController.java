package com.capstone.startmap.domain.town.controller;

import com.capstone.startmap.domain.town.api.dto.TownResDto;
import com.capstone.startmap.domain.town.service.TownService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class TownController {
    private final TownService townService;
    public TownController(TownService townService) { this.townService = townService; }


    @GetMapping("/town/{townId}")
    public ResponseEntity<TownResDto> getResult(@PathVariable Long townId) {
        TownResDto townResDto = townService.showTown(townId);
        return new ResponseEntity<>(townResDto, HttpStatus.OK);
    }
}
