package runner;

import container.Container;
import container.StrategyContainer;
import factory.TaskContainerFactory;
import model.Task;

public class StrategyTaskRunner implements TaskRunner{
    protected Container container;
    /**
     * Initializarea unui container dupa o strategie
     * param: strategy-strategia dupa care se initializeazaz containerul*/
    public StrategyTaskRunner(StrategyContainer strategy){
        this.container = TaskContainerFactory.getInstance().createContainer(strategy);
    }
    /**
     * Se executa unul din task-uri, adica se elimina din lista*/
    @Override
    public void executeOneTask() {
        if(!container.isEmpty()){
            container.remove().execute();
        }
    }
    /**
     * Se finalizeaza toate task-urile*/
    @Override
    public void executeAll() {
        while (!container.isEmpty())
            executeOneTask();
    }
    /**
     *  Se adauga un task nou
     *  param: task-noul task ce se adauga*/
    @Override
    public void addTask(Task task) {
        container.add(task);
    }
    /**
     *  Se verifica daca exista task-uri*/
    @Override
    public boolean hasTask() {
        return !container.isEmpty();
    }
}
