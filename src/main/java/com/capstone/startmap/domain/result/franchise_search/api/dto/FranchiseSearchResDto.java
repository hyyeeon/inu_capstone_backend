package com.capstone.startmap.domain.result.franchise_search.api.dto;

import com.capstone.startmap.domain.result.Result;
import com.capstone.startmap.domain.result.franchise_search.Franchise_search;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FranchiseSearchResDto {

    @Schema(description = "상가 ID")
    private Long building_id;

    @Schema(description = "검색 ID, 후에 상세 조회할 때 필요")
    private Long result_id;

    @Schema(description = "검색 날짜")
    private Date date;

    public FranchiseSearchResDto(Franchise_search franchise_search) {

    }
    public static FranchiseSearchResDto fromFranchiseSearch(Franchise_search franchise_search) {
        return new FranchiseSearchResDto(franchise_search);
    }
}
