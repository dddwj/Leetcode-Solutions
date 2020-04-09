package leetcode.java.LC844;

import java.util.*;

public class Main_844 {

    public static void main(String[] args) {
        Main_844 main_844 = new Main_844();
        System.out.println(main_844.backspaceCompare_solution1("y#fo##f", "y#f#o##f"));
    }




    // My Solution (Solution1): Build String
    // Time: O(M+N)
    // Space: O(M+N)
    public boolean backspaceCompare_solution1(String S, String T) {
        Stack<Character> l1 = new Stack<>();
        Stack<Character> l2 = new Stack<>();

        for (int i = 0; i < S.length(); i++) {
            if (S.charAt(i) == '#') {
                if (!l1.isEmpty()) {
                    l1.pop();
                }
            } else {
                l1.push(S.charAt(i));
            }
        }
        for (int i = 0; i < T.length(); i++) {
            if (T.charAt(i) == '#') {
                if (!l2.isEmpty()) {
                    l2.pop();
                }
            } else {
                l2.push(T.charAt(i));
            }
        }

        System.out.println(l1);
        System.out.println(l2);

        while (!l1.isEmpty() && !l2.isEmpty()) {
            if (l1.pop() != l2.pop()) {
                return false;
            }
        }

        return l1.isEmpty() && l2.isEmpty();
    }


    // Solution2: Two Pointers
    // Time: O(M+N)
    // Space: O(1)
    public boolean backspaceCompare_solution2(String S, String T) {
        int i = S.length() -1, j = T.length() - 1;
        int skipS = 0, skipT = 0;

        while (i >= 0 || j >= 0) {
            while (i >= 0) {
                if (S.charAt(i) == '#') {
                    skipS++;
                    i--;
                } else if (skipS > 0) {
                    skipS--;
                    i--;
                } else {
                    break;
                }
            }
            while (j >= 0) {
                if (T.charAt(j) == '#') {
                    skipT++;
                    j--;
                } else if (skipT > 0) {
                    skipT--;
                    j--;
                } else {
                    break;
                }
            }
            if (i >= 0 && j >= 0 && S.charAt(i) != T.charAt(j))
                return false;
            if ((i >= 0) != (j >= 0))
                return false;
            i--;
            j--;
        }

        return true;
    }
}
