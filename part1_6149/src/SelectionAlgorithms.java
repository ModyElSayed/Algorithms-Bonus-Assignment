
import java.util.Random;

 class Rand_DAC_Median {
    private int[] arr;
    private int p, r, mid;
    private Random randnum;
    private int size;

    public Rand_DAC_Median() {

        this.randnum = new Random();
        this.size = this.randnum.nextInt(100, 10000000);
        this.arr = new int[this.size];
        for (int i = 0; i < this.size - 1; i++) {
            this.randnum = new Random();
            int temp = randnum.nextInt(100, 1000);
            arr[i] = temp;
        }


    }

    public void quicksort(Integer[] arr, int low, int high) {
        if (low < high) {
            mid = randPartition(arr, low, high);
            quicksort(arr, low, mid - 1);
            quicksort(arr, mid + 1, high);
        }
    }

    public int randPartition(Integer[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;
        for (int j = low; j < high - 1; j++) {
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

    public int randSelect(Integer[] arr, int low, int high, int mid) {


        int pos = randPartition(arr, low, high);
        if (pos - low == mid - 1) {
            return arr[pos];
        }
        if (pos - low > mid - 1) {
            return randSelect(arr, low, pos - 1, mid);
        } else return randSelect(arr, pos + 1, high, mid - pos + low - 1);
    }
}


public class SelectionAlgorithms {
    public static void main(String[] args) {

        // Test the 3rd method of the k-th smallest number
        NaiveSelection kthElement = new NaiveSelection(10, 100);
        kthElement.printNumbers();

        Rand_DAC_Median rand =new Rand_DAC_Median();

        System.out.println(kthElement.getKthElement(5));

        kthElement.updateLimits(200, 1000);
        System.out.println(kthElement.getKthElement(4));
    }
}
