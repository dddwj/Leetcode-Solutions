package leetcode.java.LC108;

/*
    GREAT Tutorial:
    https://leetcode-cn.com/problems/convert-sorted-array-to-binary-search-tree/solution/jiang-you-xu-shu-zu-zhuan-huan-wei-er-cha-sou-s-15/
 */


import java.util.Random;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) {
        val = x;
    }
}

public class Main_108 {

    public static void main(String[] args) {
        Main_108 main_108 = new Main_108();
        main_108.sortedArrayToBST(new int[] {-10,-3,0,5,9});
    }

    // Solution: In-order Traverse:
    //       Opt1: always choose left middle node as a root
    //       Opt2: always choose right middle node as a root
    //       Opt3: choose random middle node as a root
    // Time: O(N)
    // Space: O(logN), for recursive function call
    public TreeNode sortedArrayToBST(int[] nums) {
        this.nums = nums;
        return helper(0, this.nums.length - 1);
    }

    int[] nums;
    Random rand = new Random();

    public TreeNode helper(int left, int right) {
        if (left > right) return null;

        // Opt1: always choose left middle node as a root
        int p = (left + right) / 2;
/*
        // Opt2: always choose right middle node as a root
        int p = (left + right) / 2;
        if ((left + right) % 2 == 1) ++p;

        // Opt3: choose random middle node as a root
        int p = (left + right) / 2;
        if ((left + right) % 2 == 1) p += rand.nextInt(2);
*/

        // inorder traversal: left -> node -> right
        TreeNode root = new TreeNode(nums[p]);
        root.left = helper(left, p - 1);
        root.right = helper(p + 1, right);
        return root;
    }


}

