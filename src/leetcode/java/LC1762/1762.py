from typing import *

class Solution:
    def findBuildings(self, heights: List[int]) -> List[int]:
        if not heights or len(heights) == 0:
            return []
        n = len(heights)

        highest = 0
        buildings = []
        for i, height in enumerate(reversed(heights)):
            if height > highest:
                buildings.append(n-i-1)
                highest = height
        return buildings[::-1]
