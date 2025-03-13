package com.capstone.startmap.domain.location_result;

import com.capstone.startmap.domain.building.Building;
import com.capstone.startmap.domain.result.Result;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Location_result {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="search_id", nullable=false)
    private Long searchId;

    @Column(name = "expected_sales", nullable = false)
    private Long expectedSales;

    @Column(name = "success_rate", nullable = false)
    private Float successRate;

    @Column(name="rank", nullable = false)
    private Integer rank;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "result_id")
    private Result result_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "building_id")
    private Building building_id;

}
