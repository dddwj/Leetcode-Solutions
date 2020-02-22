package leetcode.java.LC349;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class Main_349 {
    // Solution1: Two Sets
    // Time: O(N+M)  Space: O(N+M)
    public int[] intersection_solution1(int[] nums1, int[] nums2) {
        HashSet<Integer> set1 = new HashSet<Integer>();
        for (Integer n : nums1)
            set1.add(n);
        HashSet<Integer> set2 = new HashSet<Integer>();
        for (Integer n : nums2)
            set2.add(n);

        if (set1.size() < set2.size())
            return set_intersection(set1, set2);
        else
            return set_intersection(set2, set1);
    }

    public int[] set_intersection(HashSet<Integer> set1, HashSet<Integer> set2) {
        int idx = 0;
        int[] output = new int[set1.size()];

        for (Integer s : set1)
            if (set2.contains(s))
                output[idx++] = s;

        return Arrays.copyOf(output, idx);  // 一定要返回Arrays.copyOf, 否则返回的列表长度仍为set1.size()
    }

    // Solution2: Built-in Set Intersection
    // Time:  avg:O(N+M)  worst:O(N*M)
    // Space: worst: O(N+M)
    public int[] intersection(int[] nums1, int[] nums2) {
        HashSet<Integer> set1 = new HashSet<Integer>();
        for (Integer n : nums1)
            set1.add(n);
        HashSet<Integer> set2 = new HashSet<Integer>();
        for (Integer n : nums2)
            set2.add(n);

        set1.retainAll(set2);

        int[] output = new int[set1.size()];
        int idx = 0;
        for (Integer s : set1)
            output[idx++] = s;
        return output;
    }


    public static void main(String[] args) {
        Main_349 main_349 = new Main_349();
        int[] output = main_349.intersection(new int[] {1, 2, 4, 6, 3, 9, 10, 11}, new int[] {1, 2, 3, 4, 5, 6});
        System.out.println(Arrays.toString(output));

    }
}
