package leetcode.java.LC875;

// See also: LC852
// Great Tutorial: https://labuladong.gitbook.io/algo/suan-fa-si-wei-xi-lie/er-fen-cha-zhao-xiang-jie

public class Main_875 {

    public static void main(String[] args) {
        Main_875 main_875 = new Main_875();
        System.out.println(main_875.minEatingSpeed_solution2(new int[]{3, 6, 7, 11}, 8));
        System.out.println(main_875.minEatingSpeed_solution2(new int[]{30,11,23,4,20}, 5));
        System.out.println(main_875.minEatingSpeed_solution2(new int[]{30,11,23,4,20}, 6));
        System.out.println(main_875.minEatingSpeed_solution2(new int[]{312884470}, 312884469));
        System.out.println(main_875.minEatingSpeed_solution2(new int[]{332484035,524908576,855865114,632922376,222257295,690155293,112677673,679580077,337406589,290818316,877337160,901728858,679284947,688210097,692137887,718203285,629455728,941802184},
                823855818));
    }


    // Solution1: Brute Force  (TLE!)
    // Time: O(N * N)
    // Space: O(N)
    public int minEatingSpeed_solution1(int[] piles, int H) {
        if (piles == null || piles.length < 1)
            return -1;

        int maxSpeed = findMax(piles);

        for (int speed = 1; speed <= maxSpeed; speed++) {
            if (canFinish(speed, piles, H))
                return speed;
        }

        return -1;
    }


    // Solution2: Binary Search  (Optimized from solution1)
    // Time: O(N * logN)
    // Space: O(1)
    // Ref: https://labuladong.gitbook.io/algo/gao-pin-mian-shi-xi-lie/koko-tou-xiang-jiao
    public int minEatingSpeed_solution2(int[] piles, int H) {
        if (piles == null || piles.length < 1)
            return -1;

        int maxSpeed = findMax(piles);
        int left = 1, right = maxSpeed + 1;
        while (left < right) {
            int mid = (right - left) / 2 + left;
            if (canFinish(mid, piles, H))
                right = mid;
            else
                left = mid + 1;
        }
        return left;
    }

    private int findMax(int[] piles) {
        int max = 0;
        for (int pile : piles)
            max = Math.max(pile, max);
        return max;
    }

    private boolean canFinish(int speed, int[] piles, int H) {
        long need = 0;          // Set to long <-- Prevent overflowing
        for (int pile : piles) {
            need += (pile / speed + (pile % speed == 0 ? 0 : 1));
        }
        return need <= (long) H;
    }
}
