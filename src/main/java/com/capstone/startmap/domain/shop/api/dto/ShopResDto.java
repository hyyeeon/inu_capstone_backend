package com.capstone.startmap.domain.shop.api.dto;

import com.capstone.startmap.domain.shop.Shop;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ShopResDto {

    @Schema(description = "매장명")
    private String shop_name;

    @Schema(description = "매장 매출액")
    private Integer shop_sales;

    @Schema(description = "프랜차이즈 id")
    private Long franchise_id;

    @Schema(description = "상가 id")
    private Long building_id;

    public ShopResDto(Shop shop) {

    }

    public static ShopResDto fromShop(Shop shop) {
        ShopResDto dto = new ShopResDto(shop);
        dto.building_id = null;
        dto.franchise_id = null;
        dto.shop_name = shop.getShopName();
        dto.shop_sales = shop.getShop_sales();

        return dto;
    }
}
