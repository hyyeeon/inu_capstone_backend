package com.capstone.startmap.domain.franchise_result.service;

import com.capstone.startmap.domain.franchise_result.Franchise_result;
import com.capstone.startmap.domain.franchise_result.api.dto.FranchiseResultResDto;
import com.capstone.startmap.domain.franchise_result.repository.FranchiseResultRepository;
import com.capstone.startmap.exception.franchise_result.NotFoundFranchiseResultException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class FranchiseResultService {
    private FranchiseResultRepository franchiseResultRepository;

    public FranchiseResultResDto showFranchiseResult(Long search_id) {
        Franchise_result franchise_result = franchiseResultRepository.findById(search_id).orElseThrow(()->
                new NotFoundFranchiseResultException("존재하지 않는 검색결과입니다."));
        return FranchiseResultResDto.fromFranchiseResult(franchise_result);
    }
}
