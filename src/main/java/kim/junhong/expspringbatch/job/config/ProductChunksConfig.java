package kim.junhong.expspringbatch.job.config;

import kim.junhong.expspringbatch.dto.ProductVo;
import kim.junhong.expspringbatch.job.chunks.ProductChunkProcessor;
import kim.junhong.expspringbatch.job.chunks.ProductChunkReader;
import kim.junhong.expspringbatch.job.chunks.ProductChunkWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class ProductChunksConfig {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job chunkJob() {
        return jobBuilderFactory
                .get("chunkJob")
                .start(chunkStep())
                .build();
    }

    @Bean
    public Step chunkStep() {
        return stepBuilderFactory.get("chunkStep")
                .<ProductVo, ProductVo>chunk(1)
                .reader(chunkReader())
                .processor(chunkProcessor())
                .writer(chunkWriter())
                .build();
    }

    @Bean
    public ItemReader<ProductVo> chunkReader() {
        return new ProductChunkReader();
    }

    @Bean
    public ItemProcessor<ProductVo, ProductVo> chunkProcessor() {
        return new ProductChunkProcessor();
    }

    @Bean
    public ItemWriter<ProductVo> chunkWriter() {
        return new ProductChunkWriter();
    }
}
