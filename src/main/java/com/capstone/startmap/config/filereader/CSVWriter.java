package com.capstone.startmap.config.filereader;

import com.capstone.startmap.domain.shop.Shop;
import com.capstone.startmap.domain.shop.dto.ShopDtoCSV;
import com.capstone.startmap.domain.shop.repository.ShopRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class CSVWriter implements ItemWriter<ShopDtoCSV> {

    private final ShopRepository shopRepository;

    @Override
    public void write(Chunk<? extends ShopDtoCSV> chunk) throws Exception {
        log.info("파일쓰기시작");
        List<Shop> shopList = new ArrayList<>();

        chunk.forEach(getShopDtoCSV -> {
            Shop shop = getShopDtoCSV.toEntity();
            shopList.add(shop);
        });

        shopRepository.saveAll(shopList);
    }
}
