package DP;

public class Linear {
    public void test() {
        int input = 12;
        System.out.println(maxProdCut(input));
    }
    public int maxProdCut(int length) {
        if (length <= 0) {
            return 0;
        }
        int[] m = new int[length + 1];
        for (int i = 2; i < m.length; i++) {
            int curMax = 0;
            for (int j = i - 1; j > 0; j--) {
                curMax = Math.max(j, m[j]) * (i-j);
                m[i] = Math.max(curMax, m[i]);
            }
        }
        return m[length];
    }
}
