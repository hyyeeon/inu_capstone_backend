package com.capstone.startmap.domain.result.controller;

import com.capstone.startmap.config.CustomUserDetails;
import com.capstone.startmap.domain.ai.dto.PredictFranchiseResponseDto;
import com.capstone.startmap.domain.ai.dto.PredictLocationResponseDto;
import com.capstone.startmap.domain.result.api.dto.ResultResDto;
import com.capstone.startmap.domain.result.service.ResultService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ResultController {
    private final ResultService resultService;
    public ResultController(ResultService resultService) { this.resultService = resultService; }

    @Operation(summary = "전체 결과 정보 조회",
            description = "해당 유저의 전체 검색 기록을 조회합니다. {type} - 프랜차이즈 조회: 0, 위치 추천 조회: 1")
    @GetMapping("/result/{type}")
    public ResponseEntity<List<ResultResDto>> getAllResult(
            @Parameter(description = "조회할 결과의 ID")
            @PathVariable Integer type, @AuthenticationPrincipal CustomUserDetails userDetails) {
        Long userId = userDetails.getId();
        List<ResultResDto> resultresdto = resultService.getAllResult(userId, type);

        return new ResponseEntity<>(resultresdto, HttpStatus.OK);
    }

    @Operation(summary = "프랜차이즈 결과 상세 조회",
            description = "resutlId를 이용해 프랜차이즈 예측 결과의 상세 정보를 조회합니다.")
    @GetMapping("/result/franchise/{resultId}")
    public ResponseEntity<List<PredictFranchiseResponseDto>> getFranchiseResult(
            @Parameter(description = "조회할 결과의 ID")
            @PathVariable Long resultId, @AuthenticationPrincipal CustomUserDetails userDetails) {
        Long userId = userDetails.getId();
        List<PredictFranchiseResponseDto> response = resultService.getFranchiseResult(userId, resultId);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @Operation(summary = "위치 추천 결과 상세 조회",
            description = "resutlId를 이용해 위치 추천 결과의 상세 정보를 조회합니다.")
    @GetMapping("/result/location/{resultId}")
    public ResponseEntity<List<PredictLocationResponseDto>> getLocationResult(
            @Parameter(description = "조회할 결과의 ID")
            @PathVariable Long resultId, @AuthenticationPrincipal CustomUserDetails userDetails) {
        Long userId = userDetails.getId();
        List<PredictLocationResponseDto> response = resultService.getLocationResult(userId, resultId);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
