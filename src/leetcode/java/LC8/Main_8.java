package leetcode.java.LC8;

public class Main_8 {
    public static void main(String[] args) {
        Main_8 main_8 = new Main_8();
//        System.out.println(main_8.myAtoi("   "));
//        System.out.println(main_8.myAtoi("  -42"));
        System.out.println(main_8.myAtoi("-2147483649"));
        System.out.println(main_8.myAtoi("-2147483648"));
//        System.out.println(main_8.myAtoi("9876543219"));
//        System.out.println(main_8.myAtoi("-91283472332"));
//        System.out.println(main_8.myAtoi("word and 472332"));
//        System.out.println(main_8.myAtoi("472332 with words"));
    }


    public int myAtoi(String str) {
        // global index
        int index = 0;
        int result = 0;
        int sign = 1;
        int len = str.length();

        // Corner case
        if (str.length() == 0)
            return 0;

        // remove head space
        while (index < len) {
            if (str.charAt(index) != ' ') {
                break;
            }
            index++;
        }
        if (index == len)
            return 0;


        // check positive flag
        char firstChar = str.charAt(index);
        if (firstChar == '+') {
            index++;
            sign = 1;
        } else if (firstChar == '-') {
            index++;
            sign = -1;
        }

        // calc number
        while (index < len) {
            char currChar = str.charAt(index);
            if (!isNumber(currChar)) {
                break;
            }

            // Check Overflow. 同Leetcode Question 7.  // TestCase: input: "2147483648"  "-2147483647" "-2147483648"  "-2147483649"
            // 题目中说：环境只能存储 32 位大小的有符号整数，因此，需要提前判断乘以 10 以后是否越界
            if (result > Integer.MAX_VALUE / 10 || (result == Integer.MAX_VALUE / 10 && (currChar - '0') > Integer.MAX_VALUE % 10)) {       // 优先级： 非>与>或
                return Integer.MAX_VALUE;
            }
            if (result < Integer.MIN_VALUE / 10 || (result == Integer.MIN_VALUE / 10 && (currChar - '0') > -(Integer.MIN_VALUE % 10))) {
                return Integer.MIN_VALUE;
            }

            result = result * 10 + sign * (currChar - '0');
            index++;
        }

        return result;
    }

    public boolean isNumber(char c) {
        return !(c < 48 || c > 57);
    }

}
