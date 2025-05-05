package com.capstone.startmap.domain.result.franchise_search.api.dto;

import com.capstone.startmap.domain.result.Result;
import com.capstone.startmap.domain.result.franchise_search.Franchise_search;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FranchiseSearchResDto {
    private Long building_id;
    private Long result_id;
    private Date date;

    public FranchiseSearchResDto(Franchise_search franchise_search) {

    }
    public static FranchiseSearchResDto fromFranchiseSearch(Franchise_search franchise_search) {
        return new FranchiseSearchResDto(franchise_search);
    }
}
