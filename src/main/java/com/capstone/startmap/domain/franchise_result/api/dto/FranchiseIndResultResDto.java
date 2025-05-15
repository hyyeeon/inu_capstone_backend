package com.capstone.startmap.domain.franchise_result.api.dto;

import com.capstone.startmap.domain.franchise_result.FranchiseResult;
import io.swagger.v3.oas.annotations.media.Schema;

public class FranchiseIndResultResDto {

    @Schema(description = "예상 매출액")
    private Long expected_sales;

    @Schema(description = "순위")
    private Integer rank;

    @Schema(description = "프랜차이즈 아이디")
    private Long franchise_id;

    public FranchiseIndResultResDto(FranchiseResult franchise_result) {

    }
    public static FranchiseIndResultResDto fromFranchiseResult(FranchiseResult franchise_result) {
        return new FranchiseIndResultResDto(franchise_result);
    }
}
