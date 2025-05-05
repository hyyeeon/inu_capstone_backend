package com.capstone.startmap.domain.town.service;

import com.capstone.startmap.domain.town.Town;
import com.capstone.startmap.domain.town.api.dto.TownResDto;
import com.capstone.startmap.domain.town.repository.TownRepository;
import com.capstone.startmap.exception.town.NotFoundTownException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TownService {
    private final TownRepository townRepository;

    public TownResDto showTown(Long town_id) {
        Town town = townRepository.findById(town_id).orElseThrow(()->
                new NotFoundTownException("존재하지 않는 동입니다."));
        return TownResDto.fromTown(town);
    }
}
