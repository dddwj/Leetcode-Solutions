package memos.Sort;

import java.util.Arrays;

// QuickSort
// Time: O(NlogN),  worst: O(N*N)
// Space: O(N)
public class QuickSort_New {

    public static void main(String[] args) {
        QuickSort_New quicksort = new QuickSort_New();
//        int[] res = quicksort.sort(new int[] {2,5,3,1,9});
        int[] res = quicksort.sort(new int[]{9,8,7,6,5});
        System.out.println(Arrays.toString(res));
    }



    public int[] sort(int[] nums) {
        if (nums == null || nums.length <= 1)
            return nums;

//        List<Integer> list = new ArrayList<>();
//        for (int num : nums)
//            list.add(num);
//        Collections.shuffle(list);
//        int[] res = new int[list.size()];
//        for (int i = 0; i < list.size(); i++)
//            res[i] = list.get(i);
        int[] res = nums;

        sort(res, 0, nums.length - 1);
        return res;
    }

    public void sort(int[] nums, int left, int right) {
        if (left < right) {
            int index = partition(nums, left, right);
            sort(nums, left, index - 1);
            sort(nums, index + 1, right);
        }
    }



    public int partition(int[] nums, int left, int right) {
        int pivot = left;
        int i = left, j = right;
        while (i < j) {
            while (nums[j] >= nums[pivot] && i < j) {
                j--;
            }
            while (nums[i] <= nums[pivot] && i < j) {
                i++;
            }
            swap(nums, i, j);
        }
        swap(nums, left, i);
        return i;
    }


    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }


}
