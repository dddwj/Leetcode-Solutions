# Reference: https://leetcode.com/problems/binary-tree-maximum-path-sum/solution/

# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right

# Time: O(N)
# Space: O(H) where H = height of tree (recursion tree)
class Solution:
    def dfs(self, node):
        if not node:
            return 0

        left_path = max(0, self.dfs(node.left))    # Use max() because corner case: input: [2, -1]
        right_path = max(0, self.dfs(node.right))

        self.max_path_sum = max(self.max_path_sum, left_path + node.val + right_path)
        return max(left_path + node.val, right_path + node.val)

    def maxPathSum(self, root: Optional[TreeNode]) -> int:
        self.max_path_sum = -math.inf
        self.dfs(root)
        return self.max_path_sum