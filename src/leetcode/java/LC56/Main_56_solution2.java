package leetcode.java.LC56;

import java.util.*;


// Solution2: Sort + Merge one by one
// Time: O(NlgN) (for sorting)
// Space: O(1) or O(N) (sorting may need space)
// Ref: https://leetcode.wang/leetCode-56-Merge-Intervals.html
public class Main_56_solution2 {
    public static void main(String[] args) {
        Main_56_solution2 main_56 = new Main_56_solution2();
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
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        /* The above is EQUIVALENT TO:
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
         */

        LinkedList<Interval> merged = new LinkedList<Interval>();
        for (int[] interval : intervals) {
            if (merged.isEmpty() || merged.getLast().end < interval[0]) {
                merged.add(new Interval(interval[0], interval[1]));
            } else {
                merged.getLast().end = Math.max(merged.getLast().end, interval[1]);
            }
        }

        int[][] res = new int[merged.size()][2];
        for (int i = 0; i < res.length; i++) {
            res[i][0] = merged.get(i).start;
            res[i][1] = merged.get(i).end;
        }
        return res;
    }

}
