package leetcode.java.LC228;

import java.util.ArrayList;
import java.util.List;

public class Main_228 {

    public static void main(String[] args) {
        Main_228 main_228 = new Main_228();
        System.out.println(main_228.summaryRanges_mysolution(new int[]{0, 1, 2, 4, 5, 7}));
        System.out.println(main_228.summaryRanges_mysolution(new int[]{1}));
        System.out.println(main_228.summaryRanges_mysolution(new int[]{-1}));

    }

    // Solution1 (My Solution): Brute Force (One pass)
    // Time: O(N)
    // Space: O(1)
    public List<String> summaryRanges_mysolution(int[] nums) {
        if (nums == null || nums.length == 0)
            return new ArrayList<>();

        // Arrays.sort(nums);

        List<String> res = new ArrayList<>();
        int start = 0;
        while (start < nums.length){
            int end = start;
            int ptr = end + 1;
            while (ptr < nums.length) {
                if (nums[ptr] - nums[end] == 1) {
                    ptr++;
                    end++;
                } else
                    break;
            }
            if (end == start) {
                res.add(nums[start] + "");
            } else {
                res.add(nums[start] + "->" + nums[end]);
            }
            start = end + 1;
        }
        return res;
    }


    // Solution2: Solution1 Optimized  (Use Standard Deviation)
    // Ref: https://leetcode.wang/leetcode-228-Summary-Ranges.html
    public List<String> summaryRanges_solution2(int[] nums) {

        List<String> resStr = new ArrayList<>();

        if (nums.length == 0) {
            return resStr;
        }

        List<Range> res = new ArrayList<>();
        helper(nums, 0, nums.length - 1, res);

        for (Range r : res) {
            if (r.start == r.end) {
                resStr.add(Integer.toString(r.start));
            } else {
                resStr.add(r.start + "->" + r.end);
            }
        }

        return resStr;
    }

    private void helper(int[] nums, int i, int j, List<Range> res) {
        if (i == j || nums[j] - nums[i] == j - i) {
            add2res(nums[i], nums[j], res);
            return;
        }

        int m = (i + j) / 2;
        //一半一半的考虑
        helper(nums, i, m, res);
        helper(nums, m + 1, j, res);
    }

    private void add2res(int a, int b, List<Range> res) {
        //判断新加入的范围和之前最后一个范围是否相连
        if (res.isEmpty() || res.get(res.size() - 1).end + 1 != a) {
            res.add(new Range(a, b));
        } else {
            res.get(res.size() - 1).end = b;
        }
    }

    class Range {
        int start;
        int end;

        Range(int s, int e) {
            start = s;
            end = e;
        }
    }

}
