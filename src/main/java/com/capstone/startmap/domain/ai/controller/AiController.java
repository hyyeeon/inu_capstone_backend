package com.capstone.startmap.domain.ai.controller;

import com.capstone.startmap.domain.ai.dto.*;
import com.capstone.startmap.domain.ai.service.AiService;
import com.capstone.startmap.domain.franchise.api.dto.FranchiseDto;
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

//    @Operation(summary = "프랜차이즈와 상가가 주어질 때 매출 예측", description = "상가 정보와 프랜차이즈 모델 이름을 입력으로 받으면, 예상 매출과 높은 가중치 3개를 응답합니다.")
//    @PostMapping("/{model_name}")
//    public ResponseEntity<PredictResponseDto> getPrediction(
//            @PathVariable String model_name,
//            @RequestBody PredictRequestDto input
//    ) {
//        PredictResponseDto response= networkPythonServer(model_name, input);
//        return new ResponseEntity<>(response, HttpStatus.OK);
//    }
    @Operation(summary = "위치 추천", description = "상가 id 3개를 요청하면 예상 매출/중요 가중치 3개/순위를 응답합니다.")
    @PostMapping("/shops")
    public ResponseEntity<List<PredictShopsResponseDto>> PredictShops(
            //@PathVariable String model_name,
            @RequestBody PredictShopRequestDto input
            ) {
        FranchiseDto franchise = aiService.getFranchiseName(input.getFranchise_id());
        List<PredictRequestDto> buildings = aiService.getBuildingsInfo(input.getBuilding_ids(), franchise.getFranchise_name());
        List<PredictResponseDto> py_responses = new ArrayList<>();
        List<PredictShopsResponseDto> response = new ArrayList<>();
        for (PredictRequestDto dto :buildings) {
            PredictResponseDto result = networkPythonServer(franchise, dto);
            py_responses.add(result);
        }
        py_responses.sort(Comparator.comparingInt(PredictResponseDto::getPredict_sales).reversed());
        for (PredictResponseDto dto: py_responses) {
            response.add(dto.toShopsDto(py_responses.indexOf(dto)+1));
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "프랜차이즈 추천", description = "상가 id 3개를 요청하면 예상 매출/중요 가중치 3개/순위를 응답합니다.")
    @PostMapping("/franchises")
    public ResponseEntity<List<PredictFranchiseResponseDto>> predictFranchises(
            @RequestBody PredictFranchiseRequestDto input
            ) {
        List<FranchiseDto> franchises = aiService.getFranchisesNames(input.getFranchise_ids());
        List<PredictResponseDto> py_response = new ArrayList<>();
        List<PredictFranchiseResponseDto> response = new ArrayList<>();
        Long buildingId = input.getBuilding_id();
        for (FranchiseDto dto : franchises) {
            PredictRequestDto building = aiService.getBuildingInfo(buildingId, dto.getFranchise_name());
            PredictResponseDto result  = networkPythonServer(dto, building);
            py_response.add(result);
        }
        py_response.sort(Comparator.comparingInt(PredictResponseDto::getPredict_sales).reversed());
        for (PredictResponseDto dto: py_response) {
            response.add(dto.toFranchisesDto(py_response.indexOf(dto)+1));
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public PredictResponseDto networkPythonServer(FranchiseDto franchise, PredictRequestDto input){
        String pythonApiUrl = "http://localhost:5000/predict/" +franchise.getFranchise_name();

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(input.toMap());
        ResponseEntity<Map> response = restTemplate.postForEntity(pythonApiUrl, request, Map.class);

        Map<String, Object> responseBody = response.getBody();

        Integer predictSales = (responseBody != null && responseBody.get("prediction") != null)
                ? ((Number) responseBody.get("prediction")).intValue()
                : null;

        String description = (responseBody != null) ? (String) responseBody.get("explanation") : null;

        return new PredictResponseDto(franchise.getFranchise_id(), input.getBuilding_id(), predictSales, description);
    }

}
