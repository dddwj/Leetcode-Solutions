package leetcode.java.LC278;

public class Main_278 {
    public static void main(String[] args) {
        Main_278 main_278 = new Main_278();
    }

    // Solution: Binary Search
    // Time: O(logN)  Space: O(1)
    public int firstBadVersion(int n) {
        int left = 1, right = n;
        while (left < right) {

            // Note: Test Case: (n=2126753390 & firstBadVersion=1702766719)
            // Mid的计算：(left + right) / 2 会导致溢出，必须使用 left + (right - left) / 2防止溢出！！
            int mid = left + (right - left) / 2;

            if (isBadVersion(mid)) {
                right = mid;    // next search range: [left, mid]
            } else {
                left = mid + 1; // next search range: [mid+1, right]
            }
        }
        // Left = Right = Mid
        return left;
    }

    private boolean isBadVersion(int i) {

        // To be implemented...

        return false;
    }
}
