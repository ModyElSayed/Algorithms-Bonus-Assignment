import java.io.*;
import java.util.BitSet;
import java.util.HashMap;
import java.util.Map;

public class HuffmanAlgorithm {

    public static void main(String[] args) throws IOException {

        // Test Huffman Compression.
        // Assuming the received input is HashMap due to its speed.

//        HashMap<String, Integer> testData = new HashMap<>(10);
//        testData.put("A", 10);
//        testData.put("B", 11);
//        testData.put("C", 5);
//        testData.put("D", 13);
//        testData.put("E", 20);
//        String text = "blah";
//


//
//        for (Map.Entry<String, String> huffmanData : compressedData.entrySet()) {
//
//            System.out.println(huffmanData.getKey() + ": " + huffmanData.getValue());
//        }


            StringBuilder fileString = null;
            fileString = new StringBuilder(HuffmanAlgorithm.input("/Users/mody/Downloads/DML.txt"));

            System.out.println(fileString);
            StringBuilder str = new StringBuilder();
            HashMap<String, Integer> data = new HashMap<>();
        for (int index = 0; index < fileString.length(); ++index) {
            for (int inner_index = index; inner_index < index + 1 && inner_index < fileString.length(); ++inner_index) {
                str.append(fileString.charAt(inner_index));
            }
            if (data.containsKey(str.toString())) {
                data.replace(str.toString(), data.get(str.toString()), data.get(str.toString()) + 1);
            } else {
                data.put(str.toString(), 1);
            }
            str.setLength(0);
        }

        for (Map.Entry<String, Integer> freqData : data.entrySet()) {
            System.out.println(freqData.getKey() + ": " + freqData.getValue());
        }

        Huffman huffman = new Huffman(data);
        HashMap<String, String> compressedData = huffman.compressData();
        for (Map.Entry<String, String> freqData : compressedData.entrySet()) {
            System.out.println(freqData.getKey() + ": " + freqData.getValue());
        }

        StringBuilder stringBuilder = new StringBuilder();

        for (int index = 0; index < fileString.length(); ++index) {
            for (int inner_index = index; inner_index < index + 1 && inner_index < fileString.length(); ++inner_index) {
                str.append(fileString.charAt(inner_index));
            }
            stringBuilder.append(compressedData.get(str.toString()));
            str.setLength(0);
        }
        System.out.println(stringBuilder.length());
        BitSet bitSet = new BitSet();
        for (int index = 0; index < stringBuilder.length(); index++) {
            if (stringBuilder.charAt(index) == '1') {
                bitSet.set(index);
            }
        }

        System.out.println(bitSet.length());
    }

    public static String input(String path) throws IOException {
        InputStream inputStream = new FileInputStream(path);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        String fileString;
        String totalStr = "";
        while((fileString = bufferedReader.readLine()) != null)
        {
            totalStr = totalStr + '\n' + fileString;

        }

        inputStream.close();
        bufferedReader.close();

        return totalStr;

    }

}