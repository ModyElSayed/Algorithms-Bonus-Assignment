public class FrequencyNode {
    public int freq;
    private int frequency;
    private String nBytesWord;
    public FrequencyNode left, right;

    public FrequencyNode(String nBytesWord, int frequency, FrequencyNode left, FrequencyNode right) {
        setnBytesWord(nBytesWord);
        setFrequency(frequency);
        this.left = left;
        this.right = right;
    }

    public FrequencyNode(String nBytesWord, int frequency) {
        this(nBytesWord, frequency, null, null);
    }

    public void setnBytesWord(String nBytesWord) {
        this.nBytesWord = nBytesWord;
    }

    public void setFrequency(int frequency) {
        if (frequency > 0) {
            this.frequency = frequency;
        } else {
            throw new IllegalArgumentException("Illegal frequency number: " + frequency);
        }
    }

    public int getFrequency() {
        return this.frequency;
    }

    public String getnBytesWord() {
        return nBytesWord;
    }

    public boolean isWordEmpty() {
        return this.nBytesWord.isEmpty();
    }
    public boolean isLeaf(FrequencyNode root){
        return root.left == null && root.right == null;
    }
}

