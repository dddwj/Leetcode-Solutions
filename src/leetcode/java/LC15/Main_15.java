package leetcode.java.LC15;

// See also: LC16, LC1

import java.util.*;

public class Main_15 {
    public static void main(String[] args) {
        Main_15 main_15 = new Main_15();
        main_15.threeSum_solution2(new int[] {-1, 0, 1, 2, -1, -4});
        main_15.threeSum(new int[] {-1, 0, 1, 2, -1, -4});
    }


    // Solution1: Transfer to 'TwoSum' question.  (TwoSum: One-pass Hash Set)
    // Time: O(N*N),  Space: O(N)
    public List<List<Integer>> threeSum_solution2(int[] nums) {
        // Data set up
        Set<List<Integer>> result = new HashSet<>();
        int cnt = nums.length;

        // Corner Case, can be omitted
        if (cnt < 3)
            return new ArrayList<>(result);


        for (int i = 0; i < nums.length - 2; i++) {
            // Confirm the first element
            int first = nums[i];

            // Solve the 'TwoSum' question, Time Complexity: O(N)
            HashSet<Integer> set = new HashSet<>();
            for (int j = i + 1; j < nums.length; j++) {
                int second = nums[j];

                int twoSum = 0 - first - second;
                if (!set.contains(twoSum))
                    set.add(second);
                else {
                    List<Integer> current = new ArrayList<Integer>() {{
                        add(first);
                        add(second);
                        add(twoSum);
                    }};
                    Collections.sort(current);
                    result.add(current);
//                    System.out.println("i, j = " + i + ", " + j);
                }
            }

        }

        // Print the Result List
//        for (List<Integer> l : result){
//            System.out.println(Arrays.toString(l.toArray()));
//            System.out.println("=================================");
//        }
        return new ArrayList<>(result);
    }





    // GREAT Tutorial: https://youtu.be/jeim_j8VdiM
    //
    // BEST SOLUTION:
    // Solution: Sort + Find (Two Pointers)
    // Time: O(N^2 + NlogN)  = O(N^2)
    // Space: O(1), No extra space, but the 'sort' operation will effect the original input array.
    //        O(lgN) for quicksort
    public List<List<Integer>> threeSum(int[] nums) {
        // Sort,   O(NlogN):   Sort is applied to avoid duplicates.  Space Complexity: O(lgN) for quicksort
        Arrays.sort(nums);

        // Find,  O(N^2)
        List<List<Integer>> result = new ArrayList();
        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i - 1] == nums[i]) {
                continue;
            }
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum == 0) {
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    System.out.println("[" + nums[i] + " " + nums[left] + " " + nums[right] + "]");

                    // Test Case: { -1, -1, 0, 0, 1, 1, 1, 2, 2, 3, 4, 4, 4 }
                    // To skip duplicates
                    while (left < right && nums[left] == nums[left+1]){
                        left++;
                    }
                    while (left < right && nums[right] == nums[right-1]){
                        right--;
                    }
                    left++;
                    right--;
                } else if (sum > 0) {
                    right--;
                } else {  // sum < 0
                    left++;
                }
            }
        }
        return result;
    }
}
