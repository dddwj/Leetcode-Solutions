package leetcode.java.LC39;

import java.util.ArrayList;
import java.util.List;

// See also: *LC46*, *LC51*, LC37, LC77, LC78, LC22  (Backtrack)

public class Main_39 {

    public static void main(String[] args) {
        Main_39 main_39 = new Main_39();
        // main_39.combinationSum(new int[] {2, 3, 5}, 8).forEach(System.out::println);
        // main_39.combinationSum(new int[] {2, 3, 5}, 10).forEach(System.out::println);
        main_39.combinationSum(new int[] {2, 3, 5}, 3).forEach(System.out::println);
        // main_39.combinationSum(new int[] {2}, 1).forEach(System.out::println);
    }


    // My Solution (Similar to Solution on leetcode-cn): Classic Backtrack
    // Ref: https://leetcode-cn.com/problems/combination-sum/solution/zu-he-zong-he-by-leetcode-solution/
    // Time: ?
    // Space: O(target) for recursion stack (worst case)
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        backtrack(0, 0, new ArrayList<>(), res, candidates, target);
        return res;
    }

    private void backtrack(int start, int sum, List<Integer> track, List<List<Integer>> res, int[] candidates, int target) {
        if (sum > target)
            return;
        if (sum == target) {
            res.add(new ArrayList<>(track));
            return;
        }

        for (int i = start; i < candidates.length; i++) {
            int candidate = candidates[i];
            track.add(candidate);
            backtrack(i, sum + candidate, track, res, candidates, target);
            track.remove(track.size() - 1);
        }
    }


}
