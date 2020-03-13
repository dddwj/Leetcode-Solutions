package leetcode.java.LC169;

import java.util.*;

public class Main_169 {

    // Reference: Leetcode 229  求众数
    // Reference: https://leetcode-cn.com/problems/majority-element/solution/qiu-zhong-shu-by-leetcode-2/

    public static void main(String[] args) {
        Main_169 main_169 = new Main_169();
//        System.out.println(main_169.majorityElement(new int[]{2, 3, 4, 3, 4, 3, 3}));
//        System.out.println(main_169.majorityElement(new int[]{0}));
        System.out.println(main_169.majorityElement_solution6(new int[]{200,100,200}));
    }

    // Solution1: Brute Force
    // Time: O(N*N) Space: O(1)



    // My Solution (Solution 2) : HashMap
    // Time: O(N)  Space: O(N)
    public int majorityElement(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            int freq = map.getOrDefault(num, 0) + 1;
            map.put(num, freq);

            if (freq > nums.length/2) {
                return num;
            }
        }
        return -1;
    }


    // Solution3: Sort + Math
    // Time: O(NlogN)   Space: O(1)
    // Ref: https://leetcode-cn.com/problems/majority-element/solution/qiu-zhong-shu-by-leetcode-2/
    public int majorityElement_solution3(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length/2];
    }

    // Solution4: Randomization
    // Time: Worst: O(INF)  Expected: O(N)
    // Space: O(1)


    // Solution5: Divide and Conquer
    // Time: O(NlogN)
    // Space: O(logN)
    //      尽管分治算法没有直接分配额外的数组空间，但因为递归的过程，在栈中使用了一些非常数的额外空间。
    //      因为算法每次将数组从每一层的中间断开，所以数组长度变为 1 之前只有 O(lgn) 次切断。
    //      由于递归树是平衡的，所以从根到每个叶子节点的长度都是 O(lgn)。由于递归树是深度优先的，空间复杂度等于最长的一条路径，也就是 O(lgn) 。
    public int majorityElement_solution5(int[] nums) {
        return majorityElementRec(nums, 0, nums.length-1);
    }

    private int countInRange(int[] nums, int num, int lo, int hi) {
        int count = 0;
        for (int i = lo; i <= hi; i++) {
            if (nums[i] == num) {
                count++;
            }
        }
        return count;
    }

    private int majorityElementRec(int[] nums, int lo, int hi) {
        // base case; the only element in an array of size 1 is the majority
        // element.
        if (lo == hi) {
            return nums[lo];
        }

        // recurse on left and right halves of this slice.
        int mid = (hi-lo)/2 + lo;
        int left = majorityElementRec(nums, lo, mid);
        int right = majorityElementRec(nums, mid+1, hi);

        // if the two halves agree on the majority element, return it.
        if (left == right) {
            return left;
        }

        // otherwise, count each element and return the "winner".
        int leftCount = countInRange(nums, left, lo, hi);
        int rightCount = countInRange(nums, right, lo, hi);

        return leftCount > rightCount ? left : right;   // 注意：如果rightCount = leftCount, 那么暂时先返回right作为众数，等待之后递归的检验。
    }


    // Solution6: Boyer-Moore Voting Algorithm  投票算法
    // Time: O(N)
    // Space: O(1)
    // Ref: https://leetcode-cn.com/problems/majority-element/solution/qiu-zhong-shu-by-leetcode-2/
    public int majorityElement_solution6(int[] nums) {
        int count = 0;
        Integer candidate = null;

        for (int num : nums) {
            if (count == 0) {
                candidate = num;
            }
            count += (num == candidate) ? 1 : -1;
        }

        return candidate;
    }


}
