package com.capstone.startmap.domain.franchise_result.service;

import com.capstone.startmap.domain.franchise_result.Franchise_result;
import com.capstone.startmap.domain.franchise_result.api.dto.FranchiseResultResDto;
import com.capstone.startmap.domain.franchise_result.repository.FranchiseResultRepository;
import com.capstone.startmap.exception.franchise_result.NotFoundFranchiseResultException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class FranchiseResultService {
    private FranchiseResultRepository franchiseResultRepository;

    public FranchiseResultResDto showFranchiseResult(Long search_id) {
        List<Franchise_result> franchise_result_list = franchiseResultRepository.findAllBySearchId(search_id);
        if (franchise_result_list.isEmpty()) {
            throw new NotFoundFranchiseResultException("존재하지 않는 검색 결과 입니다.");
        }

        return FranchiseResultResDto.fromFranchiseResultList(franchise_result_list);
    }
}
