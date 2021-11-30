from typing import *

# Definition for a binary tree node.
class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right

# Solution1: Recursive
class Solution1:
    def rangeSumBST(self, root: Optional[TreeNode], low: int, high: int) -> int:
        if not root:
            return 0

        node_in_range = []
        node_in_range.extend(self.calcSum(root, low, high))
        node_in_range_vals = [node.val for node in node_in_range]
        # print(node_in_range_vals)
        return sum(node_in_range_vals)


    def calcSum(self, node: Optional[TreeNode], low: int, high: int) -> List[TreeNode]:
        if not node:
            return []
        node_in_range = []

        # search fit node
        if node.val < low:
            node_in_range.extend(self.calcSum(node.right, low, high))
        elif node.val > high:
            node_in_range.extend(self.calcSum(node.left, low, high))
        else:  # low <= node.val <= high
            node_in_range.append(node)
            node_in_range.extend(self.calcSum(node.left, low, high))
            node_in_range.extend(self.calcSum(node.right, low, high))

        return node_in_range

# Solution2: Iterative
class Solution2:
    def rangeSumBST(self, root: Optional[TreeNode], low: int, high: int) -> int:
        ans = 0
        stack = [root]
        while stack:
            node = stack.pop()
            if node:
                if low <= node.val <= high:
                    ans += node.val
                if low < node.val:
                    stack.append(node.left)
                if node.val < high:
                    stack.append(node.right)

        return ans