package com.capstone.startmap.domain.franchise_result.api.dto;

import com.capstone.startmap.domain.franchise_result.Franchise_result;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FranchiseResultResDto {

    @Schema(description = "프랜차이즈 검색 결과 개별 dto 리스트")
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
