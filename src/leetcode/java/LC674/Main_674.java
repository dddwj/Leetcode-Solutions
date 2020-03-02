package leetcode.java.LC674;

public class Main_674 {
    public static void main(String[] args) {
        Main_674 main_674 = new Main_674();
        System.out.println(main_674.findLengthOfLCIS(new int[]{1, 1, 1, 1, 1}));
        System.out.println(main_674.findLengthOfLCIS(new int[]{1,3,3,4,2,6,7,8,9,10}));
    }

    // This is a basic version of Leetcode 28: strStr()


    // My Solution (Solution1): Sliding Window
    // Time: O(N)  Space: O(1)
    public int findLengthOfLCIS(int[] nums) {
        if (nums == null)   return 0;
        if (nums.length == 1)   return 1;

        int slow = 0, fast = 0;
        int n = nums.length;
        int maxLength = 0;
        while (slow < n) {
            int maxRound = 1;      // A single element is regarded as 'continuous increasing subseq'
            for (fast = slow + 1; fast < n; fast++) {
                if (nums[fast] <= nums[fast-1])
                    break;
                else
                    maxRound++;
            }
            maxLength = maxLength > maxRound ? maxLength: maxRound;
            slow = fast;
        }
        return maxLength;
    }

}
