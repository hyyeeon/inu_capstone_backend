package com.capstone.startmap.domain.franchise.api.dto;

import com.capstone.startmap.domain.franchise.Franchise;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FranchiseResDto {
    private String franchise_type;
    private String franchise_name;
    private List<Long> franchise_shop;

    public FranchiseResDto(Franchise franchise) {

    }
    public static FranchiseResDto fromFranchise(Franchise franchise) { return new FranchiseResDto(franchise); }
}
