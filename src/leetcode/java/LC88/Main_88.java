package leetcode.java.LC88;

import java.util.Arrays;

/*
    GREAT Tutorial: https://leetcode-cn.com/problems/merge-sorted-array/solution/he-bing-liang-ge-you-xu-shu-zu-by-leetcode/
 */

public class Main_88 {
    public static void main(String[] args) {
        Main_88 main_88 = new Main_88();
        int[] nums1 = {1, 7, 11, 0, 0, 0};
        int[] nums2 = {8, 8, 12};
        main_88.merge_solution2(nums1, 3, nums2, 3);
        System.out.println(Arrays.toString(nums1));
    }

    // Solution1:  Merge + Sort  (Built-in Functions)
    // Time: O((N+M)log(N+M))
    // Space: O(1)
    public void merge_solution1(int[] nums1, int m, int[] nums2, int n) {
        System.arraycopy(nums2, 0, nums1, m, n);
        Arrays.sort(nums1);
    }


    // Solution2:  Two Pointers  (front to back)
    // Time: O(N+M)
    // Space: O(M)  extra space for copy nums1
    public void merge_solution2(int[] nums1, int m, int[] nums2, int n) {
        // Make a copy of nums1.
        int [] nums1_copy = new int[m];
        System.arraycopy(nums1, 0, nums1_copy, 0, m);

        // Two get pointers for nums1_copy and nums2.
        int p1 = 0;
        int p2 = 0;

        // Set pointer for result (i.e. nums1)
        int p = 0;

        // Compare elements from nums1_copy and nums2
        // and add the smallest one into nums1.
        while ((p1 < m) && (p2 < n))
            nums1[p++] = (nums1_copy[p1] < nums2[p2]) ? nums1_copy[p1++] : nums2[p2++];

        // if there are still elements to add
        if (p1 < m)
            System.arraycopy(nums1_copy, p1, nums1, p1 + p2, m + n - p1 - p2);
        if (p2 < n)
            System.arraycopy(nums2, p2, nums1, p1 + p2, m + n - p1 - p2);
    }


    // Solution3:  Two Pointers (back to front) AD: No extra space needed!
    // Time: O(N+M)  Space: O(1)
    // Note: 数组合并的好方法，从末尾/没有信息的地方开始。
    public void merge_solution3(int[] nums1, int m, int[] nums2, int n) {
        int i = m + n - 1;
        m--;
        n--;
        while (m >= 0 && n >= 0) {
            if (nums1[m] < nums2[n]) {
                nums1[i] = nums2[n];
                i--;
                n--;
            } else {
                nums1[i] = nums1[m];
                i--;
                m--;
            }
        }

        while (n >= 0) {
            nums1[i] = nums2[n];
            i--;
            n--;
        }
    }
}
