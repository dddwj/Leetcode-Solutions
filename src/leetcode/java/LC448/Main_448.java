package leetcode.java.LC448;

import java.util.*;

public class Main_448 {

    // Reference: Leetcode 442

    public static void main(String[] args) {
        Main_448 main_448 = new Main_448();
        System.out.println(main_448.findDisappearedNumbers_solution1(new int[]{4, 3, 2, 7, 8, 2, 3, 1}));
        System.out.println(main_448.findDisappearedNumbers_solution1(new int[]{}));
    }

    // Solution1 (My Solution): Auxiliary Array   (Extra Space)
    // Time: O(N)
    // Space: O(N)
    public List<Integer> findDisappearedNumbers_solution1(int[] nums) {
        if (nums == null) {
            return null;
        }
        int n = nums.length;
        boolean[] seen = new boolean[n];
        List<Integer> rst = new ArrayList<>();
        for (int num : nums) {
            seen[num - 1] = true;
        }
        for (int i = 0; i < n; i++) {
            if (!seen[i]) {
                rst.add(i+1);
            }
        }
        return rst;
    }



    // Solution2: Modify array positiveness  (No Extra Space)
    // Time: O(N)
    // Space: O(1)
    public List<Integer> findDisappearedNumbers_solution2(int[] nums) {

        // Iterate over each of the elements in the original array
        for (int i = 0; i < nums.length; i++) {

            // Treat the value as the new index
            int newIndex = Math.abs(nums[i]) - 1;

            // Check the magnitude of value at this new index
            // If the magnitude is positive, make it negative
            // thus indicating that the number nums[i] has
            // appeared or has been visited.
            if (nums[newIndex] > 0) {
                nums[newIndex] *= -1;
            }
        }

        // Response array that would contain the missing numbers
        List<Integer> result = new LinkedList<Integer>();

        // Iterate over the numbers from 1 to N and add all those
        // that have positive magnitude in the array
        for (int i = 1; i <= nums.length; i++) {

            if (nums[i - 1] > 0) {
                result.add(i);
            }
        }

        return result;
    }
}
