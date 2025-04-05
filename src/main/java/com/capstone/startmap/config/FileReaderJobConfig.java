package com.capstone.startmap.config;

import com.capstone.startmap.config.filereader.CSVReader;
import com.capstone.startmap.config.filereader.CSVWriter;
import com.capstone.startmap.domain.shop.dto.ShopDtoCSV;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class FileReaderJobConfig {

    private final PlatformTransactionManager platformTransactionManager;
    private final JobRepository jobRepository;
    // 데이터 처리할 row size
    //private static final int chunkSize = 1000;
    @Value(value="1000")
    private Integer chunkSize;
    private final CSVReader reader;
    private final CSVWriter writer;

    // 저장 job
    @Bean
    public Job csvShopJob(Step step){
        log.info("job시작");
        return new JobBuilder("csvShopJob", jobRepository)//jobBuilderFactory.get("csvScheduleJob")
                .start(step)
                .build();
    }

    // 파일 읽고 DB에 쓰는 Step
    @Bean
    public Step csvShopReaderStep(){
        log.info("step시작");
        return new StepBuilder("csvShopReaderStep", jobRepository)//stepBuilderFactory.get("csvScheduleReaderStep")
                //<reader에 넘겨줄 타입, writer에 넙겨줄 타입>
                .<ShopDtoCSV, ShopDtoCSV>chunk(chunkSize,platformTransactionManager)
                .reader(reader.csvShopReader()) //csv 파일 읽고 넘겨줌
                //.writer(processor) //받은 데잍터 DB에 저장
                .writer(writer)
                .build();
    }
}
