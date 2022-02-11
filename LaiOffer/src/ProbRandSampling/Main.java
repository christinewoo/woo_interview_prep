package ProbRandSampling;

import java.util.Collections;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) {
        MedianTracker mt = new MedianTracker();
        mt.read(1);
        System.out.println(mt.median());
        mt.read(2);
        System.out.println(mt.median());
        mt.read(3);
        System.out.println(mt.median());
    }
}




