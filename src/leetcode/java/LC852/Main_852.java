package leetcode.java.LC852;

// See also: LC875, LC1011

public class Main_852 {

    // Great Tutorial: https://labuladong.gitbook.io/algo/suan-fa-si-wei-xi-lie/er-fen-cha-zhao-xiang-jie

    public static void main(String[] args) {
        Main_852 main_852 = new Main_852();
        // System.out.println(main_852.peakIndexInMountainArray_solution1(new int[]{1, 2, 3, 4, 1, 0}));
        System.out.println(main_852.peakIndexInMountainArray_solution21(new int[]{1, 2, 3, 4, 1, 0}));
        System.out.println(main_852.peakIndexInMountainArray_solution22(new int[]{1, 2, 3, 4, 1, 0}));
    }

    // Solution1: Linear
    // Time: O(N)
    // Space: O(1)
    public int peakIndexInMountainArray_solution1(int[] arr) {
        if (arr == null || arr.length < 3)
            return 0;
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] > arr[i+1])
                return i;
        }
        return 0;
    }


    // Solution2: Binary Search (Implementation #1, Preferred)
    // Time: O(lgN)
    // Space: O(1)
    // Ref: https://leetcode-cn.com/problems/peak-index-in-a-mountain-array/solution/er-fen-cha-zhao-liang-chong-mo-ban-leftrighthe-lef/
    public int peakIndexInMountainArray_solution21(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while(left <= right){                       // 最好用 `<=`, 别用`<`, 参见：https://labuladong.gitbook.io/algo/suan-fa-si-wei-xi-lie/er-fen-cha-zhao-xiang-jie
            int mid = left + (right - left) / 2;
            if(nums[mid] > nums[mid + 1]
                    && nums[mid] > nums[mid - 1]){
                return mid;
            }
            else if(nums[mid] > nums[mid + 1]){
                right = mid - 1;
            }
            else if (nums[mid] < nums[mid + 1]){
                left = mid + 1;
            }
        }

        return -1;      // 这里return什么都可以，因为对于此题来说，在循环体内一定会返回
    }


    // Solution2: Binary Search (Implementation #2)
    // Time: O(lgN)
    // Space: O(1)
    // Ref: https://leetcode-cn.com/problems/peak-index-in-a-mountain-array/solution/er-fen-cha-zhao-liang-chong-mo-ban-leftrighthe-lef/
    public int peakIndexInMountainArray_solution22(int[] arr) {
        if (arr == null || arr.length < 3)
            return 0;

        int left = 0;
        int right = arr.length - 1;
        while (left < right){
            int mid = left + (right - left) / 2;
            // 缩减区间为[mid+1,right]
            if (arr[mid] < arr[mid + 1]){      // Note: 二分法中不判定mid+1越界，因为循环内lo永远小于hi，所以mid不可能等于hi.
                left = mid + 1;
            }
            // 缩减区间为[left,mid]
            else {
                right = mid;
            }
        }

        return left;    // left = right时退出循环，返回left或right是一样的
    }
}
