package com.capstone.startmap.domain.shop.service;

import com.capstone.startmap.domain.shop.Shop;
import com.capstone.startmap.domain.shop.api.dto.ShopResDto;
import com.capstone.startmap.domain.shop.repository.ShopRepository;
import com.capstone.startmap.exception.shop.NotFoundShopException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class ShopService {
    private final ShopRepository shopRepository;

    public ShopResDto showShop(Long shop_id) {
        Shop shop = shopRepository.findById(shop_id).orElseThrow(()->
                new NotFoundShopException("존재 하지 않는 매장입니다."));
        return ShopResDto.fromShop(shop);
    }
}
