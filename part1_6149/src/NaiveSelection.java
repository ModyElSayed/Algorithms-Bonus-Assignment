import java.util.Arrays;
import java.util.Random;

// The 3rd method of the k-th smallest number.
public class NaiveSelection {
    private final Random randomNumbers;
    private final int numOfElements;
    private final int[] numbers;
    private int minBound;
    private int maxBound;//rand num%10^7

    public NaiveSelection(int minBound, int maxBound) {
        setLimits(minBound, maxBound);

        this.randomNumbers = new Random();
        this.numOfElements = randomNumbers.nextInt(1, (int) Math.pow(10, 7));
        this.numbers = new int[this.numOfElements];

        setNumbers();
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
