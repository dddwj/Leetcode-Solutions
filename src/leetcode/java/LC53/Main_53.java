package leetcode.java.LC53;

import java.util.Arrays;

public class Main_53 {

    /*
        See also: LC918
     */

    public static void main(String[] args) {
        Main_53 main_53 = new Main_53();
        main_53.maxSubArray_solution2(new int [] {-2,1,-3,4,-1,2,1,-5,4});
    }

    // Solution1: Divide and Conquer
    // To be understood.....


    // Solution2: Greedy
    // Time: O(N)  Space: O(1)
    // Note: 经典的贪心算法操作！
    public int maxSubArray_solution2(int[] nums) {
        int n = nums.length;
        int currSum = nums[0], maxSum = nums[0];

        for(int i = 1; i < n; ++i) {

//          Opt1:
            if (currSum < 0)    // i.e.: if (currSum + nums[i] < nums[i])
                currSum = nums[i];
            else
                currSum = currSum + nums[i];

//          Opt2:
//            currSum = Math.max(nums[i], currSum + nums[i]);

            maxSum = Math.max(maxSum, currSum);
        }
        return maxSum;
    }


    // Solution3: Dynamic Programming
    // Time: O(N)  Space: O(1)
    // Note: 经典的动态规划题目，思路同Solution2-贪心算法。
    // GREAT Tutorial: https://www.bilibili.com/video/av48545120/
    public int maxSubArray_solution3(int[] nums) {
        int n = nums.length;
        int[] current_max_sum = new int[n];    // 即：dp数组
        current_max_sum[0] = nums[0];
        int maxSum = nums[0];

        for(int i = 1; i < n; ++i) {
            if (current_max_sum[i - 1] < 0){
                current_max_sum[i] = nums[i];
            } else {
                current_max_sum[i] = current_max_sum[i - 1] + nums[i];
            }
            maxSum = Math.max(current_max_sum[i], maxSum);
        }
        return maxSum;
    }


    // My Solution: Brute Force
    // Time: O(N*N)  Space: O(N)
    public int maxSubArray_mysolution(int[] nums) {
        int p1 = 0, p2 = 0;
        int size = nums.length;
        int[] maxSumList = new int[size];

        if (size == 1)   return nums[0];

        for (; p1 < size; p1++) {
            int prevSum = nums[p1];
            int maxSum = prevSum;
            for (p2 = p1 + 1; p2 < size; p2++){
                maxSum = Math.max(maxSum, prevSum + nums[p2]);
                prevSum = prevSum + nums[p2];
            }
            maxSumList[p1] = maxSum;
        }

        Arrays.sort(maxSumList);
        System.out.println(Arrays.toString(maxSumList));
        return maxSumList[size - 1];
    }
}
