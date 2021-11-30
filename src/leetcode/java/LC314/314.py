from typing import *

# Definition for a binary tree node.
class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right

"""
{
    '-3': [],
    '-2': [4],
    '-1': [9, 5],
     '0': [3, 0, 1],
     1 :  [(1, 8), (2, 2)],
     2 :  [],
     3 :  []
}
"""


# DFS
# Time:  O(W * HlgG)
class Solution3:
    def verticalOrder(self, root: Optional[TreeNode]) -> List[List[int]]:
        if not root:
            return []

        nodes_dict = dict()
        self.traverse(root, nodes_dict, 0, 0)

        values = []
        for width, tuple_list in sorted(nodes_dict.items()):
            tuple_list.sort(key=lambda x : x[0])
            values.append([depth_value[1] for depth_value in tuple_list])
        return values


    def traverse(self, node, nodes_dict, width, depth):
        if not node:
            return

        if width not in nodes_dict:
            nodes_dict[width] = list()
        nodes_dict[width].append((depth, node.val))
        self.traverse(node.left, nodes_dict, width-1, depth+1)
        self.traverse(node.right, nodes_dict, width+1, depth+1)

        return
        

# BFS (without sorting)
# Time: O(N)
class Solution:
    def verticalOrder(self, root: Optional[TreeNode]) -> List[List[int]]:
        if not root:
            return []

        from collections import defaultdict, deque
        columnTable = defaultdict(list)
        min_column = max_column = 0
        queue = deque([(root, 0)])

        while queue:
            node, column = queue.popleft()

            if node is not None:
                columnTable[column].append(node.val)
                min_column = min(min_column, column)
                max_column = max(max_column, column)

                queue.append((node.left, column - 1))
                queue.append((node.right, column + 1))

        return [columnTable[x] for x in range(min_column, max_column + 1)]

