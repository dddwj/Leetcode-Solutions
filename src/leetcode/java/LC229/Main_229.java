package leetcode.java.LC229;

import java.util.*;

public class Main_229 {

    // Reference: Leetcode 169
    //            https://leetcode-cn.com/problems/majority-element-ii/solution/liang-fu-dong-hua-yan-shi-mo-er-tou-piao-fa-zui-zh/

    public static void main(String[] args) {
        Main_229 main_229 = new Main_229();
        System.out.println(main_229.majorityElement_solution1(new int[]{1, 1, 1, 3, 3, 2, 2, 2}));
        System.out.println(main_229.majorityElement_solution1(new int[]{3, 2, 3}));
    }


    // Solution1: Voting 投票算法  (Ref: Leetcode 169 - Solution6 : Boyer-Moore Voting Algorithm)
    // Ref: https://leetcode-cn.com/problems/majority-element-ii/solution/liang-fu-dong-hua-yan-shi-mo-er-tou-piao-fa-zui-zh/
    // Time: O(N)
    // Space: O(1)
    public List<Integer> majorityElement_solution1(int[] nums) {
        // 创建返回值
        List<Integer> res = new ArrayList<>();
        if (nums == null || nums.length == 0) return res;
        // 初始化两个候选人candidate，和他们的计票
        int cand1 = nums[0], count1 = 0;
        int cand2 = nums[0], count2 = 0;

        // 摩尔投票法，分为两个阶段：配对阶段和计数阶段
        // 配对阶段
        for (int num : nums) {
            // 投票
            if (cand1 == num) {
                count1++;
                continue;
            }
            if (cand2 == num) {
                count2++;
                continue;
            }

            // 第1个候选人配对
            if (count1 == 0) {
                cand1 = num;
                count1++;
                continue;
            }
            // 第2个候选人配对
            if (count2 == 0) {
                cand2 = num;
                count2++;
                continue;
            }

            count1--;
            count2--;
        }

        // 计数阶段
        // 找到了两个候选人之后，需要确定票数是否满足大于 N/3
        count1 = 0;
        count2 = 0;
        for (int num : nums) {
            if (cand1 == num) count1++;
            else if (cand2 == num) count2++;
        }

        if (count1 > nums.length / 3) res.add(cand1);
        if (count2 > nums.length / 3) res.add(cand2);

        return res;
    }



    // Solution2: HashMap
    // Time: O(N) Space: O(N)
    // ...
}
