package sort;

public class QuickSort extends AbstractSorter{

    private void swap(int arr[], int low, int pivot){
        int aux = arr[low];
        arr[low] = arr[pivot];
        arr[pivot] = aux;
    }
    private int partition(int arr[], int low, int high){
        int p = low, j;
        for(j = low + 1; j <= high; j++){
            if(arr[j] < arr[low]){
                swap(arr, ++p, j);
            }
        }
        swap(arr, low, p);
        return p;
    }

    private void quickSort(int arr[], int low, int high){
        if(low < high){
            int p = partition(arr, low, high);
            quickSort(arr, low, p - 1);
            quickSort(arr, p + 1, high);
        }
    }

    @Override
    public void sort(int[] arr){
        quickSort(arr, 0, arr.length - 1);
    }
}
