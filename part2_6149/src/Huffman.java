import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.BitSet;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Huffman {
    private final PriorityQueue<FrequencyNode> huffmanQueue;
    private final HashMap<String, String> huffmanTree;
    private final StringBuilder fileString;
    private StringBuilder tempString;
    private HashMap<String, Integer> freqData;
    private final int nBytesData;
    private BitSet dataInBits;

    public Huffman(String filePath, int nBytesData) throws IOException {
        fileString = new StringBuilder(input(filePath));
        this.nBytesData = nBytesData;
        setFrequencyMap();

        if (freqData.size() > 0) {
            this.huffmanQueue = new PriorityQueue<>(freqData.size(), new FrequencyComparator());
            this.huffmanTree = new HashMap<>(freqData.size());
        } else {
            throw new IllegalArgumentException("There is no data: " + freqData.size());
        }
    }

    private static StringBuilder input(String filePath) throws IOException {
        BufferedReader bufferedReader = Files.newBufferedReader(Paths.get(filePath));

        String line;
        StringBuilder fileData = new StringBuilder();

        while((line = bufferedReader.readLine()) != null)  {
            fileData.append(line);
            fileData.append(System.lineSeparator());
        }

        bufferedReader.close();
        return fileData;
    }

    private void setFrequencyMap() {
        tempString = new StringBuilder();
        freqData = new HashMap<>();

        for (int endIndex = 0; endIndex < this.fileString.length(); ++endIndex) {
            appendnBytes(endIndex);
            updateDataFrequency(tempString.toString());
            tempString.setLength(0);
        }
    }

    private void appendnBytes(int endIndex) {
        for (int startIndex = endIndex; (startIndex < endIndex + this.nBytesData); ++startIndex) {
            if ((startIndex < this.fileString.length())) {
                tempString.append(this.fileString.charAt(startIndex));
            }
        }
    }

    private void updateDataFrequency(String data) {
        if (freqData.containsKey(data)) {
            freqData.replace(data, freqData.get(data), freqData.get(data) + 1);
        } else {
            freqData.put(data, 1);
        }
    }

    public String compressedFile() {
        compressData();
        // TODO: Writing to new file.
        return "";
    }

    private void compressData() {
        setHuffmanTree();

        StringBuilder data = new StringBuilder();
        for (int endIndex = 0; endIndex < fileString.length(); ++endIndex) {
            appendnBytes(endIndex);
            data.append(huffmanTree.get(tempString.toString()));
            tempString.setLength(0);
        }

        convertDataToBits(data);
    }

    private void convertDataToBits(StringBuilder data) {
        dataInBits = new BitSet(data.length());
        for (int index = 0; index < data.length(); index++) {
            if (data.charAt(index) == '1') {
                dataInBits.set(index);
            }
        }
    }

    private void setHuffmanTree() {
        insertDataIntoPriorityQueue();
        buildHuffmanPriorityQueue();
        buildHuffmanTree(huffmanQueue.peek(), new StringBuilder());
    }

    private void insertDataIntoPriorityQueue() {
        int index = 0;
        FrequencyNode[] extractData = new FrequencyNode[freqData.size()];
        for (Map.Entry<String, Integer> data : freqData.entrySet()) {
            extractData[index].setnBytesWord(data.getKey());
            extractData[index].setFrequency(data.getValue());
            this.huffmanQueue.add(extractData[index++]);
        }
    }

    private void buildHuffmanPriorityQueue() {
        FrequencyNode firstMinFreq, secondMinFreq;

        int queueSize = huffmanQueue.size() - 1;
        for (int counter = 0; counter < queueSize; counter++) {
            firstMinFreq = huffmanQueue.poll();
            secondMinFreq = huffmanQueue.poll();

            assert firstMinFreq != null && secondMinFreq != null;
            int newFrequency = firstMinFreq.getFrequency() + secondMinFreq.getFrequency();
            FrequencyNode totalFreq = new FrequencyNode(null, newFrequency, firstMinFreq, secondMinFreq);
            huffmanQueue.add(totalFreq);
        }
    }

    private void buildHuffmanTree(FrequencyNode root, StringBuilder code) {
        if (nullChildren(root) && !root.isWordEmpty()) {
            huffmanTree.put(root.getnBytesWord(), code.toString());
            code.deleteCharAt(code.length() - 1);
            return;
        }
        buildHuffmanTree(root.left, code.append("0"));
        buildHuffmanTree(root.right, code.append("1"));

        if (code.length() != 0) {
            code.deleteCharAt(code.length() - 1);
        }
    }



    private boolean nullChildren(FrequencyNode root) {
        return (root.left == null && root.right == null);
    }

//    public int decompress(FrequencyNode root, int index, StringBuilder sb) {
//        if (root == null) {
//            return index;
//        } else if (root.isLeaf(root)) {
//            System.out.println(root.getnBytesWord());
//            return index;
//        } else {
//            ++index;
//            if (sb.charAt(index) == '0') {
//                root = root.left;
//            } else {
//                root = root.right;
//            }
//
//            index = this.decompress(root, index, sb);
//            return index;
//        }
//    }

    //    public void buildHashMap(String text){
//        if(text == null || text.length() == 0) return;
//        Map<Character, Integer> freq = new HashMap<>();
//        for (char c: text.toCharArray()) {
//            freq.put(c, freq.getOrDefault(c, 0) + 1);
//        }
//        PriorityQueue<FrequencyNode> pq;
//        pq = new PriorityQueue<>(Comparator.comparingInt(l -> l.freq));
//
//        insertDataIntoPriorityQueue(freq);
//
//
//        for (var entry: freq.entrySet()) {
//            pq.add(new FrequencyNode(entry.getKey(), entry.getValue()));
//        }
//
//    }

    // public Huffman(String text){

    // }

}