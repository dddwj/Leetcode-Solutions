# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right

# Time: O(N)
# Space: O(N)
class Solution_BFS:
    def widthOfBinaryTree(self, root: Optional[TreeNode]) -> int:
        if not root:
            return 0

        max_width = 1
        q = deque()
        q.append((root, 0))

        while q:
            next_q = deque([])
            first_node, first_idx = q[0]
            while q:
                node, idx = q.popleft()
                if node.left:
                    next_q.append((node.left, 2*idx))
                if node.right:
                    next_q.append((node.right, 2*idx+1))
            width = idx - first_idx + 1
            max_width = max(width, max_width)
            q = next_q


        return max_width