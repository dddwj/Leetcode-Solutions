package leetcode.java.LC232;

import java.util.Stack;

public class Main_232 {
    public static void main(String[] args) {
        MyQueue obj = new MyQueue();
        obj.push(1);
        obj.push(2);
        obj.push(3);
    }
}
class MyQueue {
    private Integer front;
    private Stack<Integer> s1 = new Stack<>();
    private Stack<Integer> s2 = new Stack<>();

    /** Initialize your data structure here. */
    public MyQueue() {

    }

    /** Push element x to the back of queue. */
    public void push(int x) {
        if (s1.empty())
            front = x;
        while (!s1.isEmpty())
            s2.push(s1.pop());
        s1.push(x);
        while (!s2.isEmpty())
            s1.push(s2.pop());
//        front = s1.peek();
    }

    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        int i = s1.pop();
        if (!s1.empty())
            front = s1.peek();
        return i;
    }

    /** Get the front element. */
    public int peek() {
        return front;
    }

    /** Returns whether the queue is empty. */
    public boolean empty() {
        return s1.isEmpty();
    }
}

