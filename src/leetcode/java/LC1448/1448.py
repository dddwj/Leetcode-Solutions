class Solution_Iteration:
    def goodNodes(self, root: TreeNode) -> int:
        if not root:
            return 0

        stack = [(root, float("-inf"))]
        count = 0
        while stack:
            node, maxSeen = stack.pop()
            if node.val >= maxSeen:
                count += 1
            if node.left:
                stack.append((node.left, max(node.val, maxSeen)))
            if node.right:
                stack.append((node.right, max(node.val, maxSeen)))

        return count


class Solution_recursion:
    def goodNodes(self, root: TreeNode) -> int:
        if not root:
            return 0
        return self.dfs(root, root.val)

    def dfs(self, root: TreeNode, maxSeen: int) -> int:
        if not root:
            return 0
        good = 0
        if root.val >= maxSeen:
            maxSeen = root.val
            good = 1
        return good + self.dfs(root.left, maxSeen) + self.dfs(root.right, maxSeen)