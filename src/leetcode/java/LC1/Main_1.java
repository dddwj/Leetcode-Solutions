package leetcode.java.LC1;

import java.util.*;

public class Main_1 {
    public static void main(String[] args) {
        Main_1 main_1 = new Main_1();
        int[] input = new int[] {2, 6, 9, 10};
        System.out.println(Arrays.toString(main_1.twoSum_solution1(input, 12)));
        System.out.println(Arrays.toString(main_1.twoSum_solution2(input, 12)));
        System.out.println(Arrays.toString(main_1.twoSum(input, 12)));
    }

    // Solution1: Brute Force
    // Time: O(N*N)  Space: O(1)
    public int[] twoSum_solution1(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target)
                    return new int[] {i, j};
            }
        }
        return null;
    }

    // Solution2: Two-Pass Hash Table
    // Time: O(N)  Space: O(N)
    // HashTable: BEST way to maintain a mapping of each element in the array to its index, time: O(1), trading space for speed
    public int[] twoSum_solution2(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int idx = 0; idx < nums.length; idx++) {
            map.put(nums[idx], idx);
        }

        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement) && map.get(complement) != i)
                return new int[] {i, map.get(complement)};
        }
        throw new IllegalArgumentException("No two sum solution");
    }

    // Solution3: One-Pass Hash Table
    // Time: O(N)  Space: O(N)
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement))
                return new int[] {i, map.get(complement)};
            else
                map.put(nums[i], i);
        }
        throw new IllegalArgumentException("No two sum solution");
    }
}
