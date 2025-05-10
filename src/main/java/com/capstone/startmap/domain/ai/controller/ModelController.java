package com.capstone.startmap.domain.ai.controller;

import com.capstone.startmap.domain.ai.dto.PredictRequestDto;
import com.capstone.startmap.domain.ai.dto.PredictResponseDto;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/predict")
public class ModelController {

    private final RestTemplate restTemplate;

    public ModelController(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    @PostMapping("/{model_name}")
    public ResponseEntity<PredictResponseDto> getPrediction(
            @PathVariable String model_name,
            @RequestBody PredictRequestDto input
    ) {
        String pythonApiUrl = "http://localhost:5000/predict/" + model_name;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(input.toMap(), headers);
        ResponseEntity<Map> response = restTemplate.postForEntity(pythonApiUrl, request, Map.class);


        //http://host.docker.internal:5000/predict/cafe_pack"
//        HttpEntity<Map<String, Object>> request = new HttpEntity<>(input.toMap());
//        ResponseEntity<Map> response = restTemplate.postForEntity(pythonApiUrl, request, Map.class);

        Map<String, Object> responseBody = response.getBody();

        Integer predictSales = (responseBody != null && responseBody.get("prediction") != null)
                ? ((Number) responseBody.get("prediction")).intValue()
                : null;

        String description = (responseBody != null) ? (String) responseBody.get("explanation") : null;

        PredictResponseDto result = new PredictResponseDto(predictSales, description);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}