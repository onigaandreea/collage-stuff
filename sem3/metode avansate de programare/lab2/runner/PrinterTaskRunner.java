package runner;

import model.MessageTask;
import utils.Constants;

import java.time.LocalDateTime;

public class PrinterTaskRunner extends AbstractTaskRunner{
    private void decorateExecuteOneTask()
    {
        System.out.println("Task executat la " + LocalDateTime.now().format(Constants.DATE_TIME_FORMATTER));
    }

    public PrinterTaskRunner(TaskRunner runner)
    {
        super(runner);
    }

    @Override
    public void executeOneTask()
    {
        runner.executeOneTask();
        this.decorateExecuteOneTask();
    }
}
