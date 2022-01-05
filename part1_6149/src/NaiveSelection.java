import java.util.Arrays;
import java.util.Random;

// The 3rd method of the k-th smallest number.
public class NaiveSelection {
    private final Random randomNumbers;
    private int numOfElements;
    private int[] numbers;
    private int minBound;
    private int maxBound;

    public NaiveSelection(int numOfElements, int minBound, int maxBound) {
        setNumOfElements(numOfElements);
        setLimits(minBound, maxBound);

        this.randomNumbers = new Random();
        this.numbers = new int[this.numOfElements];

        setNumbers();
    }

    private void setNumOfElements(int numOfElements) {
        if (numOfElements > 0) {
            this.numOfElements = numOfElements;
        } else {
            throw new IllegalArgumentException("Illegal number of elements: " + numOfElements);
        }
    }

    private void setLimits(int minBound, int maxBound) {
        this.minBound = minBound;
        this.maxBound = maxBound;
    }

    private void setNumbers() {
        for (int index = 0; index < this.numOfElements; index++) {
            numbers[index] = randomNumbers.nextInt(this.minBound, this.maxBound);
        }
        Arrays.sort(this.numbers);
    }

    public void updateLimits(int minBound, int maxBound) {
        setLimits(minBound, maxBound);
        setNumbers();
    }

    public int getKthElement(int kthElement) {
        if (kthElement > 0) {
            return numbers[kthElement - 1];
        } else {
            throw new IllegalArgumentException("Illegal index of the k-th element: " + kthElement);
        }
    }

    public void printNumbers() {
        System.out.println();
        for (int index = 0; index < this.numOfElements; index++) {
            System.out.println(numbers[index]);
        }
        System.out.println();
    }

}
