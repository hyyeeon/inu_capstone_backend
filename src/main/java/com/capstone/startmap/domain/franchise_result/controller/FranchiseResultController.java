package com.capstone.startmap.domain.franchise_result.controller;

import com.capstone.startmap.domain.franchise_result.api.dto.FranchiseResultResDto;
import com.capstone.startmap.domain.franchise_result.service.FranchiseResultService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class FranchiseResultController {
    private final FranchiseResultService franchiseResultService;
    public FranchiseResultController(FranchiseResultService franchiseResultService) {this.franchiseResultService = franchiseResultService; }

//    @Operation(summary = "프랜차이즈 검색 상세 결과",
//            description = "searchId를 이용해 검색 결과 중 프랜차이즈 검색 결과의 상세를 조회합니다.")
//    @GetMapping("/franchiseResult/{searchId}")
//    public ResponseEntity<FranchiseResultResDto> getFranchiseResult(
//            @Parameter(description = "검색 결과의 ID")
//            @PathVariable long searchId) {
//        FranchiseResultResDto franchiseResultResDto = franchiseResultService.showFranchiseResult(searchId);
//
//        return new ResponseEntity<>(franchiseResultResDto, HttpStatus.OK);
//    }
}
