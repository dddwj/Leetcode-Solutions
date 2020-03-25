package leetcode.java.LC43;

import leetcode.java.LC415.Main_415;

public class Main_43 {

    // Ref: LC2, LC415

    public static void main(String[] args) {
        Main_43 main_43 = new Main_43();
        System.out.println(main_43.multiply_solution1("123", "456"));     // output = 56088
        System.out.println(main_43.multiply("123", "456"));     // output = 56088
    }


    // Solution1: Elementary Math
    // Time: O(M * N)
    // Space: O(1)
    // ref: https://leetcode.wang/leetCode-43-Multiply-Strings.html
    public String multiply_solution1(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }

        Main_415 main_415 = new Main_415();

        String ans = "0";
        int index = 0;      //记录当前是哪一位，便于后边补0
        for (int i = num2.length() - 1; i >= 0; i--) {
            int carry = 0;
            StringBuilder ans_part = new StringBuilder();
            int m = num2.charAt(i) - '0';
            //乘上每一位
            for (int j = num1.length() - 1; j >= 0; j--) {
                int n = num1.charAt(j) - '0';
                int mul = m * n + carry;
                ans_part.insert(0, mul % 10 + "");
                carry = mul / 10;
            }
            if (carry > 0) {
                ans_part.insert(0, carry + "");
            }
            //补零
            for (int k = 0; k < index; k++) {
                ans_part.append("0");
            }
            index++;
            //和之前的结果相加, 借用LC415的函数
            ans = main_415.addStrings(ans, ans_part.toString());
        }
        return ans;
    }



    // Solution2: Elementary Math (Optimized)
    // Time: O(M * N)
    // Space: O(1)
    // ref: https://leetcode-cn.com/problems/multiply-strings/solution/you-hua-ban-shu-shi-da-bai-994-by-breezean/
    public String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }

        int len1 = num1.length(), len2 = num2.length();
        int[] res = new int[len1 + len2];
        for (int i = len1 - 1; i >= 0; i--) {
            int n1 = num1.charAt(i) - '0';
            for (int j = len2 - 1; j >= 0; j--) {
                int n2 = num2.charAt(j) - '0';
                int sum = (res[i + j + 1] + n1 * n2);
                res[i + j + 1] = sum % 10;
                res[i + j] += sum / 10;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < res.length; i++) {
            if (i == 0 && res[i] == 0) {
                continue;
            }
            sb.append(res[i]);
        }

        return sb.toString();
    }

}
