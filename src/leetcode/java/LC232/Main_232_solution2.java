package leetcode.java.LC232;

import java.util.Stack;

public class Main_232_solution2 {

    public static void main(String[] args) {
        MyQueue_S2 obj = new MyQueue_S2();
        obj.push(1);
        obj.push(2);
        obj.push(3);
    }


}

// Solution2:
// Ref: https://leetcode-cn.com/problems/implement-queue-using-stacks/solution/wen-zi-shi-pin-de-fang-shi-xiang-xi-jiang-jie-li-2/
class MyQueue_S2 {

    Stack<Integer> stack1 = new Stack<>();
    Stack<Integer> stack2 = new Stack<>();
    int front = -1;

    /** Initialize your data structure here. */
    public MyQueue_S2() {

    }

    /** Push element x to the back of queue. */
    // Time: O(1)
    public void push(int x) {
        if (stack1.isEmpty()) {
            front = x;
        }
        stack1.push(x);
    }

    /** Removes the element from in front of queue and returns that element. */
    // Time: `Amortized` O(1)
    public int pop() {
        if (stack2.isEmpty()) {
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }
        return stack2.pop();
    }

    /** Get the front element. */
    // Time: O(1)
    public int peek() {
        if (!stack2.isEmpty()) {
            return stack2.peek();
        }
        return front;
    }

    /** Returns whether the queue is empty. */
    // Time: O(1)
    public boolean empty() {
        return stack1.isEmpty() && stack2.isEmpty();
    }
}
