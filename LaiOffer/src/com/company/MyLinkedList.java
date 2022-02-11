package com.company;

public class MyLinkedList {
    ListNode head;
    ListNode tail;
    int size;
    static class ListNode {
        int value;
        ListNode next;
        ListNode prev;
        public ListNode(int val) {
            value = val;
        }
    }

    /** Initialize your data structure here. */
    public MyLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    /** Get the value of the index-th node in the linked list. If the index is invalid, return -1. */
    public int get(int index) {
        if (index >= size) {
            return -1;
        }
        ListNode cur = head;
        int count = 0;
        while (count < size && cur != null) {
            if (count == index){
                return cur.value;
            }
            count++;
            cur = cur.next;
        }
        return -1;
    }

    /** Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list. */
    public void addAtHead(int val) {
        ListNode newNode = new ListNode(val);
        newNode.next = head;
        if (head != null) {
            head.prev = newNode;
        }
        head = newNode;
        size++;
        if (tail == null) {
            tail = newNode;
        }
        System.out.println("add head: " + head.value);
    }

    /** Append a node of value val to the last element of the linked list. */
    public void addAtTail(int val) {
        ListNode newNode = new ListNode(val);
        tail.next = newNode;
        newNode.prev = tail;
        tail = newNode;
        size++;
        System.out.println("add tail: " + tail.value);
    }

    /** Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted. */
    public void addAtIndex(int index, int val) {
        if (index < 0 || index > size) {
            System.out.println("Nothing was added");
            return;
        }
        if (index == 0) {
            addAtHead(val);
            return;
        }
        if (index == size - 1) {
            ListNode newNode = new ListNode(val);
            tail.prev.next = newNode;
            newNode.next = tail;
            newNode.prev = tail.prev;
            size++;
            return;
        }
        ListNode cur = head;
        int count = 0;
        while(count < size && cur != null) {
            if (count == index) {
                ListNode newNode = new ListNode(val);
                newNode.next = cur;
                newNode.prev = cur.prev;
                cur.prev.next = newNode;
                cur.prev = newNode;
                size++;
            }
            count++;
            cur = cur.next;
        }
    }

    /** Delete the index-th node in the linked list, if the index is valid. */
    public void deleteAtIndex(int index) {
        if (index < 0 || index > size) {
            System.out.println("Nothing was removed");
            return;
        } else if (index == 0) {
            head = head.next;
            size--;
            return;
        } else if (index == size - 1) {
            tail.prev.next = null;
            tail = tail.prev;
            size--;
            return;
        }
        ListNode cur = head.next;
        int count = 1;
        while(count < size && cur.next != null){
            if (count == index) {
                cur.prev.next = cur.next;
                cur.next.prev = cur.prev;
                size--;
                return;
            }
            count++;
            cur = cur.next;
        }
    }

    public void printList() {
        ListNode cur = head;
        while (cur != null ) {
            System.out.print(cur.value + " ");
            cur = cur.next;
        }
        System.out.println(" ");
    }

    public void printTail() {
        ListNode cur = tail;
        while (cur != null) {
            System.out.print(cur.value + " ");
            cur = cur.prev;
        }
        System.out.println(" ");
    }
}

/**
 * Your MyLinkedList object will be instantiated and called as such:
 * MyLinkedList obj = new MyLinkedList();
 * int param_1 = obj.get(index);
 * obj.addAtHead(val);
 * obj.addAtTail(val);
 * obj.addAtIndex(index,val);
 * obj.deleteAtIndex(index);
 */
