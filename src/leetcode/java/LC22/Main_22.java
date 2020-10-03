package leetcode.java.LC22;

import java.util.ArrayList;
import java.util.List;

// Great Tutorial: https://leetcode.wang/leetCode-22-Generate-Parentheses.html

public class Main_22 {
    public static void main(String[] args) {
        Main_22 main_22 = new Main_22();
//        List<String> res = main_22.generateParenthesis_solution1(3);
//        List<String> res = main_22.generateParenthesis_solution2(3);
        List<String> res = main_22.generateParenthesis_solution3(3);
        res.forEach(System.out::println);
    }

    // Solution1: Brute Force
    // Time: O(N * 2^(2N))
    // Space: O(N * 2^(2N))
    public List<String> generateParenthesis_solution1(int n) {
        if (n <= 0) {
            return null;
        }
        List<String> validList = new ArrayList<>();
        generateAll(new char[2 * n], 0, validList);
        return validList;
    }

    private void generateAll(char[] current, int pos, List<String> validList) {
        if (pos == current.length) {
            String currentStr = new String(current);
            if (isValid(currentStr))
                validList.add(currentStr);
        } else {
            current[pos] = '(';
            generateAll(current, pos + 1, validList);
            current[pos] = ')';
            generateAll(current, pos + 1, validList);
        }
    }

    private boolean isValid(String expression) {
        int balance = 0;
        for (int i = 0; i < expression.length(); i++) {
            if (expression.charAt(i) == '(') {
                balance++;
                continue;
            } else { // expression.charAt(i) == ')'
                balance--;
            }
            if (balance < 0) {
                return false;
            }
        }
        return (balance == 0);
    }




    // Solution2: Backtrack
    // Ref: https://leetcode.com/problems/generate-parentheses/solution/
    //      https://leetcode.wang/leetCode-22-Generate-Parentheses.html
    public List<String> generateParenthesis_solution2(int n) {
        List<String> ans = new ArrayList<>();
        // backtrack(ans, new StringBuilder(), 0, 0, n);
        backtrack(ans, "", 0, 0, n);
        return ans;
    }

    // Cannot work with StringBuilder, because sb does shallow copy.
    /*
    private void backtrack(List<String> ans, StringBuilder cur, int left, int right, int n) {
        if (cur.length() == 2 * n) {
            ans.add(cur.toString());
            return;
        }
        if (left < n)       // Remain valid case#1: count(left par) < N
            backtrack(ans, cur.append("("), left + 1, right, n);
        if (right < left)   // Remain valid case #2: count(right par) < count(left par)
            backtrack(ans, cur.append(")"), left, right + 1, n);
    }
    */

    private void backtrack(List<String> ans, String cur, int left, int right, int n) {
        if (cur.length() == 2 * n) {
            ans.add(cur);
            return;
        }
        if (left < n)       // Remain valid case #1: count(left par) < N
            backtrack(ans, cur + "(", left + 1, right, n);
        if (right < left)   // Remain valid case #2: count(right par) < count(left par)
            backtrack(ans, cur + ")", left, right + 1, n);
    }



    // Solution3: Closure Number
    // Ref: https://leetcode.com/problems/generate-parentheses/solution/
    //      https://leetcode.wang/leetCode-22-Generate-Parentheses.html
    public List<String> generateParenthesis_solution3(int n) {
        List<String> ans = new ArrayList<>();
        if (n == 0) {
            ans.add("");
        } else {
            for (int a = 0; a < n; a++) {
                for (String left : generateParenthesis_solution3(a))
                    for (String right : generateParenthesis_solution3(n - 1 - a))
                        ans.add("(" + left + ")" + right);
            }
        }
        return ans;
    }
}
