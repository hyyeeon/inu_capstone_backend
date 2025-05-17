package com.capstone.startmap.domain.franchise_result.service;

import com.capstone.startmap.domain.ai.dto.PredictFranchiseResponseDto;
import com.capstone.startmap.domain.franchise_result.FranchiseResult;
import com.capstone.startmap.domain.franchise_result.api.dto.FranchiseResultCreateDto;
import com.capstone.startmap.domain.franchise_result.api.dto.FranchiseResultResDto;
import com.capstone.startmap.domain.franchise_result.repository.FranchiseResultRepository;
import com.capstone.startmap.domain.result.Result;
import com.capstone.startmap.domain.result.repository.ResultRepository;
import com.capstone.startmap.exception.Result.NotFoundResultException;
import com.capstone.startmap.exception.franchise_result.NotFoundFranchiseResultException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class FranchiseResultService {
    private final FranchiseResultRepository franchiseResultRepository;
    private final ResultRepository resultRepository;

    public FranchiseResultResDto showFranchiseResult(Long search_id) {
        Result result = resultRepository.findById(search_id).orElseThrow(()->
                new NotFoundResultException("Result not found"));
        List<FranchiseResult> franchise_result_list = franchiseResultRepository.findAllByResult(result);
        if (franchise_result_list.isEmpty()) {
            throw new NotFoundFranchiseResultException("존재하지 않는 검색 결과 입니다.");
        }

        return FranchiseResultResDto.fromFranchiseResultList(franchise_result_list);
    }

    public Long createFranchiseResult(PredictFranchiseResponseDto predictDto, FranchiseResultCreateDto dto){
        FranchiseResult franchiseResult = predictDto.toEntity(dto);
        franchiseResult = franchiseResultRepository.save(franchiseResult);
        return franchiseResult.getFranchise_result_id();
    }
}
