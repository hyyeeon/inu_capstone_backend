package com.capstone.startmap.domain.ai.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
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
//        map.put("building_sales", building_sales);
//        map.put("area_sales", area_sales);
//        map.put("resident_population", resident_population);
//        map.put("single_household", single_household);
//        map.put("subway_users", subway_users);
//        map.put("similar_businesses", similar_businesses);
//        map.put("distance_same_franchise", distance_same_franchise);
//        map.put("nearby_schools", nearby_schools);
//        map.put("nearby_tourist_attractions", nearby_tourist_attractions);
//        map.put("nearby_buildings", nearby_buildings);
        map.put("상가매출", building_sales);
        map.put("상권매출", area_sales);
        map.put("거주인구", resident_population);
        map.put("가구1인", single_household);
        map.put("주변전철역이용인구수", subway_users);
        map.put("동종업계개수", similar_businesses);
        map.put("동일프랜차이즈와의최단거리", distance_same_franchise);
        map.put("주변학교개수", nearby_schools);
        map.put("주변관광지유무", nearby_tourist_attractions);
        map.put("주변빌딩개수", nearby_buildings);
        //     "상가매출": 21000,
//     "상권매출": 19000,
//     "거주인구": 19588,
//     "가구1인": 32.3,
//     "주변전철역이용인구수": 0,
//     "동종업계개수": 23,
//     "동일프랜차이즈와의최단거리": 370,
//     "주변학교개수": 0,
//     "주변관광지유무": 0,
//     "주변빌딩개수": 6
        return map;
    }
}
