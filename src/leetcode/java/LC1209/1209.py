# Reference: https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string-ii/discuss/392933/JavaC%2B%2BPython-Two-Pointers-and-Stack-Solution

class Solution_stack:
    def removeDuplicates(self, s: str, k: int) -> str:
        stack = []
        for ch in s:
            if stack and stack[-1][0] == ch:
                letter, freq = stack[-1]
                freq = freq + 1
                stack[-1] = (letter, freq)
                if stack[-1][1] == k:
                    stack.pop()
            else:
                stack.append((ch, 1))

        return ''.join([c * k for c, k in stack])


class Solution_two_pointers:
    def removeDuplicates(self, s: str, k: int) -> str:
        n = len(s)
        if n == 0:
            return ""

        s = list(s)
        count = [0] * n
        i = j = 0

        while j < n:
            s[i] = s[j]
            if i > 0 and s[i - 1] == s[j]:
                count[i] = count[i - 1] + 1
            else:
                count[i] = 1
            if count[i] == k:
                i -= k
            i += 1
            j += 1

        return "".join(s[:i])