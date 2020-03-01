package leetcode.java.LC154;

public class Main_154 {
    public static void main(String[] args) {
        Main_154 main_154 = new Main_154();
        System.out.println(main_154.findMin(new int[]{1, 1, 1, 0, 1}));
        System.out.println(main_154.findMin(new int[]{3, 4, 5, 6, 0, 1, 2}));
    }



    public int findMin(int[] nums) {
        /**
         和 I 的做法类似, 都是二分法, 每次进入无序的那部分找出最小值
         但是由于有重复值的情况, 需要加入 mid 元素等于 hi 元素的情况
         此时应该将 hi 减 1 防止重复数字是最小元素
         **/
        int lo = 0, hi = nums.length - 1;

        while(lo < hi) {
            int mid = lo+(hi-lo)/2;
            if (nums[mid] > nums[hi])
                lo = mid + 1;
            else if (nums[mid] < nums[hi])
                hi = mid;
            else if (nums[mid] == nums[hi])
                hi--;        // Note: 处理重复数字的精髓
        }
        return nums[lo];
    }
}
