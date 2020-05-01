package leetcode.java.LC278;

public class Main_278 {
    public static void main(String[] args) {
        Main_278 main_278 = new Main_278();
    }

    // Ref: https://leetcode.wang/leetcode-278-First-Bad-Version.html


    // Solution1: Binary Search
    // Time: O(logN)  Space: O(1)
    public int firstBadVersion_solution1(int n) {
        int left = 1, right = n;
        while (left < right) {      // Note: Key point here. When left == right == mid, the loop exits.

            // Note: Test Case: (n=2126753390 & firstBadVersion=1702766719)
            // Mid的计算：(left + right) / 2 会导致溢出，必须使用 left + (right - left) / 2防止溢出！！  或者使用：int mid = (left + right) >>> 1;
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


    // Solution2: Naive Binary Search
    // Time: O(logN)  Space: O(1)
    public int firstBadVersion_solution2(int n) {
        int low = 1;
        int high = n;
        while (low <= high) {
            int mid = (low + high) >>> 1;
            if (isBadVersion(mid)) {
                if (mid == 1) {     // check if `mid` is the 1st version. (The version number ranges from 1 to N, not 0 to N !)
                    return 1;
                }
                //判断前一个是否是 false
                if (!isBadVersion(mid - 1)) {
                    return mid;
                }
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return -1;
    }

    private boolean isBadVersion(int n) {
        // Implemented by leetcode.com
        return true;
        // return false;
    }
}
