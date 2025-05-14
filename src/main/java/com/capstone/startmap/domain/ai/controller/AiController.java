package com.capstone.startmap.domain.ai.controller;

import com.capstone.startmap.domain.ai.dto.PredictRequestDto;
import com.capstone.startmap.domain.ai.dto.PredictResponseDto;
import com.capstone.startmap.domain.ai.dto.PredictShopsResponseDto;
import com.capstone.startmap.domain.ai.service.AiService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/predict")
@Tag(name = "Ai", description = "Ai API")
public class AiController {
    private final RestTemplate restTemplate;
    private final AiService aiService;

    @Operation(summary = "프랜차이즈와 상가가 주어질 때 매출 예측", description = "상가 정보와 프랜차이즈 모델 이름을 입력으로 받으면, 예상 매출과 높은 가중치 3개를 응답합니다.")
    @PostMapping("/{model_name}")
    public ResponseEntity<PredictResponseDto> getPrediction(
            @PathVariable String model_name,
            @RequestBody PredictRequestDto input
    ) {
        PredictResponseDto response= networkPythonServer(model_name, input);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @Operation(summary = "위치 추천", description = "상가 id 3개를 요청하면 예상 매출/중요 가중치 3개/순위를 응답합니다.")
    @PostMapping("/shops/{model_name}")
    public ResponseEntity<List<PredictShopsResponseDto>> PredictShops(
            @PathVariable String model_name,
            @RequestBody List<Long> building_ids
            ) {
        List<PredictRequestDto> buildings = aiService.getBuildingsInfo(building_ids, model_name);
        List<PredictResponseDto> responses = new ArrayList<>();
        List<PredictShopsResponseDto> response = new ArrayList<>();
        for (PredictRequestDto input :buildings) {
            PredictResponseDto result = networkPythonServer(model_name, input);
            responses.add(result);
        }
        responses.sort(Comparator.comparingInt(PredictResponseDto::getPredict_sales).reversed());
        for (PredictResponseDto dto: responses) {
            response.add(dto.toShopsDto(responses.indexOf(dto)+1));
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public PredictResponseDto networkPythonServer(String model_name, PredictRequestDto input){
        String pythonApiUrl = "http://localhost:5000/predict/" + model_name;

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(input.toMap());
        ResponseEntity<Map> response = restTemplate.postForEntity(pythonApiUrl, request, Map.class);

        Map<String, Object> responseBody = response.getBody();

        Integer predictSales = (responseBody != null && responseBody.get("prediction") != null)
                ? ((Number) responseBody.get("prediction")).intValue()
                : null;

        String description = (responseBody != null) ? (String) responseBody.get("explanation") : null;

        return new PredictResponseDto(input.getBuilding_id(), predictSales, description);
    }

}
