package leetcode.java.LC9;

public class Main_9 {

    public static void main(String[] args) {
        Main_9 main_9 = new Main_9();
        System.out.println(main_9.isPalindrome_solution1(101));
        System.out.println(main_9.isPalindrome_solution1(-12));
        System.out.println(main_9.isPalindrome_solution1(Integer.MAX_VALUE));


    }

    // Solution0: Convert to string
    // Time: O(N)
    // Space: O(N)
    public boolean isPalindrome_solution0(int x) {
        if (x < 0)
            return false;

        String str = "" + x;
        int left = 0, right = str.length() - 1;
        while (left <= right) {
            if (str.charAt(left) != str.charAt(right))
                return false;
            left++;
            right--;
        }
        return true;
    }


    // Solution1:  Math
    // Time:  O(lgN)
    // Space:  O(1)
    // Ref: https://leetcode.com/problems/palindrome-number/solution/
    public boolean isPalindrome_solution1(int x) {
        if (x < 0 || (x % 10 == 0 && x != 0))
            return false;

        int revertedNumber = 0;
        while (x > revertedNumber) {
            revertedNumber = revertedNumber * 10 + x % 10;
            x /= 10;
        }

        return  x == revertedNumber || x == (revertedNumber/10);
    }
}
