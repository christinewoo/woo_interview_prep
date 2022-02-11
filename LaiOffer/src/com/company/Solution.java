package com.company;

import Util.ListNode;

public class Solution {
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null || n == 0) {
            return head;
        }
        int curCnt = nthNode(head, n);
        if (curCnt == n) {
            return head.next;
        }
        return head;
    }
    public static int nthNode(ListNode head, int n) {
        if (head.next == null) {
//            System.out.println("going back now!! with " + head.value + " " + n);
//            if (n == 1) {
//                head = null;
//                return -1;
//            }
            return 1;
        }
        int curCnt = nthNode(head.next, n);
        if (curCnt == -1) {
//            System.out.println("already removed " + curCnt);
            return -1;
        } else if (curCnt == n) {
//            System.out.println("removing " + head.value);
            ListNode remove = head.next;
            head.next = remove.next;
            curCnt = -1;
        } else {
            curCnt++;
        }
//        System.out.println("return with, " + curCnt);
        return curCnt;
    }

    /*  Test remove Nth form end */
    public static void test() {
        int[] arr = new int[]{1, 2, 3, 4, 5, 6, 7, 8};

        for (int j = 1; j <= 1; j++){
            ListNode dummy = new ListNode(-1);
            ListNode cur = dummy;
            int i = 0;
            while (i < arr.length) {
                ListNode newNode = new ListNode(arr[i]);
                cur.next = newNode;
                i++;
                cur = cur.next;
            }

            System.out.println("===== Calling " + j + " =====");

            printList(removeNthFromEnd(null, j));
            System.out.println(" ");
        }
    }

    public static void printList(ListNode head) {
        ListNode cur = head;
        while (cur != null ) {
            System.out.print(cur.value + " ");
            cur = cur.next;
        }
        System.out.println(" ");
    }
}
