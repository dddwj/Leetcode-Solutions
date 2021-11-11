package leetcode.java.LC81;

// See also: (Series) LC33

public class Main_81 {

    public static void main(String[] args) {
        Main_81 main_81 = new Main_81();
        System.out.println(main_81.search_solution1(new int[]{2, 5, 6, 0, 0, 1, 2}, 0));
        System.out.println(main_81.search_solution1(new int[]{2, 5, 6, 0, 0, 1, 2}, 3));
        System.out.println(main_81.search_solution1(new int[]{1, 3}, 3));
        System.out.println(main_81.search_solution1(new int[]{1, 3}, 5));
        System.out.println(main_81.search_solution1(new int[]{1, 2, 1}, 3));
        System.out.println(main_81.search_solution1(new int[]{1, 2, 1}, 1));
        System.out.println(main_81.search_solution1(new int[]{1, 1, 2, 1}, 1));
        System.out.println(main_81.search_solution1(new int[]{1, 1, 2, 1}, 3));

    }




    // Solution1:  Binary Search
    // Time: O(lgN) ~ O(N)
    // Space: O(1)
    // Ref: LC33 -- Solution (Newer Version)
    //      https://leetcode.com/problems/search-in-rotated-sorted-array-ii/solution/760943
    //      Similar: https://leetcode-cn.com/problems/search-in-rotated-sorted-array-ii/solution/zai-javazhong-ji-bai-liao-100de-yong-hu-by-reedfan/
    public boolean search_solution1(int[] nums, int target) {
        if (nums == null || nums.length == 0)
            return false;
        int n = nums.length;
        if (n == 1)
            return nums[0] == target;

        int left = 0, right = n - 1;
        while (left <= right) {
            while (left < right && nums[left] == nums[left+1])
                left++;
            while (left < right && nums[right] == nums[right-1])
                right--;

            int mid = (right - left) / 2 + left;
            if (nums[mid] == target)
                return true;

            if (nums[left] <= nums[mid]) {       // 左边升序
                if (nums[left] <= target && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else if (nums[mid] <= nums[right]) { // 右边升序
                if (nums[mid] < target && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }

        return false;
    }
}
