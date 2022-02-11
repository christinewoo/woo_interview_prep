package Midterm;

import java.util.ArrayList;
import java.util.List;

public class Problem1 {
    public void testNQueen() {
        int n = 8;
        List<List<Integer>> possibleBoards = nqueen(n);
        /* Visualize Solutions */
        printBoards(possibleBoards, n, 3);
        //System.out.println(possibleBoards);
    }
    private void printBoards(List<List<Integer>> possibleBoards, int n, int numToPrint) {
        int cnt = 0;
        for (List<Integer> board: possibleBoards) {
            System.out.println("============================ Solution #" + ++cnt);
            printBoard(board, n);
            System.out.println();
            if (cnt >= numToPrint) {
                break;
            }
        }
    }
    private void printBoard(List<Integer> board, int n) {
        System.out.println("    0  1  2  3  4  5  6  7 ");
        for(int i = 0; i < board.size(); i++) {
            System.out.print(i + " ");
            for (int j = 0; j < n; j++) {
                if (j == board.get(i)) {
                    System.out.print("|Q" + i);
                } else {
                    System.out.print("|  ");
                }
            }
            System.out.println("|");
        }
    }
    /* Problem 1 : "N-Queens"
     * Find all valid ways of putting N Queens on an N * N chessboard
     * so that no two Queens can attack each other (two queens can attack each
     * other if they are on the same row/column or same diagonal line).
     * You can define your own way of how to print the solution, e.g. using a
     * size N array/List to record which column the queen occupies on each row.
     *
     * Time  Complexity: (8^8 * 8) => O(n^n * n)
     * Space Complexity:
     *
     * Approach: DFS
     * How many levels? 8 levels - each level consider one queen
     * How many branches? 8 branches - so each column can be tried

     */
    public List<List<Integer>> nqueen(int n){
        List<List<Integer>> validPos = new ArrayList<>();
        // Sanity Check
        if (n < 0) {
            return validPos;
        }
        List<Integer> board = new ArrayList<>();
        placeQueens(validPos, board, n);
        return validPos;
    }
    private void placeQueens(List<List<Integer>> validPos, List<Integer> board, int n) {
        // Base Case: n queens are placed on board successfully so add to validPos
        if (board.size() == n) {
            validPos.add(new ArrayList(board));
            return;
        }
        // try queen_i on each column for row_i
        for (int i = 0; i < n; i++) {
            if (posIsValid(board, i)) {
                board.add(i);
                placeQueens(validPos, board, n);
                board.remove(board.size() - 1);
            }
        }
    }
    private boolean posIsValid(List<Integer> board, int col) {
        int numRows = board.size();
        for (int i = 0; i < numRows; i++) {
            if(board.get(i) == col) {
                return false;
            }
            if (Math.abs(board.get(i) - col) == numRows - i) { // if equals 1, they have same slope
                return false;
            }
        }
        return true;
    }
}
