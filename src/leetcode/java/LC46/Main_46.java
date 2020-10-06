package leetcode.java.LC46;

import java.util.*;

// Great Tutorial: https://labuladong.gitbook.io/algo/suan-fa-si-wei-xi-lie/hui-su-suan-fa-xiang-jie-xiu-ding-ban
// See also: LC39, LC77, LC78, LC22

public class Main_46 {

    public static void main(String[] args) {
        Main_46 main_46 = new Main_46();
        // main_46.permute_solution1(new int[]{1, 2, 3, 4, 5}).forEach(System.out::println);
        main_46.permute_solution2(new int[]{1, 2, 3, 4, 5}).forEach(System.out::println);
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


    // Solution1: Backtrack
    // Note: 经典的回溯(排列)算法题!!
    // Ref: https://labuladong.gitbook.io/algo/suan-fa-si-wei-xi-lie/zi-ji-pai-lie-zu-he
    // Time: bigger than O(N!), Need to traverse through the whole tree.
    // Space: O(N) for recursion stack
    public List<List<Integer>> permute_solution1(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        backtrack(nums, new ArrayList<>(), ans);
        return ans;
    }

    private void backtrack(int[] nums, ArrayList<Integer> track, List<List<Integer>> ans) {
        if (track.size() == nums.length) {
            ans.add(new LinkedList<>(track)); // Correct. Reason: Must do hard copy.
                                              // Wrong: ans.add(track);
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (track.contains(nums[i]))
                continue;
            track.add(nums[i]);
            backtrack(nums, track, ans);
            track.remove(track.size() - 1);
        }
    }



    // Solution2: Search Backtrack
    // Ref: https://leetcode-cn.com/problems/permutations/solution/quan-pai-lie-by-leetcode-solution-2/
    // Time: O(N * N!),   N! for traversal, N for creating answer list
    // Space: O(N) for recursion stack
    public List<List<Integer>> permute_solution2(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();

        List<Integer> output = new ArrayList<Integer>();
        for (int num : nums) {
            output.add(num);
        }

        int n = nums.length;
        backtrack_2(n, output, res, 0);
        return res;
    }

    private void backtrack_2(int n, List<Integer> output, List<List<Integer>> res, int first) {
        if (first == n) {
            res.add(new ArrayList<>(output));
            return;
        }

        for (int i = first; i < n; i++) {
            Collections.swap(output, first, i);
            backtrack_2(n, output, res, first + 1);
            Collections.swap(output, first, i);
        }
    }
}
