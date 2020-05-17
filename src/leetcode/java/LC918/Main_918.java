package leetcode.java.LC918;

public class Main_918 {

    // See also: LC53

    public static void main(String[] args) {
        Main_918 main_918 = new Main_918();
        System.out.println(main_918.maxSubarraySumCircular(new int[]{5, -3, 5}));
        System.out.println(main_918.maxSubarraySumCircular(new int[]{-2, -3, -1, -4}));
        System.out.println(main_918.maxSubarraySumCircular(new int[]{-2, 3, 1, -4}));
    }


    // Solution: Greedy:  local & global
    // Ref: https://www.bilibili.com/video/BV1c5411s7jZ/
    // Time: O(N)
    // Space: O(1)
    public int maxSubarraySumCircular(int[] A) {
        int cur_max = A[0], glo_max = A[0],
                cur_min = A[0], glo_min = A[0];
        int sum = A[0];

        for (int i = 1; i < A.length; i++) {
            int n = A[i];
            sum += n;

            // Scenario 1: find max sum in an array
            //             LC53 Solution2
            cur_max = Math.max(n, cur_max + n);
            glo_max = Math.max(cur_max, glo_max);

            // Scenario 2: find combined max sum in two sides of an array
            //              <===>  find min sum in an array,   so that:  max = sum - min
            cur_min = Math.min(n, cur_min + n);
            glo_min = Math.min(cur_min, glo_min);
        }

        if (glo_min == sum) // every element is negative, which means `scenario 2` falls back into `scenario 1`
            return glo_max;
        else
            return Math.max(glo_max, sum - glo_min);       // compare the max sums from `scenario 1 & 2`
    }

}
