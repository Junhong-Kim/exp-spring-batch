package kim.junhong.expspringbatch.job.chunks;

import kim.junhong.expspringbatch.dto.ProductVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ItemProcessor;

@Slf4j
public class ProductChunkProcessor implements ItemProcessor<ProductVo, ProductVo>, StepExecutionListener {
    @Override
    public void beforeStep(StepExecution stepExecution) {
        log.info("processor initialized.");
    }

    @Override
    public ProductVo process(ProductVo item) {
        item.setName(item.getName() + "00");
        return item;
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        log.info("processor ended.");
        return ExitStatus.COMPLETED;
    }
}
