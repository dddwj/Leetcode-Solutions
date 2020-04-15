package leetcode.java.LC238;

import java.util.Arrays;

public class Main_238 {

    // Ref: LC29
    //      https://leetcode.wang/leetcode-238-Product-of-Array-Except-Self.html

    public static void main(String[] args) {
        Main_238 main_238 = new Main_238();
        System.out.println(Arrays.toString(main_238.productExceptSelf_solution2(new int[]{1, 2, 3, 4})));
        System.out.println(Arrays.toString(main_238.productExceptSelf_solution2(new int[]{0, 2, 3, 4})));
    }


    // Solution1: Left and Right product lists
    // Time: O(N)
    // Space: O(N)
    // Note: '存储中间结果'的思想，使用记忆数组来降低复杂度（从N*N降到N）。
    public int[] productExceptSelf_solution1(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return nums;
        }

        int n = nums.length;
        int[] L = new int[n];
        int[] R = new int[n];
        L[0] = 1; R[n - 1] = 1;
        for (int i = 1; i < n; i++) {
            L[i] = L[i - 1] * nums[i - 1];
            R[n - 1 - i] = R[n - i] * nums[n - i];
        }

        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            res[i] = L[i] * R[i];
        }
        return res;
    }


    // Solution2:  Left product list    (right list *on the fly*)
    // Time: O(N)
    // Space: O(1)
    public int[] productExceptSelf_solution2(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return nums;
        }

        int n = nums.length;
        int[] ans = new int[n];
        ans[0] = 1;
        for (int i = 1; i < n; i++) {
            ans[i] = ans[i - 1] * nums[i - 1];
        }

        int R = 1;
        for (int i = n - 1; i >= 0; i--) {
            ans[i] = ans[i] * R;
            R *= nums[i];
        }
        return ans;
    }
}
