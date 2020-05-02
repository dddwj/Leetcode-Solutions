package leetcode.java.LC55;

public class Main_55 {

    public static void main(String[] args) {
        Main_55 main_55 = new Main_55();
        System.out.println(main_55.canJump_solution22(new int[]{2, 3, 1, 1, 4}));
        System.out.println(main_55.canJump_solution22(new int[]{3, 2, 1, 0, 4}));
    }


    // My Solution: Brute Force  (TLE)  (Solution1 on Leetcode: Backtracking)
    // Time: O(N*K), where K = avg of nums array
    // Space: O(N) for recursion
    public boolean canJump_mysolution(int[] nums) {
        if (nums == null || nums.length == 0)
            return false;
        return canJump(nums, 0);
    }

    public boolean canJump(int[] nums, int index) {
        int n = nums.length;
        if (index >= n - 1)
            return true;

        int maxJump = nums[index];
        for (int step = maxJump; step > 0; step--) {
            if (canJump(nums, index + step))
                return true;
        }
        return false;
    }


    // Solution2: Greedy
    // Time: O(N)
    // Space: O(1)
    // Ref: https://leetcode-cn.com/problems/jump-game/solution/tiao-yue-you-xi-by-leetcode-solution/
    public boolean canJump_solution21(int[] nums) {
        int n = nums.length;
        int reach = 0;
        for (int i = 0; i < n; i++) {
            if (i > reach)
                return false;
            reach = Math.max(reach, i + nums[i]);
        }
        return true;
    }


    // Solution2: Greedy (Optimized)
    // Time: O(N)  Space: O(1)
    // Ref: https://leetcode-cn.com/problems/jump-game/solution/tiao-yue-you-xi-by-leetcode-solution/
    public boolean canJump_solution22(int[] nums) {
        int n = nums.length;
        int reach = 0;
        for (int i = 0; i <= reach && reach < n - 1; i++) {
            reach = Math.max(reach, i + nums[i]);
        }
        return reach >= n - 1;
    }


    // Solution3: Greedy (Solution4 on leetcode.com)
    // Time: O(N)  Space: O(1)
    // Ref: https://leetcode-cn.com/problems/jump-game/solution/tiao-yue-you-xi-by-leetcode-solution/
    public boolean canJump_solution3(int[] nums) {
        int n = nums.length, lastPos = n - 1;
        for (int i = n - 2; i >= 0; i--) {
            if (i + nums[i] >= lastPos) {
                lastPos = i;
            }
        }
        return lastPos == 0;
    }


    // Solution4: Dynamic Programming (Solution2 & 3 on leetcode.com)
    // To be learned.
}
