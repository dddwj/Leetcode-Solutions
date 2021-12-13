from typing import *

class Solution:
    def groupStrings(self, strings: List[str]) -> List[List[str]]:
        def subtract(string):
            if not string or len(string) == 0:
                return ""
            base = string[0]
            offsets = []
            for char in string[1:]:
                offset = ord(char) - ord(base)
                if offset < 0:
                    offset += 26
                offsets.append(str(offset))
            return "#".join(offsets)

        answer = {}
        for string in strings:
            offsets = subtract(string)
            if offsets not in answer:
                answer[offsets] = []
            answer[offsets].append(string)

        return list(answer.values())

s = Solution()
print(s.groupStrings(["az","ba","a","z"]))