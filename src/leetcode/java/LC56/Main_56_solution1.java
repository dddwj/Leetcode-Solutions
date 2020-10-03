package leetcode.java.LC56;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


// Solution1: Merge one by one, case by case
// Time: O(N*N), where N = size(intervals)
// Space: O(1)
// Ref: https://leetcode.wang/leetCode-56-Merge-Intervals.html
public class Main_56_solution1 {
    public static void main(String[] args) {
        Main_56_solution1 main_56 = new Main_56_solution1();
        int[][] res = main_56.merge(new int[][] {
                {15, 18},
                {1, 3},
                {8, 10},
                {2, 6}
        });
        Arrays.stream(res).forEach(x -> System.out.println(Arrays.toString(x)));
    }

    private class Interval {
        int start;
        int end;

        Interval() {
            start = 0;
            end = 0;
        }

        Interval(int s, int e) {
            start = s;
            end = e;
        }
    }


    public int[][] merge(int[][] intervals) {
        List<Interval> ans = new ArrayList<>();
        if (intervals.length == 0 || intervals[0].length != 2)
            return new int[0][2];

        ans.add(new Interval(intervals[0][0], intervals[0][1]));
        for (int i = 1; i < intervals.length; i++) {
            Interval start = null;
            Interval end = null;

            int i_start = intervals[i][0];
            int i_end = intervals[i][1];
            int size = ans.size();

            List<Interval> in = new ArrayList<>();
            for (int j = 0; j < size; j++) {
                if (i_start >= ans.get(j).start && i_start <= ans.get(j).end) {
                    start = ans.get(j);
                }

                if (i_end >= ans.get(j).start && i_end <= ans.get(j).end) {
                    end = ans.get(j);
                }
                if (i_start < ans.get(j).start && i_end > ans.get(j).end) {
                    in.add(ans.get(j));
                }
            }

            if (in.size() != 0) {
                for (int index = 0; index < in.size(); index++)
                    ans.remove(in.get(index));
            }

            if (equals(start, end)) {
                int s = start == null ? i_start : start.start;
                int e = end == null ? i_end : end.end;
                ans.add(new Interval(s, e));
            } else if (start != null && end != null) {
                ans.add(new Interval(start.start, end.end));
            } else if (start == null) {
                ans.add(new Interval(i_start, i_end));
            }

            if (start != null)
                ans.remove(start);
            if (end != null)
                ans.remove(end);
        }

        int[][] res = new int[ans.size()][2];
        for (int i = 0; i < res.length; i++) {
            res[i][0] = ans.get(i).start;
            res[i][1] = ans.get(i).end;
        }
        return res;
    }

    private boolean equals(Interval start, Interval end) {
        if (start == null && end == null) {
            return false;
        }
        if (start == null || end == null) {
            return true;
        }
        if (start.start == end.start && start.end == end.end) {
            return false;
        }
        return false;
    }
}
