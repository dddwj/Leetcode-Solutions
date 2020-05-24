package leetcode.java.LC1458;

public class Main_1458 {

    // See also: LC1143

    public static void main(String[] args) {
        Main_1458 main_1458 = new Main_1458();
        System.out.println(main_1458.maxDotProduct(new int[]{2, 1, -2, 5}, new int[]{3, 0, -6}));
        System.out.println(main_1458.maxDotProduct(new int[]{-1000}, new int[]{1000}));
    }

    // Solution: Dynamic Programming
    // Ref: LC1143
    //      https://leetcode.com/problems/max-dot-product-of-two-subsequences/discuss/648411/DP-solution-Java-Explained-with-Diagram
    //      https://leetcode.com/problems/max-dot-product-of-two-subsequences/discuss/648528/Finally-a-diagram-to-make-understanding-easy
    public int maxDotProduct(int[] nums1, int[] nums2) {
        int dp[][] = new int[nums1.length + 1][nums2.length + 1];
        for (int i = 0; i <= nums1.length; i++) {
            dp[i][0] = -1000001;
        }
        for (int j = 0; j <= nums2.length; j++) {
            dp[0][j] = -1000001;
        }

        for (int i = 1; i <= nums1.length; i++) {
            for (int j = 1; j <= nums2.length; j++) {
                int soFar = Math.max(dp[i - 1][j], dp[i][j - 1]);
                int current = nums1[i - 1] * nums2[j - 1];
                int maxCurr = Math.max(current, current + dp[i - 1][j - 1]);
                dp[i][j] = Math.max(maxCurr, soFar);
            }
        }
        return dp[nums1.length][nums2.length];
    }
}
