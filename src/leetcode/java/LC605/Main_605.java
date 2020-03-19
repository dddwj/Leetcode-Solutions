package leetcode.java.LC605;

public class Main_605 {

    public static void main(String[] args) {
        Main_605 main_605 = new Main_605();
        System.out.println(main_605.canPlaceFlowers(new int[]{1, 0, 0, 0, 1}, 2));
        System.out.println(main_605.canPlaceFlowers(new int[]{1, 0, 0, 0, 1}, 1));
    }


    // Solution: One-pass (Math)
    // Time: O(N) Space: O(1)
    // Ref: https://leetcode.com/problems/can-place-flowers/solution/
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        int i = 0, count = 0;
        while (i < flowerbed.length) {
            if (flowerbed[i] == 0
                    && (i == 0 || flowerbed[i - 1] == 0)        // NOTE: 这里的'或'判断是关键！简洁！
                    && (i == flowerbed.length - 1 || flowerbed[i + 1] == 0)) {
                flowerbed[i] = 1;
                count++;
            }
            i++;
        }
        return count >= n;
    }
}
