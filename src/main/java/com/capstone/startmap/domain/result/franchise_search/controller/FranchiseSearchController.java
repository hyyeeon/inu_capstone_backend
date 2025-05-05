package com.capstone.startmap.domain.result.franchise_search.controller;

import com.capstone.startmap.domain.franchise.api.dto.FranchiseResDto;
import com.capstone.startmap.domain.result.franchise_search.api.dto.FranchiseSearchResDto;
import com.capstone.startmap.domain.result.franchise_search.service.FranchiseSearchService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class FranchiseSearchController {
    private final FranchiseSearchService franchiseSearchService;
    public FranchiseSearchController(FranchiseSearchService franchiseSearchService) { this.franchiseSearchService = franchiseSearchService; }

    @GetMapping("/mypage/franchise/{userId}")
    public ResponseEntity<List<FranchiseSearchResDto>> getFranchise(@PathVariable long userId) {
        List<FranchiseSearchResDto> franchiseSearchList = franchiseSearchService.showFranchiseSearch(userId);

        return new ResponseEntity<>(franchiseSearchList, HttpStatus.OK);
    }
}
