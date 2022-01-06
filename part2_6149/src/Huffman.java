import org.w3c.dom.Node;

import java.util.Map;
import java.util.PriorityQueue;
import java.util.HashMap;

public class Huffman {
    private PriorityQueue<FrequencyNode> huffmanQueue;
    private final HashMap<String, String> huffmanTree;

    public Huffman(HashMap<String, Integer> receivedData) {
        if (receivedData.size() > 0) {
            this.huffmanQueue = new PriorityQueue<>(receivedData.size(), new FrequencyComparator());
            insertDataIntoPriorityQueue(receivedData);

            this.huffmanTree = new HashMap<>(receivedData.size());
        } else {
            throw new IllegalArgumentException("There is no data: " + receivedData.size());
        }
    }

    private void insertDataIntoPriorityQueue(HashMap<String, Integer> data) {
        for (Map.Entry<String, Integer> freqData : data.entrySet()) {
            FrequencyNode extractData = new FrequencyNode(freqData.getKey(), freqData.getValue());
            this.huffmanQueue.add(extractData);
        }
    }

    public HashMap<String, String> compressData() {
        buildHuffmanPriorityQueue();
        buildHuffmanTree(huffmanQueue.peek(), new StringBuilder());
        return huffmanTree;
    }
    public int decompress(FrequencyNode root, int index, StringBuilder sb){
        if(root == null){
            return index;
        }
        if (root.isLeaf(root)){
            System.out.println(root.getnBytesWord());
            return index;
        }
        index++;
        if(sb.charAt(index) == '0'){
            root=root.left;
        }else {
            root=root.right;
        }
        index = decompress(root, index, sb);

        return index;
    }

    private void buildHuffmanPriorityQueue() {
        FrequencyNode firstMinFreq , secondMinFreq ;

        int queueSize = huffmanQueue.size() - 1;
        for (int counter = 0; counter < queueSize; counter++) {
            firstMinFreq = huffmanQueue.poll();
            secondMinFreq = huffmanQueue.poll();

            int newFrequency = firstMinFreq.getFrequency() + secondMinFreq.getFrequency();
            FrequencyNode totalFreq = new FrequencyNode("", newFrequency, firstMinFreq, secondMinFreq);
            huffmanQueue.add(totalFreq);
        }
    }

    private void buildHuffmanTree(FrequencyNode root, StringBuilder code) {
        if (root.isLeaf(root) && !root.isWordEmpty()) {
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


}