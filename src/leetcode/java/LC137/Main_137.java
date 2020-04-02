package leetcode.java.LC137;

import java.util.*;

public class Main_137 {

    // Ref: LC136, LC260

    public static void main(String[] args) {

        Main_137 main_137 = new Main_137();
        System.out.println(main_137.singleNumber_solution4(new int[]{0, 1, 0, 1, 0, 1, 99}));
    }


    // Solution1:   HashSet
    // Time: O(N)
    // Space: O(N),  Extra Space
    public int singleNumber_solution1(int[] nums) {
        Set<Long> set = new HashSet<>();
        long sumSet = 0, sumArray = 0;
        for(int n : nums) {
            sumArray += n;
            set.add((long)n);
        }
        for(Long s : set)
            sumSet += s;

        return (int)((3 * sumSet - sumArray) / 2);
    }


    // Solution2: HashMap
    // Time: O(N)  Space: O(N), Extra Space
    public int singleNumber_solution2(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 1) {
                return entry.getKey();
            }
        }
        return -1;
    }


    // Solution3: Bit Manipulation    (Mask)
    // Time: O(N)
    // Space: O(1),   NO Extra Space
    // Ref: https://leetcode.wang/leetcode-137-Single-NumberII.html
    //      https://leetcode-cn.com/problems/single-number-ii/solution/zhi-chu-xian-yi-ci-de-shu-zi-ii-by-leetcode/
    /*
    public int singleNumber_solution3(int[] nums) {

    }
    */


    // Solution4: Bit Manipulation
    // Time: O(N), Space: O(1), NO Extra Space
    // Ref: https://leetcode.wang/leetcode-137-Single-NumberII.html
    public int singleNumber_solution4(int[] nums) {

        int ans = 0;

        // 考虑每一位
        for (int i = 0; i < 32; i++) {
            int count = 0;
            // 考虑每一位数
            for (int j = 0; j < nums.length; j++) {
                // 当前位是否为1
                if ((nums[j] >>> i & 1) == 1) {
                    count++;
                }
            }

            // 1的个数是否为3的倍数
            if (count % 3 != 0) {
                ans = ans | 1 << i;
            }
        }

        return ans;
    }
}
