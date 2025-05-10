package com.capstone.startmap.domain.ai.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "매출 예상 응답")
public class PredictResponseDto {
    private Integer predict_sales;
    private String description;
}
