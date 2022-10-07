class Solution:
    def subsets(self, nums: List[int]) -> List[List[int]]:
        res = [[]]
        for num in nums:
            prev_res = list(res)
            for prev_path in prev_res:
                res.append(prev_path + [num])

        return res