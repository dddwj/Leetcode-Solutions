package leetcode.java.LC172;

public class Main_172 {

    public static void main(String[] args) {
        Main_172 main_172 = new Main_172();

        System.out.println(main_172.trailingZeroes(126));
        System.out.println(main_172.trailingZeroes(626));

        /*
        * 对N分解因数5的个数：
        *
        * 626 -> 125:  125个[]    k*[5]
        * 125 -> 25:   25个{}     m*[5]*{5}
        * 25  -> 5:    5个 <>     n*[5]*{5}*<5>
        * 5   -> 1:    1个 ""     l*[5]*{5}*<5>*"5"
        * 1   -> 0:    0个        p*[5]*{5}*<5>*5*5
        *
        * */

        long sum = 1;
        for (int i = 1; i <= 125; i++) {
            sum = sum * i;
            System.out.println(sum);
        }
    }

    // My Solution: Math + Induction
    // Time: O(lgN/lg5)
    // Space: O(1)
    public int trailingZeroes(int n) {
        int count = 0;
        while (n > 0) {
            n =  n / 5;
            count += n;
        }
        return count;
    }


}
