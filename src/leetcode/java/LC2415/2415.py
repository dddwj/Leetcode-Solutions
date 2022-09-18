# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right

# My Solution
class Solution_math:
    def reverseOddLevels(self, root: Optional[TreeNode]) -> Optional[TreeNode]:
        # Flatten
        l = [root]
        i = 0
        while i < len(l):
            node = l[i]
            if node.left:
                l.append(node.left)
            if node.right:
                l.append(node.right)
            i += 1

        def swap(left: int, right: int, l: list) -> None:
            while left < right:
                l[left], l[right] = l[right], l[left]
                left += 1
                right -= 1

        # Swap
        n = len(l)
        seen = 0
        level = 0
        while seen < n:
            if level % 2 == 1:
                left = 2**(level) - 1
                right = 2 * left
                swap(left, right, l)
            seen += 2**level
            level += 1

        # Reconstruct
        i = 0
        while 2*i+2 < n:
            node = l[i]
            node.left = l[2*i+1]
            node.right = l[2*i+2]
            i += 1
        while i < n:
            node = l[i]
            node.left = None
            node.right = None
            i += 1

        return root