class Solution:
    def averageOfLevels(self, root: Optional[TreeNode]) -> List[float]:
        if not root:
            return []

        res = []
        queue = deque()
        queue.append(root)
        while queue:
            k = len(queue)
            line_sum = 0
            for i in range(k):
                node = queue.popleft()
                line_sum += node.val
                if node.left:
                    queue.append(node.left)
                if node.right:
                    queue.append(node.right)

            avg = float(line_sum) / k
            res.append(avg)

        return res