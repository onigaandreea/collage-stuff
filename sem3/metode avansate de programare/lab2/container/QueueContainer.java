package container;

import model.Task;

public class QueueContainer extends AbstractContainer implements Container{
    /** Implementarea metodei de stergere specifica pentru container-ul QUEUE
     * Se sterge primul element prin mutarea tuturor celorlalte elemente o pozitie spre stanga
     * */
    @Override
    public Task remove() {

        if(this.isEmpty())
            return null;
        Task removed = tasks[0];

        for(int i = 0; i < this.size; i++){
            this.tasks[i] = this.tasks[i + 1];
        }
        size--;
        return removed;
    }
}
