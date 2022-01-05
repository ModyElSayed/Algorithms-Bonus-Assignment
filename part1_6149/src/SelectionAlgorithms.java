public class SelectionAlgorithms {
    public static void main(String[] args) {

        // Test the 3rd method of the k-th smallest number
        NaiveSelection kthElement = new NaiveSelection(10, 10, 100);
        kthElement.printNumbers();
        System.out.println(kthElement.getKthElement(5));
    }
}
