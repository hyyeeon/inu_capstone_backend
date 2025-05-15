package com.capstone.startmap.config.filereader;

import com.capstone.startmap.domain.building.repository.BuildingRepository;
import com.capstone.startmap.domain.franchise.repository.FranchiseRepository;
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
public class CSVShopWriter implements ItemWriter<ShopDtoCSV> {

    private final ShopRepository shopRepository;
    private final BuildingRepository buildingRepository;
    private final FranchiseRepository franchiseRepository;

    @Override
    public void write(Chunk<? extends ShopDtoCSV> chunk) throws Exception {
        log.info("파일쓰기시작");
        List<Shop> shopList = new ArrayList<>();

        chunk.forEach(getShopDtoCSV -> {
            Shop shop = getShopDtoCSV.toEntity(buildingRepository, franchiseRepository);
            if (!shopRepository.existsByShopName(shop.getShopName())) {
                shopList.add(shop);
            }
        });

        shopRepository.saveAll(shopList);
    }
}
