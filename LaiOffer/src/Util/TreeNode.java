package Util;

import Midterm.Problem2;

import java.util.ArrayDeque;
import java.util.Queue;

public class TreeNode {
    public int key;
    public TreeNode left;
    public TreeNode right;
    // Constructor
    public TreeNode(int key) {
        this.key = key;
    }
    // Creates a Tree from String
    public static TreeNode createTree(String treeString) {
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
    // Prints a Given TreeNode root
    public static void printTree(TreeNode root) {
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
}
