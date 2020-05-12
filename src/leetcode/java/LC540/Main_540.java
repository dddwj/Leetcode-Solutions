package leetcode.java.LC540;

public class Main_540 {

    // See also: LC217, LC287, LC442, LC448


    public static void main(String[] args) {
        Main_540 main_540 = new Main_540();
        System.out.println(main_540.singleNonDuplicate_solution3(new int[]{1, 1, 2, 2, 3, 3, 4}));
        System.out.println(main_540.singleNonDuplicate_solution3(new int[]{1, 1, 2, 3, 3, 4, 4}));
        System.out.println(main_540.singleNonDuplicate_solution3(new int[]{1, 1, 2}));
        System.out.println(main_540.singleNonDuplicate_solution3(new int[]{1}));
    }


    // Solution1: Brute Force (One pass)
    // Time: O(N)
    // Space: O(1)
    // Omitted



    // My Solution (Solution2 on Leetcode.com): Binary Search
    // Time: O(lgN)
    // Space: O(1)
    public int singleNonDuplicate_mySolution(int[] nums) {
        if (nums == null || nums.length % 2 == 0)
            return -1;

        int lo = 0, hi = nums.length - 1;

        while (lo < hi) {
            int mid = (hi - lo) / 2 + lo;
            int target = nums[mid];
            int leftCount = mid - lo, rightCount = hi - mid;   // Here: rightCount === leftCount
            if (target == nums[mid - 1]) {
                leftCount--;
            } else if (target == nums[mid + 1]) {
                rightCount--;
            } else {
                return target;
            }

            if (leftCount % 2 != 0) {
                hi = lo + leftCount - 1;
            } else if (rightCount % 2 != 0) {
                lo = hi - rightCount + 1;
            }
        }

        return nums[lo];
    }



    // Solution3: Binary Search on even position elements (Optimized on Solution2)
    // Time: O(lg(N/2)) = O(lg(N))
    // Space: O(1)
    // Ref: https://leetcode-cn.com/problems/single-element-in-a-sorted-array/solution/you-xu-shu-zu-zhong-de-dan-yi-yuan-su-by-leetcode/
    public int singleNonDuplicate_solution3(int[] nums) {
        int lo = 0;
        int hi = nums.length - 1;
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (mid % 2 == 1)
                mid--;
            if (nums[mid] == nums[mid + 1]) {
                lo = mid + 2;
            } else {
                hi = mid;
            }
        }
        return nums[lo];
    }


    // Solution4: Math (Exclusive OR)
    // Time: O(N)
    // Space: O(1)
    // ref: https://leetcode-cn.com/problems/single-element-in-a-sorted-array/solution/san-xing-dai-ma-qiao-yong-yi-huo-yun-suan-fu-ji-ba/
    // Key point: A^A^B^C^C=B
    public int singleNonDuplicate_solution4(int[] nums) {
        int first = nums[0];
        for (int i = 1; i < nums.length; i++) {
            first = first ^ nums[i];
        }
        return  first;
    }

}

