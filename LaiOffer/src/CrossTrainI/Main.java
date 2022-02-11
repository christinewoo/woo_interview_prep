package CrossTrainI;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
//        int[] arr = {1,1,2,2,2,2,3,4,4,5,5,6,7};
//        int[] mine = dedup(arr);

        int[] arr = {0,0,4,-7,0,8,0};
        int[] mine = moveZero(arr);
        for (int e : mine) {
            System.out.print(e + " ");
        }
    }

    /*  115. Array Deduplication I */
    public static int[] dedup(int[] array) {
        if (array == null || array.length == 0) {
            return array;
        }
        int slow = 0;
        int fast = 1;
        while (fast < array.length) {
            if (array[fast] == array[slow]) {
                while (fast < array.length && array[fast] == array[slow]) {
                    fast++;
                }
            } else {
                array[++slow] = array[fast];
            }
        }
        return Arrays.copyOfRange(array, 0, slow + 1);
    }

    /* Move all 0s to right end */
    public static int[] moveZero(int[] array) {
        if (array == null || array.length == 0) {
            return array;
        }
        int s = 0;
        for (int f = 0; f < array.length; f++) {
            if (array[f] != 0) {
                swap(array, s++, f);
            }
        }
        return array;
    }
    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}