from typing import *

# Good Ref:
#   https://leetcode.com/problems/k-closest-points-to-origin/discuss/294389/Easy-to-read-Python-min-heap-solution-(-beat-99-python-solutions-)/279296
#   https://leetcode.com/problems/k-closest-points-to-origin/discuss/348171/Python3-sort-O(NlogN)-minimum-heap-O(NlogN)-and-maximum-heap-O(NlogK)

class Solution:
    def kClosest(self, points: List[List[int]], k: int) -> List[List[int]]:

        import heapq

        heap = []
        for (x, y) in points:
            dist = -(x*x + y*y)
            if len(heap) == k:
                heapq.heappushpop(heap, (dist, (x, y)))
            else:
                heapq.heappush(heap, (dist, (x, y)))

        return [[x, y] for (dist, (x, y)) in heap]