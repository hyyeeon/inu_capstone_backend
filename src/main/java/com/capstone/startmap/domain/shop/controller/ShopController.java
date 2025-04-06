package com.capstone.startmap.domain.shop.controller;

import com.capstone.startmap.domain.shop.api.dto.ShopResDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class ShopController {
    public ShopResDto showShop(Long shop_id) {
        return null;
    }

}

