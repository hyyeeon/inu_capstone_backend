package com.capstone.startmap.domain.ai.dto;

import com.capstone.startmap.domain.franchise.api.dto.FranchiseDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "매출 예상 응답")
public class PredictResponseDto {
    @Schema(description = "프랜차이즈 id")
    private Long franchise_id;
    @Schema(description = "상가 id")
    private Long building_id;
    @Schema(description = "예상 매출, 단위 만원")
    private Integer predict_sales;
    @Schema(description = "높은 가중치 3개")
    private String importance;

    public PredictShopsResponseDto toShopsDto(Integer rank){
        return PredictShopsResponseDto.builder()
                .building_id(this.building_id)
                .rank(rank)
                .predict_sales(this.predict_sales)
                .importance(this.importance)
                .build();
    }
    public PredictFranchiseResponseDto toFranchisesDto(Integer rank){
        return PredictFranchiseResponseDto.builder()
                .franchise_id(this.franchise_id)
                .rank(rank)
                .predict_sales(this.predict_sales)
                .importance(this.importance)
                .build();
    }
}