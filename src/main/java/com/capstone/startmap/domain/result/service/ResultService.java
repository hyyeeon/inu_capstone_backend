package com.capstone.startmap.domain.result.service;

import com.capstone.startmap.domain.result.Result;
import com.capstone.startmap.domain.result.api.dto.ResultResDto;
import com.capstone.startmap.domain.result.repository.ResultRepository;
import com.capstone.startmap.exception.Result.NotFoundResultException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ResultService {
    private final ResultRepository resultRepository;

    public ResultResDto showResult(Long shop_id) {
        Result result = resultRepository.findById(shop_id).orElseThrow(()->
                new NotFoundResultException("존재하지 않는 검색 결과 입니다."));
        return ResultResDto.fromResult(result);
    }
}
