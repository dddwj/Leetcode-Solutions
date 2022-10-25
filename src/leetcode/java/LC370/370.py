# Reference: https://leetcode.com/problems/range-addition/discuss/1339761/Detailed-explanation-or-Python
class Solution:
    def getModifiedArray(self, length: int, updates: List[List[int]]) -> List[int]:
        ans = [0] * length
        for start, end, incr in updates:
            ans[start] += incr
            end += 1
            if end < length:
                ans[end] -= incr

        for i in range(1, length):
            ans[i] = ans[i-1] + ans[i]

        return ans