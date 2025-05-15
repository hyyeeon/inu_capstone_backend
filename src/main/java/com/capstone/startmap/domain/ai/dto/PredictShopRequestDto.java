package com.capstone.startmap.domain.ai.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "위치 예상 요청")
public class PredictShopRequestDto {
    @Schema(description = "프랜차이즈 id")
    private Long franchise_id;
    @Schema(description = "상가 건물 id 최대 3개")
    private List<Long> building_ids;
}
