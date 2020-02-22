package leetcode.java.LC189;

import java.util.Arrays;

public class Main_189 {

    public static void main(String[] args) {
        int[] nums = {1,2,3,4,5,6,7};
//        int[] nums = {1, 2};
        Main_189 main_189 = new Main_189();
        main_189.rotate(nums, 3);
        System.out.println(Arrays.toString(nums));
    }

    // My Solution1: Brute Force
    // Time: O(kN)  Space: O(1)
    public int[] rotate_solution1(int[] nums, int k) {
        if (nums.length <= 1)   // Input cannot be rotated
            return null;

        for (int i = 0; i < k; i++) {  // Rotate one at a time
            int lastElement = nums[nums.length - 1];
            for (int j = nums.length - 1; j > 0; j--)
                nums[j] =  nums[j - 1];
            nums[0] = lastElement;
        }
        return nums;
    }


    // My Solution2: Using Extra Array of Last K elements
    // Time:  O(N)  Space:  O(k)
    public void rotate_mysolution(int[] nums, int k) {
        if (nums.length <= 1)
            return;

        if (nums.length < k)
            k = k % nums.length;

        int[] lastK = new int[k];
        for (int i = 0; i < k; i++) {
            lastK[i] = nums[nums.length - k + i];
        }

        for (int i = nums.length - k; i > 0; i--) {
            nums[i + k - 1] = nums[i - 1];
        }
        for (int i = 0; i < k; i++) {
            nums[i] = lastK[i];
        }
    }

    // Solution2: Using Extra Array
    // Time: O(N)  Space:  O(N)
    public void rotate_solution2(int[] nums, int k) {
        // Extra Array
        int[] a = new int[nums.length];

        // Calc every element with a delay of k
        for (int i = 0; i < nums.length; i++) {
            a[(i + k) % nums.length] = nums[i];    //(i+k)取余，很精辟！
        }

        // Copy Array 'a' to Array 'nums'
        System.arraycopy(a, 0, nums, 0, nums.length);
    }

    // Solution3: Cyclic Replacements
    // Time: O(N)  Space: O(1)
    // 先放一放
    public void rotate(int[] nums, int k) {


    }


    // Solution4: Using Reverse
    // Time:  O(n)  Space:  O(1)
    public void rotate_solution4(int[] nums, int k) {
        k = k % nums.length;
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }
    public void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }

}

