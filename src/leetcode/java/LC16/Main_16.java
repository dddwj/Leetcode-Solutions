package leetcode.java.LC16;

// See also: LC15, LC1

import java.util.Arrays;

public class Main_16 {

    public static void main(String[] args) {
        Main_16 main_16 = new Main_16();
        System.out.println(main_16.threeSumClosest_solution1(new int[]{-1, 2, 1, -4}, 1));
    }


    // Solution1: Brute Force
    // Time: O(N^3)
    // Space: O(1)
    public int threeSumClosest_solution1(int[] nums, int target) {
        if (nums == null || nums.length < 3)
            throw new IllegalArgumentException("Invalid input");

        int minGap = Integer.MAX_VALUE;
        int res = 0;

        for (int i = 0; i < nums.length; i++) {
            for (int j = i+1; j < nums.length; j++) {
                for (int k = j+1; k < nums.length; k++) {
                    int sum = nums[i] + nums[j] + nums[k];
                    int gap = Math.min(Math.abs(sum - target), Math.abs(target - sum));
                    if (gap < minGap) {
                        minGap = gap;
                        res = sum;
                    }
                }
            }
        }

        return res;
    }



    // Solution2:  Two Pointers, Similar to `BEST SOLUTION` in LC15
    // Ref: LC15
    // Time: O(N^2)
    //        + O(NlogN) for quicksort
    // Space: O(1), No extra space, but the 'sort' operation will effect the original input array.
    //        O(lgN) for quicksort
    public int threeSumClosest_solution2(int[] nums, int target) {
        if (nums == null || nums.length < 3)
            throw new IllegalArgumentException("Invalid input");

        Arrays.sort(nums);
        int minGap = Integer.MAX_VALUE, res = 0;

        for (int i = 0; i < nums.length; i++) {
            int left = i + 1, right = nums.length - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                int gap = Math.min(Math.abs(sum - target), Math.abs(target - sum));
                if (gap < minGap) {
                    minGap = gap;
                    res = sum;
                }
                if (sum < target)
                    left++;
                else if (sum > target)
                    right--;
                else
                    return sum;
            }
        }

        return res;
    }



}
