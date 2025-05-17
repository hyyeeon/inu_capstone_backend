package com.capstone.startmap.domain.franchise_result.api.dto;

import com.capstone.startmap.domain.franchise.Franchise;
import com.capstone.startmap.domain.result.Result;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class FranchiseResultCreateDto {
    private Result result;
    private Franchise franchise;
}
