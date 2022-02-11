package com.company;

import Util.ListNode;

public class AddTwoNum {
    public static ListNode addTwoNum(ListNode l1, ListNode l2) {
        /* Corner Case */
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        /* Reverse both lists */
        ListNode r1 = reverse(l1);
        ListNode r2 = reverse(l2);
        printList(r1);
        printList(r2);
        /* Add both lists values */
        ListNode summed = r1;
        ListNode cur = r1;
        int carry = 0;
        while(r1 != null && r2 != null) {
            int sum = r1.value + r2.value + carry;

            r1.value = sum % 10;
            System.out.println(" ..."+r1.value + " & " + sum );
            carry = sum / 10;
            cur = r1;
            r1 = r1.next;
            r2 = r2.next;
        }
        printList(summed);
        while (r1 != null) {
            int sum = r1.value + carry;
            r1.value = sum % 10;
            carry = sum / 10;
            cur = r1;
            r1 = r1.next;
        }
        printList(summed);
        if (r2 != null){
            r1.next = r2;
            while (r2 != null) {
                int sum = r2.value + carry;
                r2.value = sum % 10;
                carry = sum / 10;
                cur = r2;
                r2 = r2.next;
            }
        }
        printList(summed);
        if (carry != 0){
            ListNode carryNode = new ListNode(carry);
            cur.next = carryNode;
        }
        printList(summed);
        /* Reverse summed list */
        return reverse(summed.next);
    }
    /* Helper that reverses a linked list */
    public static ListNode reverse(ListNode head){
        ListNode prev = null;
        while (head != null){
            ListNode temp = head.next;
            head.next = prev;
            prev = head;
            head = temp;
        }
        return prev;
    }

    public static void printList(ListNode head) {
        while(head != null) {
            System.out.print(head.value + " ");
            head = head.next;
        }
        System.out.print('\n');
    }

    /*  Test Add two num */
    public static void test() {
        int[] arr1 = new int[]{9, 8};
        int[] arr2 = new int[]{1, 1};
        ListNode num1 = new ListNode(-1);
        ListNode num2 = new ListNode(-1);
        ListNode cur1 = num1;
        ListNode cur2 = num2;
        int i = 0;
        while (i < arr1.length) {
            ListNode newNode1 = new ListNode(arr1[i]);
            ListNode newNode2 = new ListNode(arr2[i]);
            cur1.next = newNode1;
            cur2.next = newNode2;
            i++;
            cur1 = cur1.next;
            cur2 = cur2.next;
        }
        addTwoNum(num1.next, num2.next);

    }
}

