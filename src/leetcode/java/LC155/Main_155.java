package leetcode.java.LC155;

import java.util.*;


// Ref: https://leetcode.wang/leetcode-155-Min-Stack.html


public class Main_155 {

}


// Solution1: Two Stacks
// Space: O(N)
class MinStack_Solution1 {
    private Stack<Integer> stack;
    private Stack<Integer> minStack;

    public MinStack_Solution1() {
        stack = new Stack<Integer>();
        minStack = new Stack<Integer>();
    }

    public void push(int x) {
        stack.push(x);
        if (!minStack.isEmpty()) {
            int top = minStack.peek();
            if (x <= top)
                minStack.push(x);
        } else
            minStack.push(x);
    }

    public void pop() {
        int pop = stack.pop();

        if (pop == minStack.peek())
            minStack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }
}


// Solution2: One Stack + Store Min in Stack
// Space: O(N)
class MinStack_Solution2 {

    /** initialize your data structure here. */
    private int min;
    private Stack<Integer> stack;

    public MinStack_Solution2() {
        stack = new Stack<Integer>();
        min = Integer.MAX_VALUE;
    }

    public void push(int x) {
        if (x <= min) {
            stack.push(min);    // store(push) the previous minimum element
            min = x;
        }
        stack.push(x);
    }

    public void pop() {
        if (stack.pop() == min) {
            min = stack.pop();
        }
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return min;
    }
}


// Solution3: One Stack + Store Min in Memory
// Omitted


// Solution4: Implement a stack with hand-written LinkedList (non built-in stacks)
// Space: O(N)
class MinStack_Solution4 {
    class Node {
        int value;
        int min;
        Node next;

        Node(int x, int min) {
            this.value = x;
            this.min = min;
            next = null;
        }
    }

    private Node head;

    public void push(int x) {
        if (head == null) {
            head = new Node(x, x);
        } else {
            Node n = new Node(x, Math.min(x, head.min));
            n.next = head;
            head = n;
        }
    }

    public void pop() {
        if (head != null)
            head = head.next;
    }

    public int top() {
        return head.value;
    }

    public int getMin() {
        return head.min;
    }
}
