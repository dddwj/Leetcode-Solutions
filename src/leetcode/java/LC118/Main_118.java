package leetcode.java.LC118;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main_118 {

    public static void main(String[] args) {
        Main_118 main_118 = new Main_118();
        main_118.generate(10);
    }

    // My Solution (Opt1): Brute Force -- [Dynamic Programming]  (similar to solution3)
    // Time: O(N*N)  Space: O(N)
    public List<List<Integer>> generate_mysolution11(int numRows) {

        List<List<Integer>> result = new ArrayList<>();

        if (numRows == 0)   return result;

        result.add(Arrays.asList(1));
        if (numRows == 1)   return result;

        for (int i = 2; i <= numRows; i++) {
            List<Integer> prevRow = result.get(i - 2);
            Integer[] row = new Integer[i];
            Arrays.fill(row, 1);
            for (int k = 1; k < i - 1; k++) {
                row[k] = prevRow.get(k-1) + prevRow.get(k);
            }
            result.add(Arrays.asList(row));
            System.out.println(Arrays.toString(row));
        }
        return result;
    }


    // My Solution (Opt2): Brute Force -- [Dynamic Programming]  (similar to solution3)
    public List<List<Integer>> generate_mysolution12(int numRows) {
        List<List<Integer>> dp = new ArrayList<>();
        if(numRows == 0){
            return dp;
        }
        dp.add(new ArrayList<>());
        dp.get(0).add(1);
        //注意这里的 i 是指行数，但是dp是从0开始的
        //所以preRow是i-2
        for(int i = 2;i <= numRows; i++){
            List<Integer> row = new ArrayList<>();
            List<Integer> preRow = dp.get(i-2);
            row.add(1);
            for(int j = 1;j < i-1;j++){
                row.add(preRow.get(j) + preRow.get(j-1));
            }
            row.add(1);
            dp.add(row);
        }
        return dp;
    }




    //      Reference: LeetCode 119
    //      GREAT Tutorial: https://youtu.be/TXd5lfP3Gac
    //      credit: https://leetcode.com/problems/pascals-triangle/discuss/38141/My-concise-solution-in-Java/36127*/
    // Solution2: Brute Force -- [fill out values from right to left]
    // Space: O(K)
    public List<List<Integer>> generate_solution2(int numRows) {
        List<List<Integer>> result = new ArrayList();
        List<Integer> row = new ArrayList();
        for (int i = 0; i < numRows; i++) {
            for (int j = row.size() - 1; j >= 1; j--) {
                row.set(j, row.get(j) + row.get(j - 1));
            }
            row.add(1);
//            System.out.println(row);
            result.add(new ArrayList<>(row));
        }
        return result;
    }




    // Solution3: Recursion
    // Reference: https://leetcode-cn.com/problems/pascals-triangle/solution/javadi-gui-dong-tai-gui-hua-by-jeromememory/
    public List<List<Integer>> generate(int numRows) {
        //存储要返回的杨辉三角
        List<List<Integer>> dg = new ArrayList<>();
        //若0行，则返回空
        if(numRows == 0){
            return dg;
        }

        //递归出口，这是第一步！找到出口
        if(numRows == 1){
            dg.add(new ArrayList<>());
            dg.get(0).add(1);
            return dg;
        }

        //递归，注意返回值！！！这是第二步
        dg = generate(numRows-1);

        //一级递归要做啥，我们可以看第二行到第三行需要做啥
        //首先是要申请一个list来存储第三行，然后通过第二行得到第三行
        //第三行的首尾为1是确定了的，然后就是中间的数如何得到
        //通过观察很容易拿到for循环里面的式子
        //最后别忘了返回值！！！
        List<Integer> row = new ArrayList<>();
        row.add(1);
        for(int j = 1;j < numRows - 1;j++){
            row.add(dg.get(numRows-2).get(j-1) + dg.get(numRows-2).get(j));
        }
        row.add(1);
        dg.add(row);
//        System.out.println(row);
        return dg;
    }

}
