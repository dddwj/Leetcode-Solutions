package leetcode.java.LC705;

// Ref: https://leetcode-cn.com/problems/design-hashset/solution/she-ji-ha-xi-ji-he-by-leetcode/

import leetcode.java.Utils.TreeNode;


// Solution2: BST + Hash
// Time: O(log(N/K)), where K = keyRange = 769
// Space: O(K + N)
// See also: LC700, LC701, LC450
public class Main_705_solution2 {
    public static void main(String[] args) {
        MyHashSet_BST hashSet = new MyHashSet_BST();
        hashSet.add(1);
        hashSet.add(2);
        System.out.println(hashSet.contains(1));
        System.out.println(hashSet.contains(3));
        hashSet.add(2);
        System.out.println(hashSet.contains(2));
        hashSet.remove(2);
        System.out.println(hashSet.contains(2));
    }
}

class MyHashSet_BST {
    int keyRange;
    MyBST[] set;

    /** Initialize your data structure here. */
    public MyHashSet_BST() {
        this.keyRange = 769;
        this.set = new MyBST[this.keyRange];
        for (int i = 0; i < this.keyRange; i++) {
            this.set[i] = new MyBST();
        }
    }

    public void add(int key) {
        if (!contains(key)) {
            int index = key % this.keyRange;
            set[index].insert(key);
        }
    }

    public void remove(int key) {
        int index = computeHash(key);
        if (set[index].exists(key))
            set[index].delete(key);
    }

    /** Returns true if this set contains the specified element */
    public boolean contains(int key) {
        int index = computeHash(key);
        return set[index].exists(key);
    }

    private int computeHash(int key) {
        return key % this.keyRange;
    }
}

class MyBST {
    private BSTree tree;

    public MyBST() {
        tree = new BSTree();
    }

    public void insert(Integer key) {
        this.tree.root = this.tree.insertIntoBST(this.tree.root, key);
    }

    public void delete(Integer key) {
        this.tree.root = this.tree.deleteNode(this.tree.root, key);
    }

    public boolean exists(Integer key) {
        TreeNode node = this.tree.searchBST(this.tree.root, key);
        return (node != null);
    }
}

class BSTree {
    TreeNode root = null;

    // Ref: LC700
    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null || root.val == val)
            return root;

        return val < root.val ? searchBST(root.left, val) : searchBST(root.right, val);
    }

    // Ref: LC701
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null)
            return new TreeNode(val);
        if (val > root.val) {
            root.right = insertIntoBST(root.right, val);
        } else if (val < root.val) {
            root.left = insertIntoBST(root.left, val);
        } else if (val == root.val) {
            // skip the insertion
        }
        return root;
    }

    // Ref: LC450
    public TreeNode deleteNode(TreeNode root, int val) {
        if (root == null)
            return null;

        if (val > root.val)
            root.right = deleteNode(root.right, val);
        else if (val < root.val)
            root.left = deleteNode(root.left, val);
        else if (val == root.val) {
            if (root.left == null && root.right == null)
                root = null;
            else if (root.right != null) {
                root.val = successor(root);
                root.right = deleteNode(root.right, root.val);
            } else if (root.left != null) {
                root.val = predecessor(root);
                root.left = deleteNode(root.left, root.val);
            }
        }
        return root;
    }

    public int successor(TreeNode root) {
        root = root.right;
        while (root.left != null)
            root = root.left;
        return root.val;
    }

    public int predecessor(TreeNode root) {
        root = root.left;
        while (root.right != null)
            root = root.right;
        return root.val;
    }

}
