# See also LC333

# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right

# Solution: Pre-order traversal
class Solution_dfs:
    def isValidBST(self, root: Optional[TreeNode]) -> bool:
        nodes = []

        def search(root):
            if not root:
                return
            search(root.left)
            nodes.append(root.val)
            search(root.right)

        search(root)
        for i in range(1, len(nodes)):
            if nodes[i-1] >= nodes[i]:
                return False

        return True

# Solution: Iterative Traversal with Valid Range
class Solution_Iterative_Traversal:
    def isValidBST(self, root: Optional[TreeNode]) -> bool:
        if not root:
            return True

        stack = [(root, -math.inf, math.inf)]
        while stack:
            root, lower, upper = stack.pop()
            if not root:
                continue
            val = root.val
            if val <= lower or val >= upper:
                return False
            stack.append((root.right, val, upper))
            stack.append((root.left, lower, val))
        return True