package leetcode.java.LC28;

public class Main_28 {
    public static void main(String[] args) {
        Main_28 main_28 = new Main_28();
        String needle = "issip"; String haystack = "mississippi";
//        String needle = "issipi"; String haystack = "mississippi";
//        String needle = "aaaa"; String haystack = "aaa";
//        String needle = "aaaa"; String haystack = "aaaaa";
        System.out.println(main_28.strStr(haystack, needle));
    }
    

    // My Solution Opt1: Two pointers - Brute Force
    // Time: O(N)  Space: O(1)
    public int strStr_mysolution(String haystack, String needle) {
        if (needle == null || needle.length() < 1 )
            return 0;

        if (needle.length() > haystack.length())
            return -1;

        int first = 0;
        int second = 0;
        while (first < haystack.length()) {
            while (second < needle.length()) {
                if ((first + second) >= haystack.length())
                    break;
                if (haystack.charAt(first + second) != needle.charAt(second)) {
                    second = 0;
                    break;
                }
                second++;
            }
            if (second == needle.length())
                return first;
            first++;
        }

        return -1;
    }

    // My Solution Opt2: Two pointers - Brute Force
    // Time: O(N*M)  Space: O(1)
    // Ref: https://leetcode-cn.com/problems/implement-strstr/solution/kmpsuan-fa-shi-xian-by-phantom_eve/
    public int strStr_mysolution2(String haystack, String needle) {
        if (needle.length() < 1)
            return 0;

        int first = 0, second = 0;
        while (first < haystack.length()) {
            if (haystack.charAt(first) == needle.charAt(second)) {
                second++;
                if (second == needle.length())
                    return first + 1 - needle.length();
            } else {
                first = first - second;  // 一旦不匹配，first后退
                second = 0;
            }
            first++;
        }

        return -1;
    }


    // Solution1: Two pointers - Built-in String.equals() Function
    // Time: O(N) Space: O(1)
    public int strStr(String haystack, String needle) {
        if (needle == null || needle.length() < 1 )
            return 0;

        int k = needle.length();

        for (int i = 0 ; i <= haystack.length() - k; i++){
            String temp = haystack.substring(i, i + k);
            if (temp.equals(needle))
                return i;
        }
        return -1;
    }
}
