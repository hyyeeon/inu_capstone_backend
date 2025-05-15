package com.capstone.startmap.domain.result.api.dto;

import com.capstone.startmap.domain.building.Building;
import com.capstone.startmap.domain.franchise.Franchise;
import com.capstone.startmap.domain.result.Result;
import com.capstone.startmap.domain.result.ResultType;
import com.capstone.startmap.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResultCreateDto {
    private ResultType type;

    private LocalDate date;

    private User user;

    private Franchise franchise;

    private Building building;

    public Result toEntity(){
        return Result.builder()
                .type(this.type)
                .date(this.date)
                .user(this.user)
                .franchise(this.franchise)
                .building(this.building)
                .build();
    }
}
