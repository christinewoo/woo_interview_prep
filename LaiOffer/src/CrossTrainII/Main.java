package CrossTrainII;

import Util.TreeNode;

import java.util.Collections;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) {
        // 8,5,10,3,7,9,null,1,4,6,null,null,null,null,2
        String mytree = "8,5,10,3,7,6,#,1,4,6,#,#,#,#,2";
        TreeNode root = TreeNode.createTree(mytree);
//        TreeNode.printTree(root);
        int[] mine = closestKValues(root,100.0,100);
        for (int e : mine) {
            System.out.print(e + " ");
        }
    }

    public static int[] closestKValues(TreeNode root, double target, int k) {
        PriorityQueue<TreeNode> maxHeap = new PriorityQueue<TreeNode>(k, Collections.reverseOrder()); // maxHeap
        // Assume: given binary search tree is not null
        int friend = root.key;
        // closest number needs to be on path
        while (root != null) {
            if (root.key == target) {
                return new int[]{root.key};
            } else {
                if (Math.abs(root.key - target) < Math.abs(friend - target)) {
                    friend = root.key;
                    if (maxHeap.size() < k) {
                        maxHeap.add(root);
                    } else if (friend < maxHeap.peek().key) {
                        maxHeap.poll();
                        maxHeap.add(root);
                    }
                }
                if (root.key < target) {
                    root = root.right;
                } else {
                    root = root.left;
                }
            }
        }
        int[] kClosest = new int[k];
        for (int i = maxHeap.size() - 1; i >= 0; i--) {
            TreeNode curK = maxHeap.poll();
            kClosest[i] = curK.key;
        }
        return kClosest;
    }
}
