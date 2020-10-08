package leetcode.java.LC78;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

// Great Tutorial: https://labuladong.gitbook.io/algo/suan-fa-si-wei-xi-lie/hui-su-suan-fa-xiang-jie-xiu-ding-ban
//                 https://mp.weixin.qq.com/s/qT6WgR6Qwn7ayZkI3AineA
// See also: *LC46*, *LC51*, LC37, LC77, LC22, LC39

public class Main_78 {
    public static void main(String[] args) {
        Main_78 main_78 = new Main_78();
//        main_78.subsets_solution3(new int[]{1,2,3,4}).forEach(System.out::println);
        main_78.subsets_solution1(new int[]{0,1,2,4}).forEach(System.out::println);
    }

    /*
    Backtrack Framework:
        result = []
        def backtrack(路径, 选择列表):
            if 满足结束条件:
                result.add(路径)
                return

            for 选择 in 选择列表:
                做选择
                backtrack(路径, 选择列表)
                撤销选择
    */


    // Solution1: Classic Backtrack
    // Note: 经典的回溯算法!!
    // Ref: https://mp.weixin.qq.com/s/qT6WgR6Qwn7ayZkI3AineA
    // See also: LC46
    // Time: O(N * (2^N)), N for creating extraList,  2^N for traversal
    // Space: O(N) for recursion stack
    public List<List<Integer>> subsets_solution1(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        backtrack_solution1(nums, 0, new ArrayList<>(), res);
        return res;
    }

    private void backtrack_solution1(int[] nums, int start, ArrayList<Integer> track, List<List<Integer>> res) {
        res.add(new ArrayList<>(track));
        for (int i = start; i < nums.length; i++) {
            track.add(nums[i]);
            backtrack_solution1(nums, i + 1, track, res);
            track.remove(track.size() - 1);
        }
    }



    // Solution2: Backtrack  (Math Induction)
    // Ref: (See pdf) https://mp.weixin.qq.com/s/qT6WgR6Qwn7ayZkI3AineA
    // Time: O(N * (2^N)), N for creating extraList,  2^N for traversal
    // Space: O(N) for recursion stack
    public List<List<Integer>> subsets_solution2(int[] nums) {
        LinkedList<Integer> remain = new LinkedList<>();
        for (int num : nums)
            remain.add(num);
        return backtrack_solution2(remain);
    }

    private List<List<Integer>> backtrack_solution2(LinkedList<Integer> remain) {
        if (remain.isEmpty())
            return new LinkedList<List<Integer>>(){{
                add(new LinkedList<>());
            }};

        int n = remain.removeLast();

        List<List<Integer>> res = backtrack_solution2(remain);

        int size = res.size();          // res.size is doubled once every recursion --> Time complexity = O(2^N)
        for (int i = 0; i < size; i++) {
            List<Integer> extraList = new ArrayList<>(res.get(i));      // O(N)
            extraList.add(n);
            res.add(extraList);
        }
        return res;
    }



    // Solution3: Bit Manipulation
    // Ref: https://leetcode-cn.com/problems/subsets/solution/zi-ji-by-leetcode-solution/
    // Time: O(N * (2^N)),  N for creating buffer, 2^N for traversal
    // Space: O(N) for buffer
    public List<List<Integer>> subsets_solution3(int[] nums) {
        int n = nums.length;
        List<Integer> buffer = new ArrayList<>();
        List<List<Integer>> ans = new ArrayList<List<Integer>>();

        for (int mask = 0; mask < (1 << n); mask++) {
            buffer.clear();
            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) != 0) {
                    buffer.add(nums[i]);
                }
            }
            ans.add(new ArrayList<>(buffer));
        }
        return ans;
    }
}
