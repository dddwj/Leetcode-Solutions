package leetcode.java.LC136;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Main_136 {
    public static void main(String[] args) {
        int[] nums = new int[]{2,2,1,3,1};

    }

    // My Solution
    // Time: O(NlongN)  Space: O(1), No Extra Space
    public int singleNumber_mysolution(int[] nums) {
        Arrays.sort(nums);
        for (int i = 0; i <= nums.length - 3; i+=2) {
            if (nums[i] != nums[i + 1])
                return nums[i];
        }
        return nums[nums.length - 1];
    }


    // Solution2: Hash Table  重点方法
    // Time: O(1*N)  Space: O(N), Extra Space
    public int singleNumber_Solution2(int[] nums) {
        Set<Integer> set = new HashSet<>(nums.length);
        for (int x: nums) {
            if (set.contains(x))
                set.remove(x);
            else
                set.add(x);
        }
        return (int) set.toArray()[0];
    }

    // Solution4: Bit Manipulation   重点方法
    // Time: O(N)  Space: O(1)
    public int singleNumber(int[] nums) {
        int rst = 0;
        for (int x: nums)
            rst = rst ^ x;
        return rst;
    }
}
