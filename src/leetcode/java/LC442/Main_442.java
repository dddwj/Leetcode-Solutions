package leetcode.java.LC442;

import java.util.*;

public class Main_442 {

    // Reference: Leetcode 448 (Solution2)

    public static void main(String[] args) {
        Main_442 main_442 = new Main_442();
        System.out.println(main_442.findDuplicates(new int[]{4, 3, 2, 7, 8, 2, 3, 1}));
    }


    // Solution: Modify array positiveness  (No Extra Space)
    // Time: O(N)
    // Space: O(1)
    // Ref: Leetcode 448
    public List<Integer> findDuplicates(int[] nums) {
        if (nums == null) {
            return null;
        }
        List<Integer> rst = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            int cur = nums[i] < 0 ? -nums[i] : nums[i];
            if (nums[cur-1] < 0) {
                rst.add(cur);
            }
            nums[cur-1] *= -1;
        }
        return rst;
    }
}
