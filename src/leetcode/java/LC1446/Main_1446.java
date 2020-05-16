package leetcode.java.LC1446;

public class Main_1446 {

    public static void main(String[] args) {
        Main_1446 main_1446 = new Main_1446();
        System.out.println(main_1446.maxPower("leetcode"));
        System.out.println(main_1446.maxPower("leetcccc"));
        System.out.println(main_1446.maxPower("l"));
        System.out.println(main_1446.maxPower(""));
    }

    // My Solution: One Pass
    // Time: O(N), N = len(s)
    // Space: O(1)
    public int maxPower(String s) {
        if (s == null || s.length() == 0)
            return 0;

        char[] chars = s.toCharArray();
        int maxCount = 0;

        char prev = chars[0];
        int count = 1;
        for (int i = 1; i < chars.length; i++) {
            if (chars[i] == prev) {
                count++;
            } else {
                maxCount = Math.max(count, maxCount);
                prev = chars[i];
                count = 1;
            }
        }
        maxCount = Math.max(count, maxCount);
        return maxCount;
    }
}
