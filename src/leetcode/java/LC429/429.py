class Solution_bfs:
    def levelOrder(self, root: 'Node') -> List[List[int]]:
        if not root:
            return []
        result = []
        prev_level = [root]
        while prev_level:
            current_level = []
            result.append([])
            for node in prev_level:
                result[-1].append(node.val)
                current_level.extend(node.children)
            prev_level = current_level
        return result

class Solution_recursion:
    def levelOrder(self, root: 'Node') -> List[List[int]]:
        if not root:
            return []
        result = []

        def recursion(node: 'Node', level: int):
            if len(result) == level:
                result.append([])
            if not node:
                return
            result[level].append(node.val)
            for child in node.children:
                recursion(child, level + 1)

        recursion(root, 0)
        return result