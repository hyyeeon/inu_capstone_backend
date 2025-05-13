package com.capstone.startmap.domain.ai.controller;

import com.capstone.startmap.domain.ai.dto.PredictRequestDto;
import com.capstone.startmap.domain.ai.dto.PredictResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
@Tag(name = "Ai", description = "Ai API")
public class AiController {
    @Autowired
    RestTemplate restTemplate;

    @Operation(summary = "프랜차이즈와 상가가 주어질 때 매출 예측", description = "상가 정보와 프랜차이즈 모델 이름을 입력으로 받으면, 예상 매출과 높은 가중치 3개를 응답합니다.")
    @PostMapping("/predict/{model_name}")
    public ResponseEntity<PredictResponseDto> getPrediction(
            @PathVariable String model_name,
            @RequestBody PredictRequestDto input
    ) {
        String pythonApiUrl = "http://localhost:5000/predict/" + model_name;

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(input.toMap());
        ResponseEntity<Map> response = restTemplate.postForEntity(pythonApiUrl, request, Map.class);

        Map<String, Object> responseBody = response.getBody();

        Integer predictSales = (responseBody != null && responseBody.get("prediction") != null)
                ? ((Number) responseBody.get("prediction")).intValue()
                : null;

        String description = (responseBody != null) ? (String) responseBody.get("explanation") : null;

        PredictResponseDto result = new PredictResponseDto(predictSales, description);
        return ResponseEntity.ok(result);
    }

}
