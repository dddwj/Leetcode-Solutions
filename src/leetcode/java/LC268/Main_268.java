package leetcode.java.LC268;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Main_268 {

    // ref: https://leetcode-cn.com/problems/missing-number/solution/que-shi-shu-zi-by-leetcode/

    public static void main(String[] args) {
        Main_268 main_268 = new Main_268();
        System.out.println(main_268.missingNumber_solution3(new int[]{0, 2, 3, 6, 5, 4}));

    }

    // Solution1:  Sort + Find
    // Time: O(NlogN)  Space: O(1)
    public int missingNumber_solution1(int[] nums) {
        if (nums.length == 0)
            return 0;

        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (i != nums[i])
                return i;
        }

        return nums.length;
    }


    // Solution2: HashSet
    // Time: O(N)  Space: O(N)
    public int missingNumber_solution2(int[] nums) {
        Set<Integer> numSet = new HashSet<Integer>();
        for (int num : nums) numSet.add(num);

        int expectedNumCount = nums.length + 1;
        for (int number = 0; number < expectedNumCount; number++) {
            if (!numSet.contains(number)) {
                return number;
            }
        }
        return -1;
    }


    // Solution3:  Bit Manipulation
    // Time: O(N)  Space: O(1)
    public int missingNumber_solution3(int[] nums) {
        int missing = nums.length;
        for (int i = 0; i < nums.length; i++) {
            missing ^= i ^ nums[i];
        }
        return missing;
    }


    // Solution4: Mathematics -- Gauss' Formula
    // Time: O(N)  Space: O(1)
    public int missingNumber_solution4(int[] nums) {
        int expectedSum = nums.length * (nums.length + 1) / 2;
        int actualSum = 0;
        for (int num : nums)
            actualSum += num;
        return expectedSum - actualSum;
    }

}
