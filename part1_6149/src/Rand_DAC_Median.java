import java.util.Random;

public class Rand_DAC_Median {
    private final Integer[] arr;
    private final int size;

    public Rand_DAC_Median() {
        Random randNum = new Random();
        this.size = randNum.nextInt(100, 10000000);
        this.arr = new Integer[this.size];
        for (int i = 0; i < this.size - 1; i++) {
            randNum = new Random();
            int temp = randNum.nextInt(100, 1000);
            arr[i] = temp;
        }
    }

    private int randPartition(Integer[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        return i + 1;
    }

    public int randSelect() {
        return randSelect(arr, 0, this.size - 1, (this.size - 1) / 2);
    }

    private int randSelect(Integer[] arr, int low, int high, int mid) {

        int pos = randPartition(arr, low, high);
        if (pos - low == mid - 1) {
            return arr[pos];
        }
        if (pos - low > mid - 1) {
            return randSelect(arr, low, pos - 1, mid);
        } else return randSelect(arr, pos + 1, high, mid - pos + low - 1);
    }
}


