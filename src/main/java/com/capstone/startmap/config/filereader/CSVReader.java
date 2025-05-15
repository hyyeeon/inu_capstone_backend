package com.capstone.startmap.config.filereader;

import com.capstone.startmap.domain.building.dto.BuildingDtoCSV;
import com.capstone.startmap.domain.shop.dto.ShopDtoCSV;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class CSVReader {
    @Bean
    public FlatFileItemReader<ShopDtoCSV> csvShopReader(){
        log.info("파일읽기 시작");
        /* 파일읽기 */
        FlatFileItemReader<ShopDtoCSV> flatFileItemReader = new FlatFileItemReader<>();
        //flatFileItemReader.setResource(new ClassPathResource("csv/cafe_pack.csv"));
        flatFileItemReader.setEncoding("UTF-8"); //인코딩 설정
        flatFileItemReader.setLinesToSkip(1);//첫줄 헤더 스킵

        /* defaultLineMapper: 읽으려는 데이터 LineMapper을 통해 Dto로 매핑 */
        DefaultLineMapper<ShopDtoCSV> defaultLineMapper = new DefaultLineMapper<>();

        /* delimitedLineTokenizer : csv 파일에서 구분자 지정하고 구분한 데이터 setNames를 통해 각 이름 설정 */
        DelimitedLineTokenizer delimitedLineTokenizer = new DelimitedLineTokenizer(","); //csv 파일에서 구분자
        delimitedLineTokenizer.setNames("shop_name", "shop_address", "building_id", "franchise_id", "shop_sales", "building_sales",
                "area_sales", "resident_population", "single_household", "subway_users", "similar_businesses",
                "distance_same_franchise", "nearby_schools", "nearby_tourist_attractions", "nearby_buildings"); //행으로 읽은 데이터 매칭할 데이터 각 이름
        defaultLineMapper.setLineTokenizer(delimitedLineTokenizer); //lineTokenizer 설정


        /* beanWrapperFieldSetMapper: 매칭할 class 타입 지정 */
        BeanWrapperFieldSetMapper<ShopDtoCSV> beanWrapperFieldSetMapper = new BeanWrapperFieldSetMapper<>();
        beanWrapperFieldSetMapper.setTargetType(ShopDtoCSV.class);

        defaultLineMapper.setFieldSetMapper(beanWrapperFieldSetMapper); //fieldSetMapper 지정

        flatFileItemReader.setLineMapper(defaultLineMapper); //lineMapper 지정

        return flatFileItemReader;
    }

    @Bean
    public FlatFileItemReader<BuildingDtoCSV> csvBuildingReader(){
        log.info("파일읽기 시작");
        /* 파일읽기 */
        FlatFileItemReader<BuildingDtoCSV> flatFileItemReader = new FlatFileItemReader<>();
        flatFileItemReader.setResource(new ClassPathResource("csv/songdo_1.csv"));
        flatFileItemReader.setEncoding("UTF-8"); //인코딩 설정
        flatFileItemReader.setLinesToSkip(1);//첫줄 헤더 스킵

        /* defaultLineMapper: 읽으려는 데이터 LineMapper을 통해 Dto로 매핑 */
        DefaultLineMapper<BuildingDtoCSV> defaultLineMapper = new DefaultLineMapper<>();

        /* delimitedLineTokenizer : csv 파일에서 구분자 지정하고 구분한 데이터 setNames를 통해 각 이름 설정 */
        DelimitedLineTokenizer delimitedLineTokenizer = new DelimitedLineTokenizer(","); //csv 파일에서 구분자
        delimitedLineTokenizer.setNames("zone", "address", "building_sales", "area_sales",
                "resident_population", "single_household", "similar_businesses_cafe", "similar_businesses_bakery",
                "subway_name", "subway_users", "nearby_schools", "nearby_tourist_attractions", "nearby_buildings",
                "distance_parisbaguette",  "distance_touslesjours", "distance_dunkin", "distance_theventi", "distance_mega", "distance_pack", "distance_compose",
                "distance_ediya", "distance_twosome"
                ); //행으로 읽은 데이터 매칭할 데이터 각 이름
        defaultLineMapper.setLineTokenizer(delimitedLineTokenizer); //lineTokenizer 설정

        /* beanWrapperFieldSetMapper: 매칭할 class 타입 지정 */
        BeanWrapperFieldSetMapper<BuildingDtoCSV> beanWrapperFieldSetMapper = new BeanWrapperFieldSetMapper<>();
        beanWrapperFieldSetMapper.setTargetType(BuildingDtoCSV.class);

        defaultLineMapper.setFieldSetMapper(beanWrapperFieldSetMapper); //fieldSetMapper 지정

        flatFileItemReader.setLineMapper(defaultLineMapper); //lineMapper 지정

        return flatFileItemReader;
    }
}
