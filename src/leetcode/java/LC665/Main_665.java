package leetcode.java.LC665;

public class Main_665 {

    public static void main(String[] args) {
        Main_665 main_665 = new Main_665();
        System.out.println(main_665.checkPossibility_solution1(new int[]{8,10,9,10}));
        System.out.println(main_665.checkPossibility_solution1(new int[]{10,13,9,10}));
        System.out.println(main_665.checkPossibility_solution1(new int[]{4, 2, 3}));
    }


    // Solution: One Pass
    // Time: O(N)
    // Space: O(1)
    // Ref: https://leetcode-cn.com/problems/non-decreasing-array/solution/xiang-xia-guai-dian-de-xun-zhao-yu-xiao-chu-by-joh/
    public boolean checkPossibility_solution1(int[] nums) {
        if (nums == null || nums.length <= 0)
            return false;
        int n = nums.length;
        if (n <= 2)
            return true;

        int cnt = 0;
        for (int i = 1; i < nums.length && cnt <= 1 ; i++){
            if (nums[i-1] > nums[i]){
                cnt++;
                if (i-2 >= 0 && nums[i-2] > nums[i])
                    nums[i] = nums[i-1];
                else
                    nums[i-1] = nums[i];
            }
        }
        return cnt <= 1;
    }


    // Solution2: Recording Index  （Solution3 on leetcode.com)
    // Ref: https://leetcode.com/problems/non-decreasing-array/solution/151390
    public boolean checkPossibility_solution2(int[] nums) {
        int p = -1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i-1] > nums[i]) {
                if (p != -1) return false;
                else p = i;
            }
        }
        return (p == -1 ||   // no reversed pair
                p == 1 || p == nums.length - 1 ||  // reversed pair is first or last element
                nums[p - 2] <= nums[p] || nums[p - 1] <= nums[p + 1]); // normal case range [p-2 --> p+1] all valid
    }
}
