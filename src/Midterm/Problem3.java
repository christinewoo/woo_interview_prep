package Midterm;

import java.util.ArrayList;
import java.util.List;

public class Problem3 {
    public void testMinCut() {
        String input = "ababbbabbababa";
//        System.out.println("Min cut for " + input + " is " + minCut(input));
        System.out.println("Recursively\nMin cut for " + input + " is " + minCut(input));
    }
    /* Problem 3 : "Min Cut Palindrome"
     * Given a string, a partitioning of the string is a palindrome partitioning if every partition is a palindrome.
     * For example, “aba |b | bbabb |a| b| aba” is a palindrome partitioning of “ababbbabbababa”.
     * Determine the fewest cuts needed for palindrome partitioning of a given string.
     * For example,
     * minimum 3 cuts are needed for “ababbbabbababa”. The three cuts are “a | babbbab | b | ababa”.
     * If a string is palindrome, then minimum 0 cuts are needed.
     * Return the minimum cuts.
     *
     * Time  Complexity: O(2 loops and 1 palindrom check) -> O(n^3)
     * Space Complexity: O(n+1) -> O(n)
     *
     * Approach: DP-I Linear Scan look back(bobrobcat)
     */
    public int minCutRecursion(String input) {
        if (input == null || input.length() <= 1) {
            return 0;
        }
        char[] arr = input.toCharArray();
        return helper(arr, 0, arr.length - 1);
    }
    private int helper(char[] arr, int i, int j) {
        if (i == j) { // char is always palindrome no need cuts
            return 0;
        }
        if (isPali(arr, i, j)) { // substring already palindrome
            return 0;
        }
        int minCut = Integer.MAX_VALUE;
        for (int c = i; c <= j - 1; c++) {
            int left = helper(arr, i, c);
            int right = helper(arr, c + 1, j);
            int curMin = left + right + 1; // add one for current cut into left and right substring
            minCut = Math.min(minCut, curMin);
        }
        return minCut;
    }
    public int minCut(String input) {
        if (input == null || input.length() <= 1) {
            return 0;
        }
        char[] arr = input.toCharArray();
        int[] cuts = new int[arr.length + 1];
        cuts[0] = 0;
        cuts[1] = 0; // one char is a pali
        // m[i] - is the min cut of palidrome partitions in substring [0, i)
        for (int i = 2; i < cuts.length; i++) {
            int curMinCut = Integer.MAX_VALUE;
            for (int j = 0; j < i; j++) {
                System.out.print("j, i => " + j + ", " + i + "    substr: " +  input.substring(j, i));
                if (isPali(arr, j, i - 1)) { // remember not including i
                    System.out.print("   is a palindrome");
                    if (j == 0) { // zero cuts needed at j == is palindrome
                        curMinCut = 0;
                        break;
                    } else {
                        curMinCut = Math.min(cuts[j] + 1, curMinCut);
                    }
                }
                System.out.println();
                for (int cut : cuts) {
                    System.out.print(cut + " ");
                }
                System.out.println("   curminCut: " + curMinCut);
            }
            cuts[i] = curMinCut;
            System.out.println("\n Currently: ");
            for (int cut : cuts) {
                System.out.print(cut + " ");
            }
            System.out.println("\n");
        }

        return cuts[cuts.length - 1];
    }
    private boolean isPali(char[] arr, int i, int j) {
        while (i < j) {
            if (arr[i++] != arr[j--]) {
                return false;
            }
        }
        return true;
    }
}
