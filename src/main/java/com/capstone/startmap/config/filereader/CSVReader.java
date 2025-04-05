package com.capstone.startmap.config.filereader;

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
public class CSVReader {//extends Batch<ShopDtoCSV>{
    @Bean
    public FlatFileItemReader<ShopDtoCSV> csvShopReader(){
        log.info("파일읽기 시작");
        /* 파일읽기 */
        FlatFileItemReader<ShopDtoCSV> flatFileItemReader = new FlatFileItemReader<>();
        flatFileItemReader.setResource(new ClassPathResource("csv/cafe_pack.csv"));
        flatFileItemReader.setEncoding("UTF-8"); //인코딩 설정

        /* defaultLineMapper: 읽으려는 데이터 LineMapper을 통해 Dto로 매핑 */
        DefaultLineMapper<ShopDtoCSV> defaultLineMapper = new DefaultLineMapper<>();

        /* delimitedLineTokenizer : csv 파일에서 구분자 지정하고 구분한 데이터 setNames를 통해 각 이름 설정 */
        DelimitedLineTokenizer delimitedLineTokenizer = new DelimitedLineTokenizer(","); //csv 파일에서 구분자
        delimitedLineTokenizer.setNames("shop_name", "shop_sales", "building_sales", "area_sales",
        "resident_population", "single_household", "subway_users", "similar_businesses", "distance_same_franchise",
        "nearby_schools", "nearby_tourist_attractions", "nearby_buildings"); //행으로 읽은 데이터 매칭할 데이터 각 이름
        defaultLineMapper.setLineTokenizer(delimitedLineTokenizer); //lineTokenizer 설정

        /* beanWrapperFieldSetMapper: 매칭할 class 타입 지정 */
        BeanWrapperFieldSetMapper<ShopDtoCSV> beanWrapperFieldSetMapper = new BeanWrapperFieldSetMapper<>();
        beanWrapperFieldSetMapper.setTargetType(ShopDtoCSV.class);

        defaultLineMapper.setFieldSetMapper(beanWrapperFieldSetMapper); //fieldSetMapper 지정

        flatFileItemReader.setLineMapper(defaultLineMapper); //lineMapper 지정

        return flatFileItemReader;

    }
}
