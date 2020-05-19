package leetcode.java.LC901;

import java.util.*;

public class Main_901 {

    public static void main(String[] args) {
        StockSpanner_solution1 solution = new StockSpanner_solution1();
        // StockSpanner_solution1_optimized solution = new StockSpanner_solution1_optimized();
        System.out.println(solution.next(1));
        System.out.println(solution.next(1));
        System.out.println(solution.next(1));
        System.out.println(solution.next(1));
        System.out.println(solution.next(100));
        System.out.println(solution.next(80));
        System.out.println(solution.next(60));
        System.out.println(solution.next(70));
        System.out.println(solution.next(60));
        System.out.println(solution.next(75));
        System.out.println(solution.next(85));
    }


    // Solution: Monotone Decreasing Stack
    // Ref: https://leetcode-cn.com/problems/online-stock-span/solution/dan-diao-zhan-tao-lu-xie-fa-you-hua-wei-guan-fang-/
    static class StockSpanner_solution1 {

        private Stack<Integer> prices, cache;

        public StockSpanner_solution1() {
            prices = new Stack<>();
            cache = new Stack<>();
        }

        public int next(int price) {
            int w = 1;
            while (!prices.isEmpty() && prices.peek() <= price) {
                prices.pop();
                w += cache.pop();
            }

            prices.push(price);
            cache.push(w);

            return w;
        }
    }


    // Solution (Optimized): Monotone Decreasing Stack
    static class StockSpanner_solution1_optimized {

        private int[] pricesStack;
        private int[] spansStack;
        private int top;

        public StockSpanner_solution1_optimized() {
            pricesStack = new int[10000];
            spansStack = new int[10000];
            top = -1;
        }

        public int next(int price) {
            int span = 1;
            while (0 <= top && pricesStack[top] <= price) {
                span += spansStack[top];
                top--;
            }
            top++;
            pricesStack[top] = price;
            spansStack[top] = span;
            return span;
        }
    }

}
