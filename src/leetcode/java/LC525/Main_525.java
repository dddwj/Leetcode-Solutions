package leetcode.java.LC525;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Main_525 {

    public static void main(String[] args) {
        Main_525 main_525 = new Main_525();
        System.out.println(main_525.findMaxLength_solution2(new int[]{0, 1, 0}));
    }

    // Solution1: Brute Force  TLE



    // Solution2: Using Extra Array
    // Time: O(N)
    // Space: O(N)
    public int findMaxLength_solution1(int[] nums) {
        int[] arr = new int[2 * nums.length + 1];   // range from (-n, +n)
        Arrays.fill(arr, -2);
        arr[nums.length] = -1;
        int maxLen = 0, count = 0;
        for (int i = 0; i < nums.length; i++) {
            count = count + (nums[i] == 0 ? -1 : 1);
            if (arr[count + nums.length] >= -1) {   // encountered before
                maxLen = Math.max(maxLen, i - arr[count + nums.length]);
            } else {
                arr[count + nums.length] = i;
            }
        }
        return maxLen;
    }


    // Solution3: Using Hashmap
    // Time: O(N)
    // Space: O(N)
    // Ref: https://leetcode.com/problems/contiguous-array/solution/
    public int findMaxLength_solution2(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();        // (count, index)
        map.put(0, -1);    // count = 0, index = -1
        int maxLen = 0, count = 0;
        for (int i = 0; i < nums.length; i++) {
            count = count + (nums[i] == 1 ? 1 : -1);
            if (map.containsKey(count)) {
                maxLen = Math.max(maxLen, i - map.get(count));
            } else {
                map.put(count, i);
            }
        }
        return maxLen;
    }

}
