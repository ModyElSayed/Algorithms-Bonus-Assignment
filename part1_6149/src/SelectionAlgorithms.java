public class SelectionAlgorithms {
    public static void main(String[] args) {

        // Test the 3rd method of the k-th smallest number
        NaiveSelection kthElement = new NaiveSelection(10, 100);
        kthElement.printNumbers();
        System.out.println(kthElement.getKthElement(5));

        kthElement.updateLimits(200, 1000);
        System.out.println(kthElement.getKthElement(4));
    }
}
