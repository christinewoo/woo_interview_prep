package StringQs;

import java.util.HashSet;
import java.util.Set;

public class StringII {
    public void test() {
        /*  Compress String */
//        String input = "abbcccdeee";
//        String ans = "a1b2c3d1e3";
//        String mine = compress(input);
//        System.out.println(mine);
//        System.out.println(ans.equals(mine));
        /*  Decompress String */
//        String input = "a1b2c3d1e3";
//        String ans = "abbcccdeee";
//        String mine = decompress(input);
//        System.out.println(mine);
//        System.out.println(ans.equals(mine));
        /* Longest Substring Without Repeating Characters */
//        String input = "bcdfbd"; //"efhrgsayekasdanfev";
//        int ans = 4; //9;
//        int mine = longestNonRepSubStr(input);
//        System.out.println(mine);
//        System.out.println(ans == mine);
        /* Longest subarray contains only 1s */
        int[] input = new int[]{1,1,0,0,1,1,1,0,0,0};
        int k = 2;
        int ans = 7;
        int mine = longestOnes(input, k);
        System.out.println(mine);
        System.out.println(ans == mine);
    }

    /* Question: "Longest subarray contains only 1s" */
    public int longestOnes(int[] input, int k) {
        if (input.length < 1 || k < 0) {
            return -1;
        }
        int K = k;
        int slow = 0;
        int fast = 0;
        int longest = 0;
        while (fast < input.length) {
            if (input[fast] == 0) {
                if (k <= 0) {
                    longest = Math.max(longest, fast - slow);
                    slow++;
                    fast = slow;
                    k = K;
                } else {
                    k--;
                    fast++;
                }
            } else {
                fast++;
            }
        }
        longest = Math.max(longest, fast - slow);
        return longest;
    }


    /* Question: "Longest Substring Without Repeating Characters" */
    public int longestNonRepSubStr(String input) {
        if (input == null || input.length() == 0) {
            return 0;
        }
        char[] arr = input.toCharArray();
        return longestSubStr(arr);
    }
    private int longestSubStr(char[] arr) {
        Set<Character> set = new HashSet<Character>();
        int longest = 0; // stores current longest length
        int fast = 0;
        while (fast < arr.length) {
            int begin = fast;
            set.add(arr[fast++]);
            while (fast < arr.length && !set.contains(arr[fast])) {
                set.add(arr[fast]);
                fast++;
            }
            set.clear();
            longest = Math.max(longest, fast - begin);
            fast = begin + 1;
        }
        return longest;
    }

    /* Question: "Decompress String II" */
    public String decompress(String input) {
        if (input == null || input.length() == 0) {
            return input;
        }
        char[] arr = input.toCharArray();
        return decodeLong(arr, decodeShort(arr));
    }
    private int decodeShort(char[] arr) {
        int end = 0;
        // string is shorter so decode left -> right
        for (int i = 0; i < arr.length; i += 2) {
            int digit = getDigit(arr[i + 1]);
            if (digit >= 0 && digit <= 2) {
                for (int j = 0; j < digit; j++) {
                    arr[end++] = arr[i];
                }
            } else { // don't handle longer decoded string
                arr[end++] = arr[i];
                arr[end++] = arr[i + 1];
            }
        }
        return end;
    }
    private int getDigit(char c) {
       return c - '0';
    }
    private String decodeLong(char[] arr, int len) {
        int newLength = len;
        // Find the new length needed after expansion (right <- left)
        for (int i = 0; i < len; i++) {
            int digit = getDigit(arr[i]);
            if (digit > 2 && digit <= 9) { // CASE of single digits
                newLength += digit - 2; // remove 2 at a time for char & digit
            }
        }
        // Create new char[] for output
        char[] extended = new char[newLength];
        // Populate extended char[] by expansion
        int end = newLength - 1;
        for (int i = len - 1; i >= 0; i--) { // do (right -> left)
            int digit = getDigit(arr[i]);
            if (digit > 2 && digit <= 9) {
                i--; // move slow back by one, because slow always at next avaliable write slot
                for (int j = 0; j < digit; j++)  {
                    extended[end--] = arr[i];
                }
            } else { // taken care of the shorter case
                extended[end--] = arr[i];
            }
        }
        return new String(extended);
    }

    /* Question: "Compress String II" */
    public String compress(String input) {
        if (input == null || input.length() == 0) {
            return input;
        }
        char[] arr = input.toCharArray();

        /* Step 1: deal with adj occurrence of letter >= 2 */
        int slow = 0;
        int fast = 0;
        int newLength = 0;
        while(fast < arr.length) {
            int begin = fast; // record starting of repetition
            while (fast < arr.length && arr[fast] == arr[begin]) {
                fast++;
            }
            arr[slow++] = arr[begin];
            if (fast - begin == 1) {
                newLength += 2;
            } else {
                int len = copyDigits(arr, slow, fast - begin);
                slow += len;
                newLength  += len + 1;
            }
        }
        /* STEP 2: create new char[] with newLength */
        char[] extended = new char[newLength];
        fast = slow - 1;
        slow = newLength - 1;
        while (fast >= 0) { // fill in from the end
            if (Character.isDigit(arr[fast])) { // copy long digits
                while (fast >= 0 && Character.isDigit(arr[fast])) {
                    extended[slow--] = arr[fast--];
                }
            } else {
                extended[slow--] = '1'; // when there is only 1 char
            }
            extended[slow--] = arr[fast--]; // copy the character
        }
        return new String(extended);
    }
    private int copyDigits(char[] arr, int slow, int cnt) {
        int len = 0;
        for (int i = cnt; i > 0; i /= 10) {
            slow++; // move forward number of digit spaces needed
            len++;
        }
        // populate the digit backwards
        for (int i = cnt; i > 0; i /= 10) {
            int digit = i % 10;
            arr[--slow] = (char)('0' + digit);
            // here --slow b/c slow always pts to the next writing position
        }
        return len;
    }
}
