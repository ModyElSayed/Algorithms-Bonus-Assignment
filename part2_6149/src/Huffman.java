import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Huffman {
    private final PriorityQueue<FrequencyNode> huffmanQueue;
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

}