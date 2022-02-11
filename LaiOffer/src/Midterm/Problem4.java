package Midterm;

import java.util.ArrayList;
import java.util.List;

public class Problem4 {
    public void testValidIfs() {
        int n = 2;
        List<String> perms = validIfs(n);
    }
    /* Problem4: DFS Parenthesis with indentation
     * Given an integer n, print/output all possible ways of writing n pairs of if blocks with correct indentation.
     * Say n=2 output should be
     * if {
     * }
     * if {
     * }
     * <newline>
     * if {
     *  if { // here should exist two spaces before each inner block
     *  }
     * }
     *
     * Time Complexity: O(2 ^ (2n) * n^2)
     * Space Complexity: O(height of recursion tree) = O(2n)
     *
     * Approach: DFS parenthesis permutation + post-printing indentation
     * Number of branches: 2 for add or no add
     * Number of levels: 2n b/c n pairs of {}
     */
    public List<String> validIfs(int n) {
        List<String> perms = new ArrayList<String>();
        StringBuilder sb = new StringBuilder();
        DFS(perms, sb, n, 0, 0);
//        printPerms(perms);
        return perms;
    }
    private void DFS(List<String> perms, StringBuilder sb, int n, int left, int right) {
        if (left == n && right == n) {
            perms.add(sb.toString());
            printIfs(perms.get(perms.size() - 1));
            return;
        }
        if (left < n) {
            int size = sb.length();
            sb.append("{");
            DFS(perms, sb, n, left + 1, right);
            sb.setLength(size);
        }
        if (right < left) {
            int size = sb.length();
            sb.append("}");
            DFS(perms, sb, n, left, right + 1);
            sb.setLength(size);
        }
    }
    private void printPerms(List<String> perms) {
        for (int i = 0; i < perms.size(); i++) {
            printIfs(perms.get(i));
            System.out.println();
        }
    }
    private void printIfs(String ifs) {
        int space = 0;
        for (int i = 0; i < ifs.length(); i++) {
            char cur = ifs.charAt(i);
            if (cur == '{') {
                printSpace(space);
                System.out.println("if {");
                space += 2;
            } else if (cur == '}') {
                space -= 2;
                printSpace(space);
                System.out.println("}");
            }
        }
        System.out.println();
    }
    private void printSpace(int space) {
        for (int i = 0; i < space; i++) {
            System.out.print(" ");
        }
    }
}
