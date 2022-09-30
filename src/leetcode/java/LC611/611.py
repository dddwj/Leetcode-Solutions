# Ref: https://leetcode.com/problems/valid-triangle-number/solutions/128135/a-similar-o-n-2-solution-to-3-sum/
#      https://leetcode.com/problems/valid-triangle-number/solutions/1339340/c-java-python-two-pointers-picture-explain-clean-concise-o-n-2/
class Solution_two_pointers:
    def triangleNumber(self, nums: List[int]) -> int:
        n = len(nums)
        nums.sort()
        res = 0

        for k in range(2, n):
            cnt = 0
            i, j = 0, k-1
            while i < j:
                if nums[i] + nums[j] <= nums[k]:
                    i += 1
                else:
                    cnt += (j - i)
                    j -= 1
            res += cnt

        return res
