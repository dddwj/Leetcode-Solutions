# Reference: https://leetcode.com/problems/maximum-nesting-depth-of-the-parentheses/discuss/888949/JavaC%2B%2BPython-Parentheses-Problem-Foundation
class Solution:
    def maxDepth(self, s: str) -> int:
        depth = 0
        res = 0

        for char in s:
            if char == '(':
                depth += 1
                res = max(res, depth)
            elif char == ')':
                depth -= 1

        return res