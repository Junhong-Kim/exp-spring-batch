package kim.junhong.expspringbatch.job.chunks;

import kim.junhong.expspringbatch.dto.ProductVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ItemReader;

import java.util.Random;

@Slf4j
public class ProductChunkReader implements ItemReader<ProductVo>, StepExecutionListener {

    @Override
    public void beforeStep(StepExecution stepExecution) {
        log.info("reader initialized.");
    }

    @Override
    public ProductVo read() {
        Random random = new Random();
        int i = random.nextInt(10);

        if (i == 0) {
            return null;
        } else {
            return new ProductVo("new" + i);
        }
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        log.info("reader ended.");
        return ExitStatus.COMPLETED;
    }
}
