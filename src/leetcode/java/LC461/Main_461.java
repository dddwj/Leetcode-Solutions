package leetcode.java.LC461;

public class Main_461 {

    public static void main(String[] args) {
        Main_461 main_461 = new Main_461();
        System.out.println(main_461.hammingDistance_solution3(131, 4));
    }

    // Solution1: Built-in Functions
    // Time: O(1)  Space: O(1)
    public int hammingDistance_solution1(int x, int y) {
        //        System.out.println(Integer.bitCount(-5));
        //        System.out.println(Integer.bitCount(5));
        //        Refer to the attached screenshot
        return Integer.bitCount(x ^ y);
    }


    // Solution2: Bit Manipulation  (Opt1)
    // My solution
    public int hammingDistance_solution21(int x, int y) {
        int count = 0;

        while (x != 0 || y != 0) {
            if (((x % 2) ^ (y % 2)) == 1)
                count++;
            System.out.println("x, y = " + x + ", " + y);
            x = x >> 1;
            y = y >> 1;
        }

        return count;
    }

    // Solution2: Bit Manipulation (Opt2)
    // Time: O(1)  Space: O(1)
    public int hammingDistance_solution22(int x, int y) {
        int xor = x ^ y;
        int distance = 0;
        while (xor != 0) {
            if (xor % 2 == 1)
                distance += 1;
            xor = xor >> 1;
        }
        return distance;
    }

    // Solution3: Brian Kernighan Algorithm
    // Time: O(1)  Space: O(1)
    public int hammingDistance_solution3(int x, int y) {
        int xor = x ^ y;
        int distance = 0;
        while (xor != 0) {
            distance += 1;
            // remove the rightmost bit of '1'
            xor = xor & (xor - 1);
        }
        return distance;
    }

}
