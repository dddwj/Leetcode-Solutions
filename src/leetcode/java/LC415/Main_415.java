package leetcode.java.LC415;

public class Main_415 {

    // Ref: LC2, LC43

    public static void main(String[] args) {
        Main_415 main_415 = new Main_415();
        System.out.println(main_415.addStrings("123", "913"));
    }


    // Solution: Elementary Math  (Equivalent to LC2 !!)
    // Time: O(max(N, M))
    // Space: O(max(N, M))
    public String addStrings(String num1, String num2) {
        StringBuilder res = new StringBuilder("");
        int i = num1.length() - 1;
        int j = num2.length() - 1;
        int carry = 0;

        while (i >= 0 || j >= 0) {
            int n1 = (i >= 0)? num1.charAt(i) - '0' : 0;    // NOTE: 这样的写法值得学习。
            int n2 = (j >= 0)? num2.charAt(j) - '0' : 0;
            int tmp = n1 + n2 + carry;
            carry = tmp / 10;
            res.append(tmp % 10);
            i--;
            j--;
        }

        if (carry == 1) {       // 求和运算最后可能出现额外的进位，这一点很容易被遗忘, Test Case: 5 + 5
            res.append(1);
        }

        return res.reverse().toString();
    }
}
