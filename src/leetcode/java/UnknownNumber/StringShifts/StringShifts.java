package leetcode.java.UnknownNumber;

public class StringShifts {

    public static void main(String[] args) {
        StringShifts stringShifts = new StringShifts();
        System.out.println(stringShifts.stringShift("abcdef", new int[][]{{0, 1}, {1, 2}, {0, 2}}));
        System.out.println(stringShifts.stringShift("yisxjwry", new int[][]{
            {1,8},
            {1,4},
            {1,3},
            {1,6},
            {0,6},
            {1,4},
            {0,2},
            {0,1}
            // , {0,28}
        }));
    }

    public String stringShift(String s, int[][] shift) {
        if (s == null || s.length() <= 1 || shift == null || shift.length == 0) {
            return s;
        }

        int allStep = 0;
        for (int[] act : shift) {
            int direction = act[0];
            int step = act[1];
            allStep += step * (direction == 1 ? 1 : -1);        // +1: right shift    -1: left shift
        }

        String res = s;
        allStep = allStep % s.length();
        if (allStep > 0) {  // shift right
            res = s.substring(s.length() - allStep, s.length());
            res += s.substring(0, s.length() - allStep);
        } else if (allStep < 0) {  // shift left
            res = s.substring(-allStep, s.length());
            res += s.substring(0, -allStep);
        }
        return res;
    }
}
