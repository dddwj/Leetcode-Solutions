package leetcode.java.LC283;

import java.util.Arrays;

public class Main_283 {
    public static void main(String[] args) {
        Main_283 main_283 = new Main_283();
        int[] nums = {2, 3, 0, 2, 1, 0, 1, 0};
        main_283.moveZeroes(nums);
        System.out.println(Arrays.toString(nums));
    }

    // Solution: Two Pointers
    // Time: O(N)  Space: O(1)
    public void moveZeroes(int[] nums) {
        int fast = 0, slow = 0;
        while (fast < nums.length) {
            if (nums[fast] != 0) {
                nums[slow] = nums[fast];
                slow++;
            }
            fast++;
        }
        while (slow < nums.length)
            nums[slow++] = 0;
    }
}
