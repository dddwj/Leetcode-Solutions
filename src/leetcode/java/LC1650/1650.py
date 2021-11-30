from typing import *

# Definition for a Node.
class Node:
    def __init__(self, val):
        self.val = val
        self.left = None
        self.right = None
        self.parent = None


class Solution1:
    def lowestCommonAncestor(self, p: 'Node', q: 'Node') -> 'Node':
        path_p = self.findPath(p)
        path_q = self.findPath(q)

        res = self.findLCA(path_p, path_q)
        return res

    def findPath(self, p: 'Node') -> List['Node']:
        path = []
        while p:
            path.append(p)
            p = p.parent
        return path

    def findLCA(self, path1, path2):
        if len(path1) < len(path2):
            path1, path2 = path2, path1
        path1 = path1[::-1]
        path2 = path2[::-1]

        """
        [4,2,5,3]  -> [3,5,2,4]
        [5,3]      -> [3,5]
        """
        ancestor = None
        for i, node in enumerate(path2):
            if node.val == path1[i].val:
                ancestor = node
            else:
                return ancestor

        return ancestor

class Solution2:
    def lowestCommonAncestor(self, p: 'Node', q: 'Node') -> 'Node':
        path = set()
        while p:
            path.add(p)
            p = p.parent

        while q:
            if q in path:
                return q
            q = q.parent

        return q