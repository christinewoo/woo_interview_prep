package Midterm;

import Util.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Problem2 {
    public void testMaxSumLeaf2Leaf() {
        String mytree = "-15,5,6,-8,1,3,9,2,6,#,#,#,#,#,0,#,#,#,#,4,-1,#,#,10,#";
        TreeNode root = createTree(mytree);
//        printTree(root);
        int maxSum = maxSumL2L(root);
        System.out.println("MaxSum from Leaf to Leaf is " + maxSum);
    }
    private void printTree(TreeNode root) {
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        while(!queue.isEmpty()) {
            int qsize = queue.size();
            for (int i = 0; i < qsize; i++) {
                TreeNode cur = queue.poll();
                System.out.print(cur.key + " ");
                if (cur.left != null) {
                    queue.offer(cur.left);
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
            }
            System.out.println();
        }
    }
    private TreeNode createTree(String treeString) {
        if (treeString == null) {
            return null;
        }
        String delims = "[,]+";
        String[] tokens = treeString.split(delims);
        TreeNode root = new TreeNode(Integer.parseInt(tokens[0]));
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        int i = 0;
        while(i < tokens.length - 1 && !queue.isEmpty()) {
            TreeNode cur = queue.poll();
            if (tokens[i+1].equals("#")) {
                cur.left = null;
            } else {
                cur.left = new TreeNode(Integer.parseInt(tokens[i+1]));
                queue.offer(cur.left);
            }
            if (tokens[i+2].equals("#")) {
                cur.right = null;
            } else  {
                cur.right = new TreeNode(Integer.parseInt(tokens[i+2]));
                queue.offer(cur.right);
            }
            i += 2;
        }
        return root;
    }
    /* Problem 2 : "Max Sum from Leaf to Leaf"
     * Given a binary tree in which each node contains an int number.
     * Find the maximum possible sum from any leaf node to another leaf node.
     * The maximum sum path may or may not go through root.
     * Expected time complexity is O(n).
     *
     * Time  Complexity: O(# of nodes) - O(n)
     * Space Complexity: O(height)
     *
     * Approach: Recursion
     * What to receive from Child?
     *  - left : maximum sum [root, left]  in left subtree
     *  - right: maximum sum [root, right] in right subtree
     * What to do on current layer?
     *  - check if leaf?
     *      YES -> check if new max? left + right + root.key
     *          YES -> update globMax
     * What to pass to parent?
     *  - max(left, right) + root.key
     *  - if only one child, pass child + root.key
     */
    public int maxSumL2L(TreeNode root) {
        int[] globMax = {Integer.MIN_VALUE};
        maxSumL2L(root, globMax);
        return globMax[0];
    }
    public int maxSumL2L(TreeNode root, int[] globMax) {
        if (root == null) {
            return 0;
        }
        // If leaf node, just return its value
        if (root.left == null && root.right == null) {
            return root.key;
        }
        int left = maxSumL2L(root.left, globMax);
        int right = maxSumL2L(root.right, globMax);
        // Only update when have BOTH children to guarantee leaf -> left
        if (root.left != null && root.right != null) {
            int curMax = left + right + root.key;
            globMax[0] = Math.max(curMax, globMax[0]);
            return Math.max(left, right) + root.key;
        }
        return (root.left == null) ? right + root.key : left + root.key;
    }
}