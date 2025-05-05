package com.capstone.startmap.domain.franchise.service;

import com.capstone.startmap.domain.franchise.Franchise;
import com.capstone.startmap.domain.franchise.api.dto.FranchiseResDto;
import com.capstone.startmap.domain.franchise.repository.FranchiseRepository;
import com.capstone.startmap.exception.franchise.NotFoundFranchiseException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor

public class FranchiseService {
    private FranchiseRepository franchiseRepository;

    public FranchiseResDto showFranchise(Long franchise_id) {
        Franchise franchise = franchiseRepository.findById(franchise_id).orElseThrow(()->
                new NotFoundFranchiseException("존재하지 않는 프랜차이즈입니다."));
        return FranchiseResDto.fromFranchise(franchise);
    }
    public List<FranchiseResDto> showAllFranchises() {
        List<Franchise> franchises = franchiseRepository.findAll();

        return franchises.stream()
                .map(FranchiseResDto::fromFranchise)  // fromFranchise 메서드를 그대로 활용
                .collect(Collectors.toList());
    }
}
