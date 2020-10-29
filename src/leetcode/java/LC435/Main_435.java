package leetcode.java.LC435;

import java.util.Arrays;

public class Main_435 {

    public static void main(String[] args) {
        Main_435 main_435 = new Main_435();
        System.out.println(main_435.eraseOverlapIntervals_solution4(new int[][]{
                {1, 2},
                {2, 3},
                {3, 4},
                {1, 3}
        }));
        System.out.println(main_435.eraseOverlapIntervals_solution4(new int[][]{
                {1, 2},
                {1, 2},
                {1, 2}
        }));
        System.out.println(main_435.eraseOverlapIntervals_solution4(new int[][]{
                {1, 2},
                {2, 3}
        }));
    }



    // Solution4 on leetcode-cn.com: Greedy
    // Ref: https://labuladong.gitbook.io/algo/dong-tai-gui-hua-xi-lie/tan-xin-suan-fa-zhi-qu-jian-tiao-du-wen-ti
    // Time: O(NlgN)  for sorting
    // Space: O(1)
    public int eraseOverlapIntervals_solution4(int[][] intervals) {
        if (intervals == null || intervals.length == 0)
            return 0;

        Arrays.sort(intervals, (i1, i2) -> i1[1] - i2[1]);

        int count = 0;
        int end = intervals[0][1], start = intervals[0][0];
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] < end) {
                count++;
                continue;
            }
            start = intervals[i][0];
            end = intervals[i][1];
        }

        return count;
    }
}
