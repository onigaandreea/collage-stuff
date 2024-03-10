package run;

import container.StrategyContainer;
import model.MessageTask;
import runner.DelayTaskRunner;
import runner.PrinterTaskRunner;
import runner.StrategyTaskRunner;
import sort.SortingTask;
import sort.Strategy;

import java.time.LocalDateTime;

public class TestRunner {
    private static MessageTask[] getMessages()
    {
        MessageTask messageTask1 = new MessageTask("1", "Seminar","Tema laborator", "Florentin", "Razvan", LocalDateTime.now());
        MessageTask messageTask2 = new MessageTask("2", "Laborator","Solutie", "Razvan", "Florentin", LocalDateTime.now());
        MessageTask messageTask3 = new MessageTask("3", "Nota laborator","10", "Florentin", "Razvan", LocalDateTime.now());

        return new MessageTask[] { messageTask1, messageTask2, messageTask3 };
    }

    public static void Sort()
    {
        int[] v = new int[] {1,3,5,7,9,6,8,4,2};
        SortingTask sorter = new SortingTask("1", "Sortare folosind QuickSort", v, Strategy.Quick_Sort);
        sorter.execute();
    }

    public static void Run(StrategyContainer strategy)
    {
        StrategyTaskRunner runner = new StrategyTaskRunner(strategy);
        PrinterTaskRunner printer = new PrinterTaskRunner(runner);
        DelayTaskRunner delayedPrinter = new DelayTaskRunner(runner);

        MessageTask[] messages = getMessages();
        for (int i = 0; i < getMessages().length; ++i)
        {
            runner.addTask(messages[i]);
        }
        runner.executeAll();

        for (int i = 0; i < getMessages().length; ++i)
        {
            printer.addTask(messages[i]);
        }
        printer.executeAll();

        for (int i = 0; i < getMessages().length; ++i)
        {
            delayedPrinter.addTask(messages[i]);
        }
        delayedPrinter.executeAll();
    }
}
