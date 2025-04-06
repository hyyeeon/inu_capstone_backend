package com.capstone.startmap.domain.shop.api.dto;

import com.capstone.startmap.domain.shop.Shop;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ShopResDto {
    private Long shop_id;
    private String shop_name;
    private Long shop_sales;
    private Long franchise_id;
    private Long building_id;

    public ShopResDto(Shop shop) {

    }
}
