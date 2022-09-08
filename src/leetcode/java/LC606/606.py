# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution_recursion:
    def tree2str(self, root: Optional[TreeNode]) -> str:
        if not root:
            return ''

        buf = []
        def dfs(node):
            buf.append('(')
            buf.append(str(node.val))
            if not node.left and not node.right:
                buf.append(')')
                return
            if (not node.left) and node.right:
                buf.append('()')
                dfs(node.right)
                buf.append(')')
                return
            if node.left and (not node.right):
                dfs(node.left)
                buf.append(')')
                return
            if node.left and node.right:
                dfs(node.left)
                dfs(node.right)
                buf.append(')')
                return

        dfs(root)
        return "".join(buf[1:-1])

class Solution_recursion_concise:
    def tree2str(self, root: Optional[TreeNode]) -> str:
        if not root:
            return ''

        def dfs(node):
            if not node:
                return ""
            if not node.left and not node.right:
                return str(node.val)
            if not node.right:
                return str(node.val) + '(' + dfs(node.left) + ')'
            # if node.right
            return str(node.val) + '(' + dfs(node.left) + ')(' + dfs(node.right) + ')'

        return dfs(root)