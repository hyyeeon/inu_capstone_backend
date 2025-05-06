package com.capstone.startmap.domain.franchise.api.dto;

import com.capstone.startmap.domain.franchise.Franchise;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FranchiseResDto {

    @Schema(description = "프랜차이즈 종류")
    private String franchise_type;

    @Schema(description = "프랜차이즈 이름")
    private String franchise_name;

    @Schema(description = "프랜차이즈 매장 목록")
    private List<Long> franchise_shop;

    public FranchiseResDto(Franchise franchise) {

    }
    public static FranchiseResDto fromFranchise(Franchise franchise) { return new FranchiseResDto(franchise); }
}
