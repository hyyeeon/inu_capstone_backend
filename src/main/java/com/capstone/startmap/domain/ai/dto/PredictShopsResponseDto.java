package com.capstone.startmap.domain.ai.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "위치 추천 응답")
public class PredictShopsResponseDto {
    @Schema(description = "빌딩 id")
    private Long building_id;
    @Schema(description = "순위")
    private Integer rank;
    @Schema(description = "예상 매출, 단위 만원")
    private Integer predict_sales;
    @Schema(description = "높은 가중치 3개")
    private String importance;
}
