import java.util.HashMap;
import java.util.Map;

public class HuffmanAlgorithm {
    public static void main(String[] args) {

        // Test Huffman Compression.
        // Assuming the received input is HashMap due to its speed.
        HashMap<String, Integer> testData = new HashMap<>(10);
        testData.put("A", 10);
        testData.put("B", 11);
        testData.put("C", 5);
        testData.put("D", 13);
        testData.put("E", 20);

        Huffman huffman = new Huffman(testData);
        HashMap<String, String> compressedData = huffman.compressData();

        for (Map.Entry<String, String> huffmanData : compressedData.entrySet()) {
            System.out.println(huffmanData.getKey() + ": " + huffmanData.getValue());
        }

    }
}
