package leetcode.java.LC20;

import java.util.Stack;

public class Main_20 {
    public static void main (String[] args) {
        String[] ss = new String[] {"{}", "({})", "{}()]", "[{}", "(}", "([]])", "([.]}"};
        Main_20 main_20 = new Main_20();
        for (int i = 0; i < ss.length; i++) {
            System.out.println(main_20.judge(ss[i]));
        }
    }

    public boolean judge (String s) {
        Stack<Character> stack = new Stack<Character>();
        for (int i = 0; i < s.length(); i++) {
            Character c = s.charAt(i);
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } else if (c == ')' || c == '}' || c == ']') {
                if (stack.isEmpty())
                    return false;

                Character p = stack.pop();
                if (c == ')' && p == '(')
                    continue;
                if (c == '}' && p == '{')
                    continue;
                if (c == ']' && p == '[')
                    continue;
            } else {
                return false;
            }
        }
        return stack.isEmpty();
    }

}
