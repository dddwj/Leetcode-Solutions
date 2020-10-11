package leetcode.java.LC1011;

// See also: LC875, LC852

public class Main_1011 {

    // Great Tutorial: https://labuladong.gitbook.io/algo/gao-pin-mian-shi-xi-lie/koko-tou-xiang-jiao

    public static void main(String[] args) {
        Main_1011 main_1011 = new Main_1011();
        System.out.println(main_1011.shipWithinDays_solution2(new int[]{3, 2, 2, 4, 1, 4}, 3));
        System.out.println(main_1011.shipWithinDays_solution2(new int[]{1,2,3,1,1}, 4));
        System.out.println(main_1011.shipWithinDays_solution2(new int[]{1,2,3,4,5,6,7,8,9,10}, 5));
    }

    // Solution1: Brute Force (Linear)
    // Time: O(N * W), where W = len(weights)
    // Space: O(1)
    public int shipWithinDays_solution1(int[] weights, int D) {
        int sumCapacity = 0;
        int leastCapacity = 0;
        for (int weight : weights) {
            sumCapacity += weight;
            leastCapacity = Math.max(leastCapacity, weight);
        }

        for (int cap = leastCapacity; cap < sumCapacity; cap++) {
            if (needDays(cap, weights) <= D)
                return cap;
        }

        return sumCapacity;
    }


    // Solution2: Binary Search (Optimized from solution1)
    // Time: O(lgN * W), where W = len(weights)
    // Space: O(1)
    public int shipWithinDays_solution2(int[] weights, int D) {
        int sumCapacity = 0;
        int leastCapacity = 0;
        for (int weight : weights) {
            sumCapacity += weight;
            leastCapacity = Math.max(leastCapacity, weight);
        }

        int left = leastCapacity, right = sumCapacity + 1;
        while (left < right) {
            int mid = (right - left) / 2 + left;
            if (needDays(mid, weights) <= D) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }


    private int needDays(int cap, int[] weights) {
        int day = 1;
        int todayRemain = cap;
        for (int weight : weights) {
            if (weight <= todayRemain) {
                todayRemain -= weight;
            } else {
                day++;
                todayRemain = cap;
                todayRemain -= weight;
            }
        }
        // System.out.println("cap: " + cap + " day: " + day);
        return day;
    }

}
