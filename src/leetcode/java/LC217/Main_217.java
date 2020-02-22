package leetcode.java.LC217;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Main_217 {
    public static void main(String[] args) {
        Main_217 main_217 = new Main_217();
        int[] nums = {1,9,1,7,1,3,3,4,3,2,4,2};
//        int[] nums = {1,2,3};
        System.out.println(main_217.containsDuplicate_solution3(nums));
    }

    // Solution2: Sorting
    // Time: O(NlogN)  Space: O(1)
    public boolean containsDuplicate_solution2(int[] nums) {
        // Better to use clone(). Or the sort operation would be performed on the original array
        Arrays.sort(nums.clone());  // HeapSort
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] == nums[i + 1]) {
                return true;
            }
        }
        return false;
    }

    // Solution3: Hash Table
    // Time: O(N)  Space: O(N)  【哈希表的(搜索/添加)时间复杂度：O(1)、空间复杂度：O(N)】
    public boolean containsDuplicate_solution3(int[] nums) {
        Set<Integer> set = new HashSet<>(nums.length);
        for (int x: nums) {
            if (set.contains(x))
                return true;
            set.add(x);
        }
        return false;
    }
}
