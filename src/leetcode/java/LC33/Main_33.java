package leetcode.java.LC33;

public class Main_33 {
    public static void main(String[] args) {
        Main_33 main_33 = new Main_33();
        System.out.println(main_33.search_solution11(new int[]{4, 5, 6, 7, 0, 1, 2}, 0));
        System.out.println(main_33.search_solution11(new int[]{4, 5, 6, 7, 0, 1, 2}, 3));
        System.out.println(main_33.search_solution11(new int[]{0, 1, 2, 3 ,4, 5, 6, 7}, 6));
        System.out.println(main_33.search_solution11(new int[]{0, 1, 2, 3, 4, 5, 6, 7}, 8));
        System.out.println(main_33.search_solution11(new int[]{0}, 0));
        System.out.println(main_33.search_solution11(new int[]{0}, 1));
        System.out.println(main_33.search_solution11(new int[]{}, 1));

    }

    // My Solution (Solution1 (Opt1)) Search for inflection point (Brought from Leetcode 153, Leetcode 154)  +   Binary Search in one of two sorted lists
    // Time: O(logN)  Space: O(1)
    public int search_solution11(int[] nums, int target) {
        // Init data
        int length = nums.length;

        // Corner case
        if (length == 0)
            return -1;
        if (length == 1)
            return target == nums[0] ? 0 : -1;


        // Search the minimum point, Time: O(logN)
        int lo = 0, hi = length - 1;
        while (lo < hi) {
            int mi = (hi - lo) / 2 + lo;
            if (nums[mi] > nums[hi]) {
                lo = mi + 1;
            } else if (nums[mi] < nums[hi]) {
                hi = mi;
            }
        }
        int min_index = lo;


        // Do binary search, Time: O(logN)
        if (min_index == 0)
            return binarySearch(nums, target, min_index, length - 1);

        int min = nums[min_index], max = nums[min_index - 1], right = nums[length - 1], left = nums[0];
        if (min <= target && target <= right)
            return binarySearch(nums, target, min_index, length - 1);
        else if (left <= target && target <= max)
            return binarySearch(nums, target, 0, min_index - 1);

        // Nothing found, return -1
        return -1;
    }


    private int binarySearch(int[] nums, int target, int start, int end) {
        while (start <= end) {
            int mid = (end - start) / 2 + start;
            if (nums[mid] > target) {
                end = mid - 1;
            } else if (nums[mid] < target) {
                start = mid + 1;
            } else if (nums[mid] == target)
                return mid;
        }
        return -1;
    }



    // Solution1: (Opt2)
    public int search_solution12(int[] nums, int target) {
        int left = 0;
        int right = nums.length-1;
        int mid = left + (right-left)/2;

        while(left <= right){
            if(nums[mid] == target){
                return mid;
            }

            if(nums[left] <= nums[mid]){  //左边升序
                if(target >= nums[left] && target <= nums[mid]){//在左边范围内
                    right = mid-1;
                }else{//只能从右边找
                    left = mid+1;
                }

            }else{ //右边升序
                if(target >= nums[mid] && target <= nums[right]){//在右边范围内
                    left = mid +1;
                }else{//只能从左边找
                    right = mid-1;
                }

            }
            mid = left + (right-left)/2;
        }

        return -1;  //没找到
    }
}