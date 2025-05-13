package com.capstone.startmap.domain.ai.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "매출 예상 요청")
public class PredictRequestDto {
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

    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("building_sales", building_sales);
        map.put("area_sales", area_sales);
        map.put("resident_population", resident_population);
        map.put("single_household", single_household);
        map.put("subway_users", subway_users);
        map.put("similar_businesses", similar_businesses);
        map.put("distance_same_franchise", distance_same_franchise);
        map.put("nearby_schools", nearby_schools);
        map.put("nearby_tourist_attractions", nearby_tourist_attractions);
        map.put("nearby_buildings", nearby_buildings);
        return map;
    }
}
