package com.capstone.startmap.domain.franchise.api.dto;

import com.capstone.startmap.domain.shop.api.dto.ShopResDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FranchiseDto {
    @Schema(description = "프랜차이즈 id")
    private Long franchise_id;

    @Schema(description = "프랜차이즈 이름")
    private String franchise_name;
}
