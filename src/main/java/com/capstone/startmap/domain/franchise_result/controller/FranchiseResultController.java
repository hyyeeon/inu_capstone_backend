package com.capstone.startmap.domain.franchise_result.controller;

import com.capstone.startmap.domain.franchise_result.api.dto.FranchiseResultResDto;
import com.capstone.startmap.domain.franchise_result.service.FranchiseResultService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class FranchiseResultController {
    private final FranchiseResultService franchiseResultService;
    public FranchiseResultController(FranchiseResultService franchiseResultService) {this.franchiseResultService = franchiseResultService; }

    @GetMapping("/franchiseResult/{searchId}")
    public ResponseEntity<FranchiseResultResDto> getFranchiseResult(@PathVariable long searchId) {
        FranchiseResultResDto franchiseResultResDto = franchiseResultService.showFranchiseResult(searchId);

        return new ResponseEntity<>(franchiseResultResDto, HttpStatus.OK);
    }
}
