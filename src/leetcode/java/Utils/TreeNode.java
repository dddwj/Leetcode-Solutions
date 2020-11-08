package leetcode.java.Utils;

public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;
    public TreeNode(int x) { val = x; }

    public static TreeNode buildFromList(String str) {
        str = str.substring(1, str.length() - 1);
        String[] elementStrings = str.split(",");
        int[] arr = new int[elementStrings.length];  int index = 0;
        for (String element : elementStrings) {
            if (element.equals("null"))
                arr[index++] = Integer.MIN_VALUE;
            else
                arr[index++] = Integer.parseInt(element);
        }

        TreeNode[] nodes = new TreeNode[arr.length + 1];
        for (int i = 1; i <= arr.length; i++) {
            if (arr[i - 1] == Integer.MIN_VALUE)
                nodes[i] = null;
            else
                nodes[i] = new TreeNode(arr[i - 1]);
        }
        TreeNode node = null;
        for (int i = 1; i < nodes.length / 2; i++) {
            node = nodes[i];
            if (node == null)
                continue;
            node.left = nodes[i * 2];
            node.right = nodes[i * 2 + 1];
        }
        return nodes[1];
    }
}
