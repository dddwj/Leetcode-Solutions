package leetcode.java.LC1232;

public class Main_1232 {

    public static void main(String[] args) {

        Main_1232 main_1232 = new Main_1232();
//        System.out.println(main_1232.checkStraightLine(new int[][]{
//                {1, 2},
//                {1, 2},
//                {3, 4},
//                {4, 5},
//                {5, 6},
//                {7, 7}
//        }));
//        System.out.println(main_1232.checkStraightLine(new int[][]{
//                {1, 2},
//                {2, 3},
//                {3, 4},
//                {4, 5},
//                {5, 6},
//                {6, 7}
//        }));
        System.out.println(main_1232.checkStraightLine_solution1(new int[][]{
                {1, 1},
                {2, 2},
                {3, 4},
                {4, 5},
                {5, 6},
                {6, 7}
        }));
    }



    // My Solution: Math
    // Time: O(N)
    // Space: O(1)
    public boolean checkStraightLine_mysolution(int[][] coordinates) {
        if (coordinates.length == 2)
            return true;

        int[] p0 = coordinates[0], p1 = coordinates[1];
        if (p1[0] == p0[0]) {
            for (int i = 1; i < coordinates.length; i++) {
                if (coordinates[i][0] != p0[0])
                    return false;
            }
            return true;
        }

        double k = (double)(p1[1] - p0[1]) / (double)(p1[0] - p0[0]);

        for (int i = 2; i < coordinates.length; i++) {
            int[] p = coordinates[i];
            if (p[0] == p0[0])
                return false;
            double k2 = (double)(p[1] - p0[1]) / (double)(p[0] - p0[0]);
            if (Math.abs(k - k2) > 10e-3)
                return false;
        }

        return true;
    }



    // Solution1: (Similar to my solution) Math
    // Time: O(N)
    // Space: O(1)
    public boolean checkStraightLine_solution1(int[][] coordinates) {
        int x1 = coordinates[1][0] - coordinates[0][0];
        int y1 = coordinates[1][1] - coordinates[0][1];
        for (int i = 2; i < coordinates.length; i++) {
            int x2 = coordinates[i][0] - coordinates[0][0];
            int y2 = coordinates[i][1] - coordinates[0][1];
            if (x1 * y2 != x2 * y1)     // Note: 关键的简化点！避免判断除数为0！
                return false;
        }
        return true;
    }
}
