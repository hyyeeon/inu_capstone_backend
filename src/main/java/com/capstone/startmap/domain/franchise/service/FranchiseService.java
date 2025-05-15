package com.capstone.startmap.domain.franchise.service;

import com.capstone.startmap.domain.franchise.Franchise;
import com.capstone.startmap.domain.franchise.api.dto.FranchiseDto;
import com.capstone.startmap.domain.franchise.api.dto.FranchiseResDto;
import com.capstone.startmap.domain.franchise.repository.FranchiseRepository;
import com.capstone.startmap.domain.shop.Shop;
import com.capstone.startmap.domain.shop.api.dto.ShopResDto;
import com.capstone.startmap.domain.shop.repository.ShopRepository;
import com.capstone.startmap.exception.franchise.NotFoundFranchiseException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FranchiseService {
    private final FranchiseRepository franchiseRepository;
    private final ShopRepository shopRepository;

    public FranchiseResDto showFranchise(Long franchise_id) {
        Franchise franchise = franchiseRepository.findById(franchise_id).orElseThrow(()->
                new NotFoundFranchiseException("존재하지 않는 프랜차이즈입니다."));
        List<Shop> shops = shopRepository.findAllByFranchiseId(franchise);
        List <ShopResDto> shopdtos = shops.stream()
                .map(ShopResDto::fromShop)  // fromFranchise 메서드를 그대로 활용
                .toList();
        return franchise.toDto(shopdtos);//FranchiseResDto.fromFranchise(franchise, shopdtos);
    }

    public List<FranchiseResDto> showAllFranchises() {
        List<Franchise> franchises = franchiseRepository.findAll();
        List<FranchiseResDto> result = new ArrayList<>();
        for (Franchise franchise : franchises) {
            result.add(franchise.toDto());
        }
        return result;
    }

    public List<FranchiseDto> getFranchisesname(List<Long> ids){
        List<FranchiseDto> result = new ArrayList<>();
        for (Long id : ids) {
            Franchise franchise = franchiseRepository.findById(id).orElseThrow(()->
                    new NotFoundFranchiseException("존재하지 않는 프랜차이즈입니다."));
            String name = franchise.getFranchise_type()+"_"+franchise.getFranchise_name();
            System.out.println(name);
            result.add(new FranchiseDto(id, name));
        }
        return result;
    }
    public FranchiseDto getFranchisename(Long id){
        Franchise franchise = franchiseRepository.findById(id).orElseThrow(()->
                new NotFoundFranchiseException("존재하지 않는 프랜차이즈입니다."));
        String name = franchise.getFranchise_type()+"_"+franchise.getFranchise_name();
        return new FranchiseDto(id, name);
    }
    public Franchise findById(Long id){
        return franchiseRepository.findById(id).orElseThrow(()->
                new NotFoundFranchiseException("프랜차이즈가 존재하지 않습니다."));
    }
}
