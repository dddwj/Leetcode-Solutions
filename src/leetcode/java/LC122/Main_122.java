package leetcode.java.LC122;

public class Main_122 {
    public static void main (String[] args) {
        int[] prices = new int[] {7, 1, 5, 3, 6};

        int profit = 0;

        int[] rateOfChange = new int[prices.length - 1];
        for (int i = 0; i < prices.length - 1; i++) {
            rateOfChange[i] = prices[i+1] - prices[i];
        }


        for (int i = 0; i < rateOfChange.length; i++) {
            if (rateOfChange[i] > 0)
                profit += rateOfChange[i];
        }
        System.out.println(profit);
//        return profit;
    }
}
