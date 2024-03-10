package factory;

import container.Container;
import container.QueueContainer;
import container.StackContainer;
import container.StrategyContainer;

public final class TaskContainerFactory implements Factory {

    public static final TaskContainerFactory instance;
    /**
     * Initializare Singleton
     * Se creeaza o singura instanta
     * */
    private TaskContainerFactory(){ }

    static {
        instance = new TaskContainerFactory();
    }

    public static TaskContainerFactory getInstance(){
        return instance;
    }
    /**
     * Initializarea unui container, dupa o anumita strategie
     * param: strategy -stretegia dupa care se creeaza containerul */
    @Override
    public Container createContainer(StrategyContainer strategy) {
        if(strategy == StrategyContainer.LIFO){
            return new StackContainer();
        } else if (strategy == StrategyContainer.FIFO) {
            return new QueueContainer();
        }
        return null;
    }
}
