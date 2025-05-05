package com.capstone.startmap.domain.franchise_result.api.dto;


import com.capstone.startmap.domain.franchise.Franchise;
import com.capstone.startmap.domain.franchise.api.dto.FranchiseResDto;
import com.capstone.startmap.domain.franchise_result.Franchise_result;
import com.capstone.startmap.domain.result.api.dto.ResultResDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FranchiseResultResDto {
    private List<ResultResDto> franchiseResult_list;

    public FranchiseResultResDto(Franchise_result franchise_result) {

    }

    public static FranchiseResultResDto fromFranchiseResult(Franchise_result franchise_result) { return new FranchiseResultResDto(franchise_result); }
}
