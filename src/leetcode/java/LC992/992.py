# Reference: https://leetcode.com/problems/subarrays-with-k-different-integers/discuss/2475570/Sliding-window-(HashmapTwo-pointer)-with-details-Python
#            https://leetcode.com/problems/subarrays-with-k-different-integers/discuss/1996719/For-those-who-didn't-understand-why-exactly(K)-atMost(K)-atMost(K-1)-works....

# Solution: Sliding Window
# Time: O(N)
# Space: O(N)
class Solution:
    def subarraysWithKDistinct(self, nums: List[int], k: int) -> int:

        def atMostKDistinct(nums, k):
            n = len(nums)
            left = 0
            right = 0
            freq = {}
            count = 0

            while right < n:
                num = nums[right]
                if num not in freq:
                    freq[num] = 0
                freq[num] += 1

                while len(freq) > k:
                    l_num = nums[left]
                    freq[l_num] -= 1
                    if freq[l_num] == 0:
                        del freq[l_num]
                    left += 1

                count += (right - left + 1)
                right += 1

            return count

        return atMostKDistinct(nums, k) - atMostKDistinct(nums, k-1)