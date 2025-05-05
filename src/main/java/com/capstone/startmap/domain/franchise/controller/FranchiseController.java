package com.capstone.startmap.domain.franchise.controller;

import com.capstone.startmap.domain.franchise.api.dto.FranchiseResDto;
import com.capstone.startmap.domain.franchise.service.FranchiseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class FranchiseController {
    private final FranchiseService franchiseService;
    public FranchiseController(FranchiseService franchiseService) { this.franchiseService = franchiseService; }

    @GetMapping("/franchise/{franchiseId}")
    public ResponseEntity<FranchiseResDto> getFranchise(@PathVariable long franchiseId) {
        FranchiseResDto franchiseresdto = franchiseService.showFranchise(franchiseId);

        return new ResponseEntity<>(franchiseresdto, HttpStatus.OK);

    }
    @GetMapping("/franchise")
    public ResponseEntity<List<FranchiseResDto>> getFranchises() {
        List<FranchiseResDto> franchiseList = franchiseService.showAllFranchises();
        return new ResponseEntity<>(franchiseList, HttpStatus.OK);

    }
}
