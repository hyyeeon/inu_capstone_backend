package com.capstone.startmap.domain.result.controller;

import com.capstone.startmap.domain.result.api.dto.ResultResDto;
import com.capstone.startmap.domain.result.service.ResultService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class ResultController {
    private final ResultService resultService;
    public ResultController(ResultService resultService) { this.resultService = resultService; }

    @Operation(summary = "결과 정보 조회",
            description = "resutlId를 이용해 특정 결과의 상세 정보를 조회합니다.")
    @GetMapping("/result/{resultId}")
    public ResponseEntity<ResultResDto> getResult(
            @Parameter(description = "조회할 결과의 ID")
            @PathVariable Long resultId) {
        ResultResDto resultresdto = resultService.showResult(resultId);

        return new ResponseEntity<>(resultresdto, HttpStatus.OK);
    }
}
