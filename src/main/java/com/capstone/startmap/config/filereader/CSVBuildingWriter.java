package com.capstone.startmap.config.filereader;

import com.capstone.startmap.domain.building.Building;
import com.capstone.startmap.domain.building.dto.BuildingDtoCSV;
import com.capstone.startmap.domain.building.repository.BuildingRepository;
import com.capstone.startmap.domain.shop.Shop;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class CSVBuildingWriter implements ItemWriter<BuildingDtoCSV> {

    private final BuildingRepository buildingRepository;

    @Override
    public void write(Chunk<? extends BuildingDtoCSV> chunk) throws Exception {
        log.info("파일쓰기시작");

        List<Building> buildingList = new ArrayList<>();

        chunk.forEach(getBuildingDtoCSV -> {
            Building building = getBuildingDtoCSV.toEntity();
            if (!buildingRepository.existsByZone(building.getZone())) {
                buildingList.add(building);
            }
        });

        buildingRepository.saveAll(buildingList);
    }
}
