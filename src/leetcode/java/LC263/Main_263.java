package leetcode.java.LC263;

public class Main_263 {

    public static void main(String[] args) {
        Main_263 main_263 = new Main_263();
        System.out.println(-(Integer.MIN_VALUE));
        System.out.println(Integer.MIN_VALUE);
        System.out.println(Math.pow(2, 31));
        System.out.println((Integer.MIN_VALUE+1));
        System.out.println(-(Integer.MIN_VALUE+1));
    }


    // My Solution:
    public boolean isUgly(int num) {
        if (num <= 0) {
            return false;
        }

        num = helper(num, 2);
        num = helper(num, 3);
        num = helper(num, 5);

        if (num == 1) {
            return true;
        }

        return false;
    }

    private int helper(int num, int div) {
        while (num % div == 0) {
            num = num / div;
        }
        return num;
    }


    // Solution1  (Faster, compared with my solution.)
    public boolean isUgly_solution1(int num){
        if (num <= 0) return false;
        if (num == 1) return true;
        while (num != 1){
            if ( num % 2 == 0){
                num /= 2;
            } else if ( num % 3 == 0){
                num /= 3;
            } else if ( num % 5 == 0){
                num /= 5;
            } else{
                return false;
            }
        }
        return true;
    }
}
