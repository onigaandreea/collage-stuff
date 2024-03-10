package container;

import model.Task;

import static utils.Constants.INITIAL_CONTAINER_SIZE;

public abstract class AbstractContainer implements Container{

    protected Task[] tasks;
    protected int size;
    /** Constructorul clasei
     * Se initializeaza o lista vida de task-uri
     * INITIAL_CONTAINER_SIZE capacitatea initiala a listei
     * */
    public AbstractContainer() {
        this.tasks = new Task[INITIAL_CONTAINER_SIZE];
        this.size = 0;
    }
    /**Se face adaugarea unui nou element in lista
     * param: task -elementul de tip Task care se adauga
     * */
    @Override
    public void add(Task task) {
        if(tasks.length==size) {
            Task[] t=new Task[tasks.length * 2];
            System.arraycopy(tasks, 0, t, 0, tasks.length);
            tasks = t;
        }
        tasks[size] = task;
        size++;
    }
    /**Se returneaza lungimea listei
     */
    @Override
    public int size() {
        return size;
    }
    /**Verificare lista vida
     * Returneaza True daca lista este vida si False altfel */
    @Override
    public boolean isEmpty() {
        return size==0;
    }
}
