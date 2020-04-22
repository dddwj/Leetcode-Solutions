package leetcode.java.LC560;

import java.util.HashMap;
import java.util.Map;

public class Main_560 {

    public static void main(String[] args) {
        Main_560 main_560 = new Main_560();
        System.out.println(main_560.subarraySum_solution2(new int[]{1, 1, 1}, 2));
        System.out.println(main_560.subarraySum_solution3(new int[]{1, 1, 1}, 2));
        System.out.println(main_560.subarraySum_solution4(new int[]{1, 1, 1}, 2));
        System.out.println(main_560.subarraySum_mysolution(new int[]{1, 1, 1}, 2));
    }



    // Solution1: Brute Force
    // Time: O(N^3)
    // Space: O(1)
    // Omitted




    // Solution2: Using Cumulative sum
    // Time: O(N*N)
    // Space: O(N)
    public int subarraySum_solution2(int[] nums, int k) {
        int count = 0;
        int[] sum = new int[nums.length + 1];
        sum[0] = 0;
        for (int i = 1; i < sum.length; i++) {
            sum[i] = sum[i - 1] + nums[i - 1];
        }
        for (int start = 0; start < sum.length - 1; start++) {
            for (int end = start + 1; end < sum.length; end++) {
                if (sum[end] - sum[start] == k)
                    count++;
            }
        }
        return count;
    }




    // Solution3: Without space
    // Time: O(N*N)
    // Space: O(1)
    public int subarraySum_solution3(int[] nums, int k) {
        int count = 0;
        for (int start = 0; start < nums.length; start++) {
            int sum = 0;
            for (int end = start; end < nums.length; end++) {
                sum += nums[end];
                if (sum == k)
                    count++;
            }
        }
        return count;
    }




    // Solution4: Using HashMap   --> Transferred to 2Sum Problem
    // Time: O(N)
    // Space: O(N)
    // Ref: https://leetcode.com/problems/subarray-sum-equals-k/discuss/102106/java-solution-presum-hashmap
    public int subarraySum_solution4(int[] nums, int k) {
        int sum = 0, cnt = 0;
        Map<Integer, Integer> preSum = new HashMap<>();
        preSum.put(0, 1);

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (preSum.containsKey(sum - k)) {
                // Note: KEY point here: preSum.get(sum-k)
                // Explanation: if we know SUM[0, i - 1] and SUM[0, j], then we can easily get SUM[i, j] via (SUM[0, j] - SUM[0, i-1]). NOTE: j ~ i-1 区间的长度是可变的.
                cnt += preSum.get(sum - k);
            }
            preSum.put(sum, preSum.getOrDefault(sum, 0) + 1);
        }
        return cnt;
    }



    // My Solution: Sliding window  (Similar to Solution3 on leetcode.com)
    // Time: O(N*N)
    // Space: O(1)
    public int subarraySum_mysolution(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0] == k ? 1 : 0;
        }

        int n = nums.length;        // n >= 2
        int cnt = 0;
        for (int len = 1; len <= n; len++) {
            int start = 0;
            int sum;
            if (start + len - 1 >= n) {     // start + len - 1 >= n
                break;
            }

            sum = calcSum(nums, start, start + len - 1);
            while (true) {                  // start + len - 1 < n
                if (sum == k)
                    cnt++;
                if (start + len == n) {
                    break;
                }
                sum -= nums[start];
                sum += nums[start + len];
                start++;
            }
        }
        return cnt;
    }

    private int calcSum(int[] nums, int start, int end) {
        int res = 0;
        for (int i = start; i <= end; i++) {
            res += nums[i];
        }
        return res;
    }


}
