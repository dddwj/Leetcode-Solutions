package leetcode.java.LC100;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

class TreeNode {
     int val;
     TreeNode left;
     TreeNode right;
     TreeNode(int x) { val = x; }
}


public class Main_100 {

    public static void main(String[] args) {
        // TestCase:
        //      p: [1,2,3]
        //      q: [1,2,3]


    }

    // Solution1 (My solution) : Recursion
    // Time: O(N)  Space: O(logN) ~ O(N)
    public boolean isSameTree_solution1(TreeNode p, TreeNode q) {
        if (p == null && q == null)
            return true;
        if (p == null || q == null)
            return false;

        return p.val == q.val
                && isSameTree_solution1(p.left, q.left)
                && isSameTree_solution1(p.right, q.right);
    }


    // Solution2:  Iteration
    // Time:  O(N)
    // Space: O(logN) ---> O(N),  (to keep a fully-balanced tree --->  un-balanced tree)
    public boolean isSameTree_solution2(TreeNode p, TreeNode q) {
        if (p == null && q == null)
            return true;

        Queue<TreeNode> que1 = new LinkedList<>();
        Queue<TreeNode> que2 = new LinkedList<>();
        que1.offer(p);
        que2.offer(q);

        if (!check(p, q))
            return false;

        while (!que1.isEmpty()) {
            p = que1.poll();
            q = que2.poll();

            if (p != null) {
                if (!check(p.left, q.left))
                    return false;
                if (p.left != null) {
                    que1.offer(p.left);
                    que2.offer(q.left);
                }
                if (!check(p.right, q.right))
                    return false;
                if (p.right != null) {
                    que1.offer(p.right);
                    que2.offer(q.right);
                }
            }
        }

        return true;
    }


    private boolean check(TreeNode p, TreeNode q) {
        if (p == null && q == null)
            return true;
        if (p == null || q == null)
            return false;
        return p.val == q.val;
    }



    /*
    * [390,255,2266,-273,337,1105,3440,-425,4113,null,null,600,1355,3241,4731,-488,-367,16,null,565,780,1311,1755,3075,3392,4725,4817,null,null,null,null,-187,152,395,null,728,977,1270,null,1611,1786,2991,3175,3286,null,164,null,null,4864,-252,-95,82,null,391,469,638,769,862,1045,1138,null,1460,1663,null,1838,2891,null,null,null,null,3296,3670,4381,null,4905,null,null,null,-58,null,null,null,null,null,null,null,null,734,null,843,958,null,null,null,1163,1445,1533,null,null,null,2111,2792,null,null,null,3493,3933,4302,4488,null,null,null,null,null,null,819,null,null,null,null,1216,null,null,1522,null,1889,2238,2558,2832,null,3519,3848,4090,4165,null,4404,4630,null,null,null,null,null,null,1885,2018,2199,null,2364,2678,null,null,null,3618,3751,null,4006,null,null,4246,null,null,4554,null,null,null,1936,null,null,null,null,2444,2642,2732,null,null,null,null,null,null,null,4253,null,null,null,null,2393,2461,null,null,null,null,4250,null,null,null,null,2537]
    * [390,255,2266,-273,337,1105,3440,-425,4113,null,null,600,1355,3241,4731,-488,-367,16,null,565,780,1311,1755,3075,3392,4725,4817,null,null,null,null,-187,152,395,null,728,977,1270,null,1611,1786,2991,3175,3286,null,164,null,null,4864,-252,-95,82,null,391,469,638,769,862,1045,1138,null,1460,1663,null,1838,2891,null,null,null,null,3296,3670,4381,null,4905,null,null,null,-58,null,null,null,null,null,null,null,null,734,null,843,958,null,null,null,1163,1445,1533,null,null,null,2111,2792,null,null,null,3493,3933,4302,4488,null,null,null,null,null,null,819,null,null,null,null,1216,null,null,1522,null,1889,2238,2558,2832,null,3519,3848,4090,4165,null,4404,4630,null,null,null,null,null,null,1885,2018,2199,null,2364,2678,null,null,null,3618,3751,null,4006,null,null,4246,null,null,4554,null,null,null,1936,null,null,null,null,2444,2642,2732,null,null,null,null,null,null,null,4253,null,null,null,null,2461,2393,null,null,null,null,4250,null,null,null,null,2537]
    *
    * */
}
