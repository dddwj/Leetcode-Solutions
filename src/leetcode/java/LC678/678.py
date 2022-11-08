# Solution: Dynamic Programming
# Time: O(3^N)
# Space: O(N) + cache
class Solution_Dynamic_Programming:
    def checkValidString(self, s: str) -> bool:
        if not s:
            return False

        left = 0
        right = 0
        i = 0

        @lru_cache(maxsize=None)
        def isValid(left: int, right: int, i: int) -> bool:
            if i == len(s):
                if left == right:
                    return True
                return False
            if left < right:
                return False

            if s[i] == '(':
                return isValid(left + 1, right, i + 1)
            elif s[i] == ')':
                return isValid(left, right + 1, i + 1)
            else:  # s[i] == '*'
                return isValid(left, right, i + 1) or isValid(left + 1, right, i + 1) or isValid(left, right + 1, i + 1)

            return False


        return isValid(left, right, i)



# Solution: Greedy (One Pass)
# Reference: https://leetcode.com/problems/valid-parenthesis-string/discuss/107570/JavaC%2B%2BPython-One-Pass-Count-the-Open-Parenthesis
# Time: O(N)
# Space: O(1)
class Solution_Greedy:
    def checkValidString(self, s: str) -> bool:
        if not s:
            return False

        c_min = c_max = 0
        for c in s:
            if c == '(':
                c_max += 1
                c_min += 1
            elif c == ')':
                c_max -= 1
                c_min = max(0, c_min - 1)
            else: # c == '*'
                c_max += 1
                c_min = max(0, c_min - 1)

            if c_max < 0:
                return False

        return c_min == 0





