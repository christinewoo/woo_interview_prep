package BitOperations;

public class Main {
    public static void main(String[] args) {
//        long input = 11832448;
//        long ans = 20000000;
//        System.out.println(reverseBits(input));
//        System.out.println(reverseBits(input) == ans);

        System.out.println(hex(16));
    }
    public static long reverseBits(long num) {
        for (int i = 0; i < 16; i++) {
            long right = (num >> i) & 1L;
            long left = (num >> 31 - i) & 1L;
            if (left != right) {
                num ^= (1L << i);
                num ^= (1L << (31 - i));
            }
        }
        return num;
    }

    public static String hex(int number) {
        String prefix = "0x";
        if (number == 0) {
            return "0x0";
        }
        StringBuilder sb = new StringBuilder();
        while (number > 0) {
            int rem = number % 16;
            if (rem <= 9) {
                sb.append((char)(rem + '0'));
            } else {
                sb.append((char)(rem - 10 + 'A'));
            }
            number >>>= 4;
        }
        return prefix + sb.reverse().toString();
    }
}
