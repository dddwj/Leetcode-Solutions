package leetcode.java.LC78;

import java.util.ArrayList;
import java.util.List;

public class Main_78 {
    public static void main(String[] args) {
        Main_78 main_78 = new Main_78();
        main_78.subsets(new int[]{1,2,3, 4}).forEach(System.out::println);
    }

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        getAns(nums, 0, new ArrayList<>(), ans);
        return ans;
    }

    int m = 0;

    private void getAns(int[] nums, int start, ArrayList<Integer> temp, List<List<Integer>> ans) {
        System.out.println(m++);
        ans.add(new ArrayList<>(temp));
        for (int i = start; i < nums.length; i++) {
            temp.add(nums[i]);
            getAns(nums, i + 1, temp, ans);
            temp.remove(temp.size() - 1);
        }
    }
}
