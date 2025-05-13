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
    @Schema(description = "예상 매출, 단위 만원")
    private Integer predict_sales;
    @Schema(description = "높은 가중치 3개")
    private String importance;
}