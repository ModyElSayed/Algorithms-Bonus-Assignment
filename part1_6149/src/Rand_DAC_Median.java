import java.util.Arrays;
import java.util.Random;

public class Rand_DAC_Median {
    private final int[] arr;
    private final int size;
    private final Random randNum;

    public Rand_DAC_Median() {
        randNum = new Random();
        this.size = randNum.nextInt(10, 15);
        this.arr = new int[this.size];
        setArr();
    }

    private void setArr() {
        for (int i = 0; i < this.size; i++) {
            arr[i] = randNum.nextInt(100, 1000);
        }
    }

    private int randPartition(int[] arr, int low, int high) {
        if (low < high) {
            int random = randNum.nextInt(low, high);
            swap(arr, random, high);
        }
        return partition(arr, low, high);
    }

    private void swap(int[] arr, int first, int second) {
        int temp = arr[first];
        arr[first] = arr[second];
        arr[second] = temp;
    }

    private int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, high);
        return i + 1;
    }

    public int randSelect() {
        int median;
        if (this.size % 2 == 0) {
            median = this.size / 2;
        } else {
            median = this.size / 2 + 1;
        }
        return randSelect(arr, 0, this.size - 1, median);
    }

    private int randSelect(int[] arr, int low, int high, int mid) {

        if (low == high) {
            return arr[low];
        }

        int pos = randPartition(arr, low, high);
        int currentPosition = pos - low + 1;

        if (currentPosition == mid) {
            return arr[pos];
        } else if (currentPosition > mid) {
            return randSelect(arr, low, pos - 1, mid);
        } else  {
            return randSelect(arr, pos + 1, high, mid - currentPosition);
        }
    }

    public void correctMedian() {
        int temp;
        if (this.size % 2 == 1) {
            temp = this.size / 2 + 1;
        } else {
            temp = this.size / 2;
        }
        Arrays.sort(arr);
        System.out.println("Sorted: " + arr[temp - 1]);
        System.out.println("temp: " + temp);
        for (int index = 0; index < this.size; index++) {
            System.out.print((index + 1) + ":" + arr[index] + " ");
        }
        System.out.println();
    }
}


