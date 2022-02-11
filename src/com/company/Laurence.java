package com.company;

public class Laurence {
    public static void getDiv() {
        int cnt = 0;
        for (int l=1; l<=1000; l++) {
            if (l>329) {
                break;
            }
            if (l%18 == 0 && l%4 == 0) {
                cnt++;
                System.out.print(l + " ");
            }
        }
        System.out.println("\n" + cnt);
        //	    for (int l=5; l<10; l=l+2) {
        //	        System.out.print(l);
        //        }
        //        System.out.println('\n');

        //            if (l==6 || l==10 || l==13) {
        //                continue;
        //            }
        //            System.out.print(l + " ");
    }

    /*
    2 4 3
    count 0
    243 / (l = 1) = 243     243 < 10    F    count 1
    243 / (l = 10) = 24      24 < 10    F    count 2
    243 / (l = 100) = 2       2 < 10    T    count 3   break
    print count


    count 1
    243 / (l = 1) = 243     243 < 10    F             count 2
    243 / (l = 10) = 24      24 < 10    F             count 3
    243 / (l = 100) = 2       2 < 10    T    break



    654321    6
    count 0
    x = 654321 count 0
    x>0  T  count 1  65432
    x>0  T  count 2  6543
    x>0  T  count 3  654
    x>0  T  count 4  65
    x>0  T  count 5  6
    x>0  T  count 6  0
    x==0 F


    */
    public static void getNumINt(int x) {
        int count = 0;
        while (x > 0) {
            count++;
            x /= 10;
        }
        System.out.println(count);
    }
//    for (int i = 1; i <= x; i *= 10) {
//        count++;
//    }
}
