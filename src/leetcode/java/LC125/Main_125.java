package leetcode.java.LC125;

public class Main_125 {
    public static void main(String[] args) {
        Main_125 main_125 = new Main_125();
        System.out.println(main_125.isPalindrome_solution3("A man, a plan, a canal: Panama"));
    }


    // Solution1: Built-in Function: StringBuilder.reverse()
    // Pre-process Solution: Regex
    //
    // Time: O(N)  Space: O(N)
    public boolean isPalindrome_solution1(String s) {
        if (s.length() == 0)
            return true;

        s = s.toLowerCase().replaceAll("[^a-zA-Z0-9]","");
        StringBuilder sbRev = new StringBuilder(s);
        if (s.equals(sbRev.reverse().toString()))
            return true;

        return false;
    }

    // Solution2: Two Pointers
    // Pre-process Solution:  Unicode
    //
    // Time: O(N)  Space: O(1)
    public boolean isPalindrome_solution2(String s) {
        if (s.length() == 0)
            return true;

        // Pre-process:  Unicode
        String res = "";
        StringBuilder sb = new StringBuilder(res);
        for(int i = 0;i < s.length(); i++){
            char ch = s.charAt(i);
            if((ch >= 65 && ch <= 90) || (ch >= 48 && ch <= 57) || (ch >= 97 && ch <= 122)){
                sb.append(ch);
            }
        }
        res = sb.toString().toLowerCase();

        // Two pointers
        int start = 0, end = res.length() - 1;
        while (start < end) {
            if (res.charAt(start) != res.charAt(end)) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }

    // Solution2: Two Pointers
    // Pre-process Solution: Built-in Function
    //
    // Time: O(N)  Space: O(1)
    public boolean isPalindrome_solution3(String s) {
        if (s == null || s.length() == 0)
            return true;

        int i = 0;
        int j = s.length() - 1;
        while (i < j) {
            if (!Character.isLetterOrDigit(s.charAt(i))) {       // Pre-process: Built-in function
                i++;
            } else if (!Character.isLetterOrDigit(s.charAt(j))) {
                j--;
            } else {
                if (Character.toLowerCase(s.charAt(i)) != Character.toLowerCase(s.charAt(j)))
                    return false;
                i++;
                j--;
            }
        }
        return true;
    }
}
