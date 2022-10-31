# Solution: Stack
# Time: O(N)
# Space: O(N)
class Solution:
    def calculate(self, s: str) -> int:
        stack = []
        res = 0
        num = 0
        sign = 1

        for c in s:
            if c == ' ':
                continue
            if c == '+':
                res += sign * num
                sign = 1
                num = 0
            if c == '-':
                res += sign * num
                sign = -1
                num = 0
            if c == '(':
                stack.append((res, sign))
                sign = 1
                res = 0
            if c == ')':
                res += sign * num
                s_res, s_sign = stack.pop()
                res = res * s_sign + s_res
                num = 0
            if c.isdigit():
                num = num * 10 + int(c)

        return res + sign * num