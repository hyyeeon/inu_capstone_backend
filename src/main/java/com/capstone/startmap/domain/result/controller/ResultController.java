package com.capstone.startmap.domain.result.controller;

import com.capstone.startmap.domain.result.api.dto.ResultResDto;
import com.capstone.startmap.domain.result.service.ResultService;
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

    @GetMapping("/result/{resultId}")
    public ResponseEntity<ResultResDto> getResult(@PathVariable Long resultId) {
        ResultResDto resultresdto = resultService.showResult(resultId);

        return new ResponseEntity<>(resultresdto, HttpStatus.OK);
    }
}
