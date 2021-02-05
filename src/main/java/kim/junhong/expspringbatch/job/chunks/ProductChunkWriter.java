package kim.junhong.expspringbatch.job.chunks;

import kim.junhong.expspringbatch.dto.ProductVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ItemWriter;

import java.util.List;

@Slf4j
public class ProductChunkWriter implements ItemWriter<ProductVo>, StepExecutionListener {

    @Override
    public void beforeStep(StepExecution stepExecution) {
        log.info("writer initialized.");
    }

    @Override
    public void write(List<? extends ProductVo> productVoList) {
        for (ProductVo productVo : productVoList) {
            log.info("product => " + productVo);
        }
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        log.info("writer ended.");
        return ExitStatus.COMPLETED;
    }
}
