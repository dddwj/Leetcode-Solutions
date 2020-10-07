package leetcode.java.LC77;

import java.util.ArrayList;
import java.util.List;

// See also: *LC46*, LC39, LC78, LC22  (Backtrack)


public class Main_77 {

    public static void main(String[] args) {
        Main_77 main_77 = new Main_77();
        main_77.combine_solution1(4, 2).forEach(System.out::println);
    }


    // Solution1: Classic Backtrack
    // Ref: https://leetcode.wang/leetCode-77-Combinations.html
    //      https://labuladong.gitbook.io/algo/suan-fa-si-wei-xi-lie/hui-su-suan-fa-xiang-jie-xiu-ding-ban
    //      LC78
    // Time: O(N * (2^N)), N for creating extraList,  2^N for traversal
    // Space: O(N) for recursion stack
    public List<List<Integer>> combine_solution1(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        backtrack(1, n, k, new ArrayList<>(), res);
        return res;
    }

    private void backtrack(int start, int n, int k, ArrayList<Integer> track, List<List<Integer>> res) {
        if (track.size() == k) {
            res.add(new ArrayList<>(track));
            return;
        }

        // for (int i = start; i <= n; i++) {                            // Normal version.
        for (int i = start; i <= n - (k - track.size()) + 1; i++) {      // Optimization version. (eliminating some unnecessary iteration)
            track.add(i);
            backtrack(i + 1, n, k, track, res);
            track.remove(track.size() - 1);
        }
    }


    // Solution2: Classic Backtrack  (Iteration Implementation)
    // Ref: https://leetcode.wang/leetCode-77-Combinations.html
    public List<List<Integer>> combine_solution2(int n, int k) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            temp.add(0);
        }
        int i = 0;
        while (i >= 0) {
            temp.set(i, temp.get(i) + 1);   // 当前数字加 1
            if (temp.get(i) > n)            // 当前数字大于 n，对应回溯法的 i == n + 1，然后回到上一层
                i--;
            else if (i == k - 1)            // 当前数字个数够了
                ans.add(new ArrayList<>(temp));
            else {
                i++;
                temp.set(i, temp.get(i - 1));   // 把下一个数字置为上一个数字，类似于回溯法中的 start
            }
        }
        return ans;
    }

}
