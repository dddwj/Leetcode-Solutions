package leetcode.java.LC1447;

import java.util.*;

public class Main_1447 {

    public static void main(String[] args) {
        Main_1447 main_1447 = new Main_1447();
        System.out.println(main_1447.simplifiedFractions(5));
        System.out.println(main_1447.simplifiedFractions(4));
        System.out.println(main_1447.simplifiedFractions(2));
        System.out.println(main_1447.simplifiedFractions(1));
        System.out.println(main_1447.simplifiedFractions(0));
    }

    // My Solution: HashSet
    // Time: O(N*N)
    // Space: O(N*N)
    public List<String> simplifiedFractions(int n) {
        if (n < 1 || n > 100)
            return new ArrayList<String>();

        List<String> res = new ArrayList<>();
        Set<Float> seen = new HashSet<>();

        for (float down = 2; down <= n; down++) {
            for (float up = 1; up <= down - 1; up++) {
                float frac = (float)Math.round(up / down * 1000000) / 1000000;
                if (!seen.contains(frac)) {
                    seen.add(frac);
                    String str = String.valueOf((int) up) +
                            "/" +
                            (int) down;
                    res.add(str);
                }
            }
        }
        return res;
    }
}
