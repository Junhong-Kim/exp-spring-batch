package kim.junhong.expspringbatch.job.tasklets;

import kim.junhong.expspringbatch.dto.ProductVo;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

import java.util.ArrayList;
import java.util.List;

public class ProductReader implements Tasklet, StepExecutionListener {

    private List<ProductVo> list;

    @Override
    public void beforeStep(StepExecution stepExecution) {
        list = new ArrayList<>();
    }

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        for (int i = 0; i < 10; i++) {
            list.add(new ProductVo(String.valueOf(i)));
        }
        return RepeatStatus.FINISHED;
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        stepExecution
                .getJobExecution()
                .getExecutionContext()
                .put("list", list);
        return ExitStatus.COMPLETED;
    }
}
