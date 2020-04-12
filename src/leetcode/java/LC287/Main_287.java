package leetcode.java.LC287;

public class Main_287 {
    public static void main(String[] args) {
        Main_287 main_287 = new Main_287();
        System.out.println(main_287.findDuplicate_solution2(new int[]{1, 2, 3, 4, 5, 1, 6, 7, 8}));
    }


    // Solution1: Two Pointers (Floyd's Tortoise and Hare (Cycle Detection))
    // Time: O(N)
    // Space: O(1)
    public int findDuplicate_solution1(int[] nums) {
        int slow = nums[0], fast = nums[0];
        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (fast != slow);

        slow = nums[0];
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow;
    }

    // Solution2: Binary Search
    // Ref: https://leetcode.wang/leetcode-287-Find-the-Duplicate-Number.html
    // Time: O(N * logN)
    // Space: O(1)
    public int findDuplicate_solution2(int[] nums) {
        int n = nums.length - 1;
        int low = 1, high = n;
        while (low < high) {
            int mid = (low + high) >>> 1;
            int count = 0;
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] <= mid) {
                    count++;
                }
            }
            if (count > mid) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }
}
