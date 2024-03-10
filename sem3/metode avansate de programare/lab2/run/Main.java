package run;

import container.StrategyContainer;

public class Main {
    public static void main(String[] args) {

        StrategyContainer strategy;

        if (args.length < 1)
            return;

        if (args[0].compareTo("FIFO") == 0) {
            strategy = StrategyContainer.FIFO;
            TestRunner.Run(strategy);
            TestRunner.Sort();
        } else if (args[0].compareTo("LIFO") == 0) {
            strategy = StrategyContainer.LIFO;
            TestRunner.Run(strategy);
            TestRunner.Sort();
        }
    }
}