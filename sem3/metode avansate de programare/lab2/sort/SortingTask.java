package sort;

import model.Task;

public class SortingTask extends Task {

    private int[] array;
    private AbstractSorter sorter;

    public SortingTask(String taskId, String description, int[] arr, Strategy sg) {
        super(taskId, description);
        this.array = arr;
        if(sg == Strategy.Bubble_Sort)
            this.sorter = new BubbleSort();
        else if (sg == Strategy.Quick_Sort)
            this.sorter = new QuickSort();

    }

    public int[] getArray() {
        return array;
    }

    public void setArray(int[] array) {
        this.array = array;
    }

    @Override
    public void execute() {

        System.out.println("Vectorul nesortat: ");
        String string = "";
        for(int i = 0; i < this.array.length; i++)
            string = string + Integer.toString(this.array[i]) + " ";
        System.out.println(string);

        System.out.println("Vectorul sortat: ");
        String string1 = "";
        this.sorter.sort(this.array);
        for(int i = 0; i < this.array.length; i++)
            string1 = string1 + Integer.toString(this.array[i]) + " ";
        System.out.println(string1);

    }
}
