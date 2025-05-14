package com.capstone.startmap.domain.franchise.api.dto;

import com.capstone.startmap.domain.franchise.Franchise;
import com.capstone.startmap.domain.shop.api.dto.ShopResDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FranchiseResDto {

    @Schema(description = "프랜차이즈 종류")
    private String franchise_type;

    @Schema(description = "프랜차이즈 이름")
    private String franchise_name;

    @Schema(description = "프랜차이즈 매장 목록")
    private List<ShopResDto> franchise_shop;


    public FranchiseResDto(Franchise franchise) {

    }
    public static FranchiseResDto fromFranchise(Franchise franchise) {
        FranchiseResDto dto = new FranchiseResDto();
        dto.franchise_type = franchise.getFranchise_type();
        dto.franchise_name = franchise.getFranchise_name();
        dto.franchise_shop = null;
        return new FranchiseResDto(franchise);
    }
    public static FranchiseResDto fromFranchise(Franchise franchise, List<ShopResDto> shops) {
        FranchiseResDto dto = new FranchiseResDto();
        dto.franchise_type = franchise.getFranchise_type();
        dto.franchise_name = franchise.getFranchise_name();
        dto.franchise_shop = shops;
        return new FranchiseResDto(franchise);
    }
}
