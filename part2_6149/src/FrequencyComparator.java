import java.util.Comparator;

public class FrequencyComparator implements Comparator<FrequencyNode> {

        @Override
        public int compare(FrequencyNode firstNode, FrequencyNode secondNode) {
                return (firstNode.getFrequency() - secondNode.getFrequency());
        }
}
