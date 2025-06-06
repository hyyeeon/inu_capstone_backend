package com.capstone.startmap.domain.ai.dto;

import com.capstone.startmap.domain.franchise_result.FranchiseResult;
import com.capstone.startmap.domain.franchise_result.api.dto.FranchiseResultCreateDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "프랜차이즈 추천 응답")
public class PredictFranchiseResponseDto {
    @Schema(description = "프랜차이즈 id")
    private Long franchise_id;
    @Schema(description = "순위")
    private Integer rank;
    @Schema(description = "예상 매출, 단위 만원")
    private Integer predict_sales;
    @Schema(description = "높은 가중치 3개")
    private String importance;
    public FranchiseResult toEntity(FranchiseResultCreateDto dto){
        return FranchiseResult.builder()
                .rank(this.rank)
                .predict_sales(this.predict_sales)
                .importance(this.importance)
                .result(dto.getResult())
                .franchise(dto.getFranchise())
                .build();
    }
}
