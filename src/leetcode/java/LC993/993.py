# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def isCousins(self, root: Optional[TreeNode], x: int, y: int) -> bool:
        if not root:
            return False

        # parents = defaultdict(TreeNode)
        # parents[root] = dummy_node
        dummy_node = TreeNode('#', root)
        x_level = None
        y_level = None
        x_parent = None
        y_parent = None

        def dfs(node, level, parent):
            nonlocal x_level, y_level, x_parent, y_parent
            if not node:
                return
            if node.val == x:
                x_level = level
                x_parent = parent
            if node.val == y:
                y_level = level
                y_parent = parent
            if x_level and y_level:
                return
            dfs(node.left, level+1, node)
            dfs(node.right, level+1, node)

        dfs(root, 0, dummy_node)
        # print(x_level, y_level)
        # print(x_parent)
        # print(y_parent)
        if x_parent == y_parent:
            return False
        return x_level == y_level
