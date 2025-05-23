package com.capstone.startmap.domain.franchise.controller;

import com.capstone.startmap.domain.franchise.api.dto.FranchiseResDto;
import com.capstone.startmap.domain.franchise.service.FranchiseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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

    @Operation(summary = "개별 프랜차이즈 검색",
            description = "franchiseId를 이용해 특정 프랜차이즈의 상세 정보를 조회합니다.")
    @GetMapping("/franchise/{franchiseId}")
    public ResponseEntity<FranchiseResDto> getFranchise(
            @Parameter(description = "조회할 프랜차이즈의 ID")
            @PathVariable long franchiseId) {
        FranchiseResDto franchiseresdto = franchiseService.showFranchise(franchiseId);

        return new ResponseEntity<>(franchiseresdto, HttpStatus.OK);

    }
    @Operation(summary = "전체 프랜차이즈 조회",
            description = "전체 프랜차이즈의 상세 정보를 조회합니다.")
    @GetMapping("/franchises")
    public ResponseEntity<List<FranchiseResDto>> getFranchises() {
        List<FranchiseResDto> franchiseList = franchiseService.showAllFranchises();
        return new ResponseEntity<>(franchiseList, HttpStatus.OK);

    }
}
