package leetcode.java.LC326;

public class Main_326 {

    public static void main(String[] args) {
        Main_326 main_326 = new Main_326();
        System.out.println(main_326.isPowerOfThree_solution32(100000007));
    }


    // Solution1: Loop Iteration
    // Time: O(lgN / lgK), where K = 3
    // Space: O(1)
    public boolean isPowerOfThree_solution1(int n) {
        if (n < 1) {
            return false;
        }

        while (n % 3 == 0) {
            n /= 3;
        }

        return n == 1;
    }


    // Solution2: Base Conversion  +  Regex
    // Time: O(lgN / lgK), where K = 3.
    //      1) Integer.toString() - Base conversion is generally implemented as a repeated division.
    //      2) String.matches() - Method iterates over the entire string. The number of digits in the base 3 representation of n is O(lgN / lg3)
    // Space: O(lgN / lgK), where K = 3.
    //      1) The size of 'string of the base 3 representation of the number' is O(lgN / lg3)
    //      2) The string of the regular expression (constant size)
    public boolean isPowerOfThree_solution2(int n) {
        return Integer.toString(n, 3).matches("^10*$");
    }


    // Solution3: Mathematics
    // Time: Language-dependent
    // Space: O(1)
    public boolean isPowerOfThree_solution31(int n) {
//        return (Math.log10(n) / Math.log10(3)) % 1 == 0;      // Limited by the computer precision errors
        double epsilon = 0.0000000001;
        return (Math.log(n) / Math.log(3) + epsilon) % 1 <= 2 * epsilon;
    }

    public boolean isPowerOfThree_solution32(int n) {
        if (n <= 0) return false;
        int d = (int) (Math.log(n)/Math.log(3));
        return Math.pow(3, d) == n;
    }



    // Solution4: Integer Limitations
    // Time: O(1)  Space: O(1)
    public boolean isPowerOfThree_solution4(int n) {
        return n > 0 && 1162261467 % n == 0;        // 1162261467 = 3^19 < Integer.MAX_VALUE < 3^20
    }
}
