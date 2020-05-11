package leetcode.java.LC733;

import java.util.*;

public class Main_733 {

    public static void main(String[] args) {
        Main_733 main_733 = new Main_733();
        int[][] res = main_733.floodFill(new int[][]{
                {1,1,1},
                {1,1,0},
                {1,0,1}}, 2, 2, 2);
        for (int[] line : res)
            System.out.println(Arrays.toString(line));
    }



    // Solution: DFS
    // Time: O(N)
    // Space: O(N + N), N for seen array, N for recursive call stack
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        if (image == null || image.length == 0 || image[0].length == 0)
            return image;

        boolean[][] seen = new boolean[image.length][image[0].length];
        for (boolean[] line : seen)
            Arrays.fill(line, false);

        if (newColor != image[sr][sc])
            floodFill(image, seen, sr, sc, newColor, image[sr][sc]);

        return image;
    }

    private void floodFill(int[][] image, boolean[][] seen, int sr, int sc, int newColor, int prevColor) {
        System.out.println("(" + sr + ", " + sc + ")");
        if (sr < image.length && sc < image[0].length && sr >= 0 && sc >= 0 && !seen[sr][sc] && image[sr][sc] == prevColor) {
            seen[sr][sc] = true;
            image[sr][sc] = newColor;
            floodFill(image, seen, sr + 1, sc, newColor, prevColor);
            floodFill(image, seen, sr, sc + 1, newColor, prevColor);
            floodFill(image, seen, sr - 1, sc, newColor, prevColor);
            floodFill(image, seen, sr, sc - 1, newColor, prevColor);
        }
    }
}
