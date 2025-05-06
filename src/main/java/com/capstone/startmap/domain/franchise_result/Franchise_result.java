package com.capstone.startmap.domain.franchise_result;


import com.capstone.startmap.domain.building.Building;
import com.capstone.startmap.domain.franchise.Franchise;
import com.capstone.startmap.domain.result.Result;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Franchise_result {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "search_id", nullable=false)
    private Long search_id;

    @Column(name = "expected_sales", nullable=false)
    private Long expected_sales;

    @Column(name = "success_rate", nullable=false)
    private Float success_rate;

    @Column(name = "rank", nullable = false)
    private Integer rank;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "result")
    private Result result;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "franchise_id")
    private Franchise franchise_id;


}
