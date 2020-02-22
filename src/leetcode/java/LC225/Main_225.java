package leetcode.java.LC225;

import java.util.LinkedList;
import java.util.Queue;

public class Main_225 {
    public static void main(String[] args) {
        Main_225 main_225 = new Main_225();

    }
}

class MyStack {
    private Queue<Integer> q1 = new LinkedList<>();
    private Queue<Integer> q2 = new LinkedList<>();
    private Integer top;

    /** Initialize your data structure here. */
    public MyStack() {

    }

    /** Push element x onto stack. */
    public void push(int x) {

    }

    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        while (q1.size() > 1) {
            top = q1.remove();
            q2.add(top);
        }
        int theTop = q1.remove();
        Queue<Integer> temp = q1;
        q1 = q2;
        q2 = temp;
        return theTop;
    }

    /** Get the top element. */
    public int top() {
        return top;
    }

    /** Returns whether the stack is empty. */
    public boolean empty() {
        return q1.isEmpty();
    }
}