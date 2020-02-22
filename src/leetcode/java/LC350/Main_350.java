package leetcode.java.LC350;

import java.util.*;

public class Main_350 {
    public static void main(String[] args) {
        Main_350 main_350 = new Main_350();
        int[] nums1 = {4,9,5,4,8,8};
        int[] nums2 = {9,4,9,8,4};
        System.out.println(Arrays.toString(main_350.intersect(nums1, nums2)));
        System.out.println(Arrays.toString(main_350.intersect_s2(nums1, nums2)));
    }

    // Solution1: HashMap     // 经常用HashMap解决Frequency的问题
    // Time: O(N+M)   Space: O(min(N+M))
    public int[] intersect(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) {   // 调节num1和num2数组长度
            return intersect(nums2, nums1);
        }

        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (Integer num : nums1)
            map.put(num, map.getOrDefault(num, 0) + 1);

        Queue<Integer> queue = new LinkedList<Integer>();
        for (Integer num : nums2) {
            if (map.containsKey(num) && map.get(num) > 0) {
                queue.offer(num);
                map.put(num, map.get(num) - 1);
            }
        }

        // 我们忽略存储答案用的空间，因为它对算法本身并不重要
        int idx = 0;
        int[] result = new int[queue.size()];
        while (!queue.isEmpty()) {
            result[idx++] = queue.remove();
        }
        return result;
    }


    // Solution2: Sort + Two Pointers
    // Time: O(NlogN + MlogM)  Space: O(1)
    public int[] intersect_s2(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);

        int[] result = new int[nums1.length > nums2.length ? nums2.length : nums1.length];
        int i = 0, j = 0, k = 0;
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] < nums2[j])
                i++;
            else if (nums1[i] > nums2[j])
                j++;
            else if (nums1[i] == nums2[j]) {
                result[k++] = nums1[i++];
                j++;
            }
        }
        return Arrays.copyOf(result, k);
    }
}
