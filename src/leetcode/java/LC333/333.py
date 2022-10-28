# See also: LC98

# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right

# Reference: https://leetcode.com/problems/largest-bst-subtree/solution/
class NodeValue:
    def __init__(self, min_node, max_node, max_size):
        self.min_node = min_node
        self.max_node = max_node
        self.max_size = max_size

class Solution:
    def largestBSTSubtree(self, root: Optional[TreeNode]) -> int:
        return self.helper(root).max_size

    def helper(self, root):
        if not root:
            return NodeValue(float('inf'), float('-inf'), 0)

        left = self.helper(root.left)
        right = self.helper(root.right)

        if left.max_node < root.val < right.min_node: # Valid BST
            return NodeValue(min(root.val, left.min_node),
                             max(root.val, right.max_node),
                             left.max_size + right.max_size + 1)

        # Return [-inf, inf] so that parent can't be valid BST
        return NodeValue(float('-inf'), float('inf'), max(left.max_size, right.max_size))