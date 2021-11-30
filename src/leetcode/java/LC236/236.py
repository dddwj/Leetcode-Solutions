from typing import *
# Definition for a binary tree node.
class TreeNode:
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None

# Recursive Approach
# Time: O(N)  Space: O(N) for recursion stack
class Solution1:
    def lowestCommonAncestor(self, root: 'TreeNode', p: 'TreeNode', q: 'TreeNode') -> 'TreeNode':
        self.ans = None
        self.recurse_tree(root, p, q)
        return self.ans

    def recurse_tree(self, current_node, p, q):
        if not current_node:
            return False

        left = self.recurse_tree(current_node.left, p, q)

        right = self.recurse_tree(current_node.right, p, q)

        mid = (current_node == p) or (current_node == q)

        if mid + left + right >= 2:
            self.ans = current_node

        return mid or left or right


# Iterative using parent pointers
# Time: O(N)  Space: O(N) for worst case
class Solution2:
    def lowestCommonAncestor(self, root: 'TreeNode', p: 'TreeNode', q: 'TreeNode') -> 'TreeNode':
        stack = [root]

        parent = {root: None}

        while p not in parent or q not in parent:
            node = stack.pop()
            if node.left:
                stack.append(node.left)
                parent[node.left] = node
            if node.right:
                stack.append(node.right)
                parent[node.right] = node

        ancestors = set()
        while p:
            ancestors.add(p)
            p = parent[p]

        #         ancestors: [4,2,5,3]
        #         q path:   [6,5,3]

        while q:
            if q in ancestors:
                return q
            else:
                q = parent[q]

        return q


# Find path + Find ancestor
# Time: O(N)
class MySolution:
    def lowestCommonAncestor(self, root: 'TreeNode', p: 'TreeNode', q: 'TreeNode') -> 'TreeNode':
        self.path = []
        self.search(root, p)
        path_p = list(self.path)
        self.path = []
        self.search(root, q)
        path_q = list(self.path)

        return self.findLCA(path_p, path_q)


    def search(self, node: 'TreeNode', target: 'TreeNode') -> List['TreeNode']:
        if not node:
            return False
        if node.val == target.val or self.search(node.left, target) or self.search(node.right, target):
            self.path.append(node)
            return True
        return False


    def findLCA(self, path1, path2):
        if len(path1) < len(path2):
            path1, path2 = path2, path1

        #         path1: [3,5,2,4]
        #         path2: [3,5,6]

        ancestor = None
        path1 = set(path1)
        path2 = path2[::-1]
        for i, node in enumerate(path2):
            if node in path1:
                ancestor = node
            else:
                return ancestor

        return ancestor

s = MySolution()
root = n3 = TreeNode(3)
n5 = TreeNode(5)
n1 = TreeNode(1)
n6 = TreeNode(6)
n2 = TreeNode(2)
n7 = TreeNode(7)
n4 = TreeNode(4)
n3.left = n5
n5.left = n6
n5.right = n2
n2.left = n7
n2.right = n4# n = TreeNode()

s.lowestCommonAncestor(root, n5, n4)