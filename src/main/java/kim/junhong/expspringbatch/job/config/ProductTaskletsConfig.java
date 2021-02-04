package kim.junhong.expspringbatch.job.config;

import kim.junhong.expspringbatch.job.tasklets.ProductProcessor;
import kim.junhong.expspringbatch.job.tasklets.ProductReader;
import kim.junhong.expspringbatch.job.tasklets.ProductWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class ProductTaskletsConfig {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job taskletsJob() {
        return jobBuilderFactory
                .get("taskletsJob")
                .start(readProducts())
                .next(processProducts())
                .next(writeProducts())
                .build();
    }

    @Bean
    protected Step readProducts() {
        return stepBuilderFactory.get("readProducts")
                .tasklet(productReader())
                .build();
    }

    @Bean
    protected Step processProducts() {
        return stepBuilderFactory.get("processProducts")
                .tasklet(productProcessor())
                .build();
    }

    @Bean
    protected Step writeProducts() {
        return stepBuilderFactory.get("writeProducts")
                .tasklet(productWriter())
                .build();
    }

    @Bean
    public ProductReader productReader() {
        return new ProductReader();
    }

    @Bean
    public ProductProcessor productProcessor() {
        return new ProductProcessor();
    }

    @Bean
    public ProductWriter productWriter() {
        return new ProductWriter();
    }
}
