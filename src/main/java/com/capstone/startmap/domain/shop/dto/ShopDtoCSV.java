package com.capstone.startmap.domain.shop.dto;

import com.capstone.startmap.domain.shop.Shop;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShopDtoCSV {
    private String shop_name;

    private Integer shop_sales;

    private Integer building_sales;

    private Integer area_sales;

    private Integer resident_population;

    private Double single_household;

    private Integer subway_users;

    private Integer similar_businesses;

    private Integer distance_same_franchise;

    private Integer nearby_schools;

    private Integer nearby_tourist_attractions;

    private Integer nearby_buildings;

    public Shop toEntity(){
        return Shop.builder()
                .shop_name(this.shop_name)
                .shop_sales(this.shop_sales)
                .building_sales(this.building_sales)
                .area_sales(this.area_sales)
                .resident_population(this.resident_population)
                .single_household(this.single_household)
                .subway_users(this.subway_users)
                .similar_businesses(this.similar_businesses)
                .distance_same_franchise(this.distance_same_franchise)
                .nearby_buildings(this.nearby_buildings)
                .nearby_schools(this.nearby_schools)
                .nearby_tourist_attractions(this.nearby_tourist_attractions)
                .build();
    }
}
