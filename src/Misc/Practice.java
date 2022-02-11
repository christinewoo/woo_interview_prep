package Cognizant;

import java.util.ArrayList;
import java.util.List;

public class Practice {
    public static void main(String[] args) {
        List<Integer> prices = new ArrayList<>();
    }

    /* Given an arraylist of stock prices, find the cut where the diff of two partition is min.
        [left partition] | [right partition]
        diff = Math.abs( avg([left part]) - avg([right part]) )
     */
    public static int stocks(List<Integer> prices) {
        if (prices == null || prices.size() == 0) {
            return -1;
        }

        int cur_min = 0;
        for (int i = 1; i < prices.size() - 1; i++) {
            int left = avgPrices(0, i, prices);
            int right = avgPrices(i, prices.size(), prices);
            int diff = Math.abs(right - left);
            cur_min = Math.min(diff, cur_min);
        }
        return cur_min;
    }
    public static int avgPrices(int i, int j, List<Integer> prices) {
        int sum = 0;
        int cnt = 0;
        for (int k = i; k < j; k++) {
            sum += prices.get(k);
            cnt++;
        }
        return sum / cnt;
    }


    // Question about prime number between 1 .. n and not contain '0'
    public static void primeNonZero(int n) {
        // corner case
        if (n <= 1) {
            System.out.println(0 + "()");
        }
        int numPrimes = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 2; i <= n; i++) {
            if (isPrime(i) && !containsZero(n)) {
                sb.append((char)('0' + i) + ", ");
                numPrimes++;
            }
        }
        // Display the output
        sb.setLength(sb.length() - 2);
        System.out.println(numPrimes + ", {" + sb.toString() + "}");
    }
    public static boolean containsZero(int n) {
        if (n == 0) {
            return true;
        }
        if (n < 0) {
            n = n * -1;
        }
        while (n > 0) {
            if (n % 10 == 0) {
                return true;
            }
            n /= 10;
        }
        return false;
    }
    public static boolean isPrime(int n) {
        // corner case
        if (n == 1) {
            return false;
        }
        for (int i = 2; i*i <= n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}
