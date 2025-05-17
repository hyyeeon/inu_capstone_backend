package com.capstone.startmap.domain.franchise_result;

import com.capstone.startmap.domain.ai.dto.PredictFranchiseResponseDto;
import com.capstone.startmap.domain.franchise.Franchise;
import com.capstone.startmap.domain.result.Result;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@AllArgsConstructor
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FranchiseResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "franchise_result_id", nullable=false)
    private Long franchise_result_id;

    @Column(name = "predict_sales", nullable=false)
    private Integer predict_sales;

    @Column(name="`rank`", nullable = false)
    private Integer rank;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "result_id")
    private Result result;

    @Schema(description = "높은 가중치 3개")
    private String importance;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "franchise_id")
    private Franchise franchise;

    public PredictFranchiseResponseDto toDto(){
        return PredictFranchiseResponseDto.builder()
                .franchise_id(this.franchise.getFranchise_id())
                .importance(this.importance)
                .predict_sales(this.predict_sales)
                .rank(this.rank)
                .build();
    }
}
