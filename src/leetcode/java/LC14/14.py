from typing import *

# class Solution:
#     def longestCommonPrefix(self, strs: List[str]) -> str:
#         if not strs or len(strs) == 0:
#             return ""
#
#         prefix = ""
#         for idx, char in enumerate(strs[0]):
#             for j in range(1, len(strs)):
#                 if idx >= len(strs[j]) or strs[j][idx] != char:
#                     return prefix
#             prefix += char
#
#         return prefix


class Solution2:
    def longestCommonPrefix(self, strs: List[str]) -> str:

        pre = strs[0]

        for i in strs:
            while not i.startswith(pre):
                pre = pre[:-1]

        return pre

class Solution:
    def longestCommonPrefix(self, strs: List[str]) -> str:
        if not strs or len(strs) == 0:
            return ""

        low = 0
        high = min(len(string) for string in strs) - 1
        while low <= high:
            mid = (low + high) // 2
            if self.isCommonPrefix(strs, mid-low+1):
                low = mid + 1
            else:
                high = mid - 1

        return strs[0][0:low+1]

    def isCommonPrefix(self, strs: List[str], length):
        prefix = strs[0][:length]
        if all((string[:length] == prefix) for string in strs):
            return True
        return False

s = Solution()
s.longestCommonPrefix(["leetcode", "lee", "l"])

# def isCommonPrefix(self, strs: List[str], length):
#     prefix = strs[0][:length]
#     if all((string[:length] == prefix) for string in strs):
#         return True
#     return False
#
# print(isCommonPrefix(None, ["leetcode", "lee", "lee3"], 2))