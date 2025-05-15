package com.capstone.startmap.domain.shop.dto;

import com.capstone.startmap.domain.building.Building;
import com.capstone.startmap.domain.building.repository.BuildingRepository;
import com.capstone.startmap.domain.franchise.Franchise;
import com.capstone.startmap.domain.franchise.repository.FranchiseRepository;
import com.capstone.startmap.domain.shop.Shop;
import com.capstone.startmap.domain.shop.repository.ShopRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.*;
import com.capstone.startmap.domain.shop.repository.ShopRepository;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShopDtoCSV {

    private String shop_name;

    private String shop_address;

    private Long building_id;

    private Long franchise_id;

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

    public Shop toEntity(BuildingRepository buildingRepository, FranchiseRepository franchiseRepository) {
        Building building = null;
        Franchise franchise = null;
        if (this.building_id != null) {
            building = buildingRepository.findById(this.building_id)
                    .orElseThrow(() -> new RuntimeException("Building not found"));
        }
        if (this.franchise_id != null) {
            franchise = franchiseRepository.findById(this.franchise_id)
                    .orElseThrow(() -> new RuntimeException("Building not found"));
        }

        return Shop.builder()
                .shopName(this.shop_name)
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
                .shop_address(this.shop_address)
                .buildingId(building)
                .franchiseId(franchise)
                .build();
    }
}
