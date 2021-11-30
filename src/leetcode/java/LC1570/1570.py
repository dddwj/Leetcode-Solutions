from typing import *

# Reference: https://leetcode.com/problems/dot-product-of-two-sparse-vectors/discuss/834376/Very-simple-Python-solution-with-follow-up

class SparseVector:
    def __init__(self, nums: List[int]):
        self.dict = self.vector2Dict(nums)

    # Return the dotProduct of two sparse vectors
    def dotProduct(self, vec: 'SparseVector') -> int:
        s = 0
        for pos, val in self.dict.items():
            if pos in vec.dict:
                s += (val * vec.dict[pos])
        return s

    def vector2Dict(self, nums: List[int]) -> dict:
        res = {}
        for i, num in enumerate(nums):
            if num != 0:
                res[i] = num
        return res


# Your SparseVector object will be instantiated and called as such:
# v1 = SparseVector(nums1)
# v2 = SparseVector(nums2)
# ans = v1.dotProduct(v2)