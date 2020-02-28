package leetcode.java.LC119;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main_119 {

    // PLEASE REFER TO Leetcode 118 !!

    // ref: https://youtu.be/iVhmR1bzKoo
    // Solution: Brute Force -- Fill out values from right to left
    // Space: O(K)
    public List<Integer> getRow(int rowIndex) {

        List<Integer> row = new ArrayList();
        row.add(1);

        for (int i = 1; i <= rowIndex; i++) {
            for (int j = i - 1; j >= 1; j--) {
                row.set(j, row.get(j) + row.get(j - 1));
            }
            row.add(1);
        }

        return row;
    }

}
