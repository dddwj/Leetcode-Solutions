# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def pruneTree(self, root: Optional[TreeNode]) -> Optional[TreeNode]:
        if self.dfs(root) == 0:
            return None
        return root

    def dfs(self, root: Optional[TreeNode]) -> int:
        if not root:
            return 0

        left_sum = self.dfs(root.left)
        if left_sum == 0:
            root.left = None

        right_sum = self.dfs(root.right)
        if right_sum == 0:
            root.right = None

        return root.val + left_sum + right_sum
