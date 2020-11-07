package leetcode.java.LC4;

// Ref: https://leetcode.wang/leetCode-4-Median-of-Two-Sorted-Arrays.html

import java.util.PriorityQueue;
import java.util.Queue;

public class Main_4 {

    public static void main(String[] args) {
        Main_4 main_4 = new Main_4();
        System.out.println(main_4.findMedianSortedArrays_solution2(new int[]{0, 0}, new int[]{0, 1}));
        System.out.println(main_4.findMedianSortedArrays_solution2(new int[]{}, new int[]{1}));
        System.out.println(main_4.findMedianSortedArrays_solution2(new int[]{1,1,5}, new int[]{5,9}));
        System.out.println(main_4.findMedianSortedArrays_solution2(new int[]{}, new int[]{5,9}));
    }


    // My Solution: Heap Sort
    // Time: O(NlogN)
    // Space: O(N)
    public double findMedianSortedArrays_mysolution(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null)
            return 0;

        // O(NlogN)
        Queue<Integer> all = new PriorityQueue<>((i1, i2) -> i1 - i2);
        for (int num : nums1)
            all.offer(num);
        for (int num : nums2)
            all.offer(num);

        int count = nums1.length + nums2.length;

        for (int i = 0; i < (count-1)/2; i++) {
            all.poll();
        }

        // O(NlogN)
        if (count % 2 == 1)
            return all.poll();

        return (all.poll() + all.poll()) / 2.0;
    }


    // Solution0: Brute Force (Merge two arrays to another array)
    // Time: O(N)
    // Space: O(N)
    // Omitted



    // Solution1: Brute Force (Do not merge two arrays to another array)
    // Time: O(N),  where N = len(num1) + len(num2)
    // Space: O(1)
    public double findMedianSortedArrays_solution1(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;
        int len = m + n;
        int left = -1, right = -1;
        int aStart = 0, bStart = 0;

        for (int i = 0; i <= len / 2; i++) {
            left = right;

            if (aStart >= m && bStart >= n)
                break;

            if (bStart >= n) {
                right = nums1[aStart];
                aStart++;
                continue;
            }
            if (aStart >= m) {
                right = nums2[bStart];
                bStart++;
                continue;
            }
            if (nums1[aStart] < nums2[bStart]) {
                right = nums1[aStart];
                aStart++;
            }
            else {
                right = nums2[bStart];
                bStart++;
            }
        }

        if ((len & 1) == 0)
            return (left + right) / 2.0;
        else
            return right;
    }



    // Solution2: Binary Search
    // Ref: https://leetcode.wang/leetCode-4-Median-of-Two-Sorted-Arrays.html
    // Time: O(log(M + N))
    //       每进行一次循环，我们就减少 k / 2 个元素，所以时间复杂度是 O（log（k）），而 k = （m + n）/ 2 ，所以最终的复杂也就是 O（log（m + n））。
    // Space:   O(1)
    //       虽然我们用到了递归，但是可以看到这个递归属于尾递归，所以编译器不需要不停地堆栈，所以空间复杂度为 O（1）。
    public double findMedianSortedArrays_solution2(int[] nums1, int[] nums2) {
        int n = nums1.length, m = nums2.length;
        int left = (n + m + 1) / 2, right = (n + m + 2) / 2;

        // 将偶数和奇数的情况合并，如果是奇数，会求两次同样的 k 。
        return (getKth(nums1, 0, n - 1, nums2, 0, m - 1, left)
                + getKth(nums1, 0, n - 1, nums2, 0, m - 1, right))
                / 2.0;
    }

    private double getKth(int[] nums1, int start1, int end1, int[] nums2, int start2, int end2, int k) {
        int len1 = end1 - start1 + 1, len2 = end2 - start2 + 1;
        if (len1 > len2)        // To ensure len1 always < len2
            return getKth(nums2, start2, end2, nums1, start1, end1, k);
        if (len1 == 0)
            return nums2[start2 + k - 1];
        if (k == 1)
            return Math.min(nums1[start1], nums2[start2]);

        int i = start1 + Math.min(len1, k / 2) - 1;
        int j = start2 + Math.min(len2, k / 2) - 1;

        if (nums1[i] > nums2[j]) {
            return getKth(nums1, start1, end1, nums2, j + 1, end2, k - (j - start2 + 1));
        } else {
            return getKth(nums1, i + 1, end1, nums2, start2, end2, k - (i - start1 + 1));
        }
    }

}