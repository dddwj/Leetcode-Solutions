# Reference: https://leetcode.com/problems/minimum-number-of-days-to-make-m-bouquets/discuss/769703/Python-Clear-explanation-Powerful-Ultimate-Binary-Search-Template.-Solved-many-problems.
#            https://leetcode.com/problems/minimum-number-of-days-to-make-m-bouquets/discuss/686316/JavaC%2B%2BPython-Binary-Search
# Binary Search
# Time: O(Nlg(D)) where N = len(bloomDay), D = max(bloomDay), range of D: (1, 10^9)
# Space: O(1)
class Solution:
    def minDays(self, bloomDay: List[int], m: int, k: int) -> int:
        if m * k > len(bloomDay):
            return -1

        def feasible(day) -> bool:
            bouquets, flowers = 0, 0
            for bloom in bloomDay:
                if bloom > day:
                    flowers = 0
                else:
                    bouquets += (flowers + 1) // k
                    flowers = (flowers + 1) % k
            return bouquets >= m


        left, right = 0, max(bloomDay)
        while left < right:
            mid = (left + right) // 2
            # print(mid)
            if feasible(mid):
                right = mid
            else:
                left = mid + 1
        return left