package com.capstone.startmap.domain.franchise_result.api.dto;

import com.capstone.startmap.domain.franchise_result.Franchise_result;

public class FranchiseIndResultResDto {
    private Long expected_sales;
    private Integer rank;
    private Long franchise_id;

    public FranchiseIndResultResDto(Franchise_result franchise_result) {

    }
    public static FranchiseIndResultResDto fromFranchiseResult(Franchise_result franchise_result) {
        return new FranchiseIndResultResDto(franchise_result);
    }
}
