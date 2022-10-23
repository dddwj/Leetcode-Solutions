import math
class Solution:
    # def gcd(self, nums) -> int:
    # res = nums[0]
    # for num in nums[1:]:
    #     res = self.get_gcd(res, num)
    # return res
    # return math.gcd(*nums)


    def get_gcd(self, num1, num2) -> int:
        if num2 == 0:
            return num1
        return self.get_gcd(num2, num1 % num2)

    def subarrayGCD(self, nums: List[int], k: int) -> int:
        n = len(nums)
        res = 0
        possible = [x % k == 0 for x in nums]

        for i in range(n):
            if nums[i] == k:
                res += 1
            current_gcd = nums[i]
            for j in range(i+1, n):
                if possible[j]:
                    current_gcd = self.get_gcd(current_gcd, nums[j])
                    if current_gcd == k:
                        res += 1
                else:
                    break

        return res
