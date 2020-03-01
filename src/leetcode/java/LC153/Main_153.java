package leetcode.java.LC153;

public class Main_153 {
    public static void main(String[] args) {
        Main_153 main_153 = new Main_153();
        System.out.println(main_153.findMin_solution2(new int[]{3, 4, 5, 6, 0, 1, 2}));
    }

    /*
    * see also:
    *   剑指offer: '面试题11' (P82)
    *   Leetcode 154
    * */


    // Solution1:  Brute Force: One-pass
    // Time: O(N)   Space: O(1)
    public int findMin_solution1(int[] nums) {
        if (nums.length == 0)
            return Integer.MIN_VALUE;

        int min = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            min = Math.min(min, nums[i]);
        }
        return min;
    }


    // My Solution: Binary Search:  Search for inflection point    (similar to Solution1 on Leetcode.com)
    // -- see also: 剑指offer '面试题11'，P82。
    // Time: O(logN)  Space: O(1)
    public int findMin_mysolution(int[] nums) {
        // If the list has just one element then return that element.
        if (nums.length == 1) {
            return nums[0];
        }

        int left = 0;
        int right = nums.length - 1;
        int min = Integer.MAX_VALUE;

        // if the last element is greater than the first element then there is no rotation.
        // e.g. 1 < 2 < 3 < 4 < 5 < 7. Already sorted array.
        // Hence the smallest element is first element. A[0]
        if (nums[right] > nums[0]) {
            return nums[0];
        }

        // Binary search way
        while (left <= right) {
            int mid = left + (right - left) / 2;   // use this instead of (left+right)/2, to avoid MAX_VALUE overflow

            if (nums[mid] > nums[right]) {
                left = mid + 1;
                min = Math.min(min, nums[right]);
            } else if (nums[mid] < nums[right]) {
                right = mid - 1;
                min = Math.min(min, nums[mid]);
            } else if (nums[mid] == nums[right]) {   // i.e.: mid == right == left
                min = Math.min(min, nums[left]);
                break;      // Test case: [4,5,6,7,0,1,2]
            }
        }
        return min;
    }


    // Soution2: Binary Search: Search for inflection point  (Brought from Leetcode 154 Solution)
    // Time: O(logN)  Space: O(1)
    public int findMin_solution2(int[] nums) {
        int lo = 0, hi = nums.length - 1;

        while (lo < hi) {
            int mid = (hi - lo) / 2 + lo;
            if (nums[mid] < nums[hi]) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }
        return nums[lo];
    }
}
