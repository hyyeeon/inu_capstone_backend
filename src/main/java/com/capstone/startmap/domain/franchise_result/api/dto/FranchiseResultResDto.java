package com.capstone.startmap.domain.franchise_result.api.dto;

import com.capstone.startmap.domain.franchise_result.Franchise_result;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FranchiseResultResDto {
    private List<FranchiseIndResultResDto> franchiseResult_list;

    public FranchiseResultResDto(List<Franchise_result> franchiseResults) {
        this.franchiseResult_list = franchiseResults.stream()
                .map(FranchiseIndResultResDto::fromFranchiseResult)
                .toList();
    }

    public static FranchiseResultResDto fromFranchiseResultList(List<Franchise_result> franchiseResults) {
        return new FranchiseResultResDto(franchiseResults);
    }
}
