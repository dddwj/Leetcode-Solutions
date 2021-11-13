from typing import *

class Solution:
    def merge(self, intervals: List[List[int]]) -> List[List[int]]:
        if len(intervals) == 1:
            return intervals

        intervals = sorted(intervals, key=lambda x: x[0])
        res = []

        for interval in intervals:
            if len(res) == 0 or res[-1][1] < interval[0]:
                res.append(interval)
            else:
                left = res[-1][0]
                right = max(interval[1], res[-1][1])
                res[-1] = [left, right]

        return res