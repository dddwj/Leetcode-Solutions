"""
# Definition for a Node.
class Node(object):
    def __init__(self, val=None, children=[]):
        self.val = val
        self.children = children
"""

# Reference: https://leetcode.com/problems/serialize-and-deserialize-n-ary-tree/discuss/393501/easy-peasy-python-solution-preorder-travel-with-child-count

class Codec:
    def serialize(self, root: 'Node') -> str:
        """Encodes a tree to a single string.
        
        :type root: Node
        :rtype: str
        """
        lst = []
        self.serializeHelper(root, lst)
        # print(",".join(lst))
        return ",".join(lst)

    def serializeHelper(self, root, lst):
        if not root:
            return
        else:
            lst.append(str(root.val))
            lst.append(str(len(root.children)))
            for child in root.children:
                self.serializeHelper(child, lst)


    def deserialize(self, data: str) -> 'Node':
        """Decodes your encoded data to tree.
        
        :type data: str
        :rtype: Node
        """
        if len(data) == 0:
            return None
        lst = data.split(",")
        q = deque([lst[i] for i in range(len(lst))])
        # print(q)
        return self.deserializeHelper(q)

    def deserializeHelper(self, q):
        if len(q) == 0:
            return None
        val = q. popleft()
        totalKids = int(q.popleft())
        root = Node(int(val), [])
        for i in range(totalKids):
            root.children.append(self.deserializeHelper(q))
        return root



# Your Codec object will be instantiated and called as such:
# codec = Codec()
# codec.deserialize(codec.serialize(root))