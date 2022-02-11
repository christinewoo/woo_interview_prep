package ProbRandSampling;

import java.util.Collections;
import java.util.PriorityQueue;


public class MedianTracker {
    private PriorityQueue<Integer> smol;
    private PriorityQueue<Integer> big;

    // Notice the smalleset number of the larger half
    // Notice the largest number of the smaller half
    public MedianTracker() {
        smol = new PriorityQueue<Integer>(11, Collections.reverseOrder()); // MAX Heap
        big = new PriorityQueue<Integer>(); // MIN Heap
    }

    // Maintain the property:
    // size(smol) == size(big)     EVEN
    // size(smol) == size(big) + 1 ODD
    public void read(int value) {
        if (smol.isEmpty() || value <= smol.peek()) {
            smol.offer(value);
        } else {
            big.offer(value);
        }
        // Maintain balance property
        if (smol.size() > big.size() + 1) {
            big.offer(smol.poll());
        } else if (smol.size() < big.size()) {
            smol.offer(big.poll());
        }
    }

    // Return median base on even and odd number of elements
    public Double median() {
        int size = smol.size() + big.size();
        if (size == 0) {
            return null;
        } else if (size % 2 != 0) { // size is odd
            return (double) (smol.peek()); // smol has that one extra element when balanced
        } else { // size is even
            return (smol.peek() + big.peek()) / 2.0;
        }
    }

}
