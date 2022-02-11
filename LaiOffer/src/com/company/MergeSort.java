package com.company;

import Util.ListNode;

public class MergeSort {

    /*  Test Merge Sort */
    public static void testMergeSort() {
        int[] arr = new int[]{6, 8, 2, 5, 7, 3};
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;
        int i = 0;
        while (i < arr.length) {
            cur.next = new ListNode(arr[i]);
            i++;
            cur = cur.next;
        }
        mergeSort(dummy.next);
    }

    /* MERGE SORT WITH LINKED LIST */
    public static ListNode mergeSort(ListNode head) {
        if (head == null || head.next == null){ // check if list is empty or has one element
            return head;
        }
        ListNode mid = findMid(head); // node that points to middle node
        ListNode rightList = mergeSort(mid.next);
        mid.next = null; // separate lists
        ListNode leftList = mergeSort(head);
        return mergeLists(leftList, rightList);
    }

    public static ListNode mergeLists(ListNode left, ListNode right) {
        System.out.print("===============\nLeft List :");
        printList(left);

        System.out.print("Right List :");
        printList(right);

        ListNode newHead = new ListNode(0);
        ListNode cur = newHead;
        while (left != null && right != null) {
            if (left.value < right.value) {
                cur.next = left;
                left = left.next;
            } else { // right is smaller
                cur.next = right;
                right = right.next;
            }
            cur = cur.next;
        }
        if (left != null) {
            cur.next = left;
        }
        if (right != null) {
            cur.next = right;
        }

        System.out.println("Merged: ");
        printList(newHead.next);

        return newHead.next;
    }

    public static ListNode findMid(ListNode head){
        if (head == null || head.next == null || head.next.next == null){
            return head;
        }
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public static void printList(ListNode head) {
        while(head != null) {
            System.out.print(head.value + " ");
            head = head.next;
        }
        System.out.print('\n');
    }
}

