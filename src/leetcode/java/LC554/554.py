# Ref: https://leetcode.com/problems/brick-wall/discuss/888577/IntuitionC%2B%2BWith-PicturesHashMapDetailed-ExplanationCommentsSolutionCode
from typing import *

class Solution:
    def leastBricks(self, wall: List[List[int]]) -> int:
        # edge: frequency
        edge_freq = dict()
        for line in wall:
            index = 0
            for brick in line[:-1]:
                index += brick
                edge_freq[index] = 1 + edge_freq.get(index, 0)

        max_frequency = max(edge_freq.values()) if len(edge_freq) != 0 else 0

        return len(wall) - max_frequency