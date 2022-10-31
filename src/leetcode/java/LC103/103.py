# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
from collections import deque


# Solution1: Iteration
class Solution_Iteration:
    def zigzagLevelOrder(self, root: Optional[TreeNode]) -> List[List[int]]:
        if not root:
            return []

        res = []
        queue = deque([root])
        to_right = True
        while queue:
            if to_right:
                res.append([n.val for n in queue])
            else:
                res.append([n.val for n in reversed(queue)])
            to_right = not to_right

            n = len(queue)
            for i in range(n):
                node = queue.popleft()
                if node.left:
                    queue.append(node.left)
                if node.right:
                    queue.append(node.right)

        return res


# Solution2: Recursion
class Solution_Recursion:
    def zigzagLevelOrder(self, root: Optional[TreeNode]) -> List[List[int]]:
        if not root:
            return []

        res = []

        def dfs(level, node):
            if level == len(res):
                res.append([])
            if level % 2 == 0:
                res[level].append(node.val)
            else:
                res[level].insert(0, node.val)
            if node.left:
                dfs(level+1, node.left)
            if node.right:
                dfs(level+1, node.right)

        dfs(0, root)
        return res
