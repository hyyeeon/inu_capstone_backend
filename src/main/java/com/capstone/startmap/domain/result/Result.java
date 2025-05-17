package com.capstone.startmap.domain.result;

import com.capstone.startmap.domain.building.Building;
import com.capstone.startmap.domain.franchise.Franchise;
import com.capstone.startmap.domain.franchise_result.FranchiseResult;
import com.capstone.startmap.domain.location_result.LocationResult;
import com.capstone.startmap.domain.result.api.dto.ResultResDto;
import jakarta.persistence.*;
import lombok.*;
import com.capstone.startmap.domain.user.User;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "result_id", nullable = false)
    private Long resultId;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private ResultType type;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "franchise_id")
    private Franchise franchise;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "building_id")
    private Building building;

    @OneToMany(mappedBy = "result", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LocationResult> location_results;

    @OneToMany(mappedBy = "result", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FranchiseResult> franchise_results;

    public ResultResDto toDto(ResultType type){
        if (type == ResultType.LOCATION){
            return ResultResDto.builder()
                    .result_id(this.resultId)
                    .date(this.date)
                    .building_id(this.building.getBuilding_id())
                    .franchise_id(null)
                    .build();
        } else {
            return ResultResDto.builder()
                    .result_id(this.resultId)
                    .date(this.date)
                    .franchise_id(this.franchise.getFranchise_id())
                    .building_id(null)
                    .build();
        }
    }
}
