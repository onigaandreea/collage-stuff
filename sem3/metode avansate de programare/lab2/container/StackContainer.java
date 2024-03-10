package container;

import model.Task;


public class StackContainer extends AbstractContainer implements Container {

    @Override
    public Task remove() {
        return isEmpty() ? null : this.tasks[--this.size];
    }
}
