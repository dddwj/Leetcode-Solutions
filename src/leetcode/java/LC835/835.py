# Time: O(N^2 + AB) where A, B = number of points of img1, img2
# Space: O(A + B)
# Reference: https://leetcode.com/problems/image-overlap/discuss/130623/C++JavaPython-Straight-Forward/183969
class Solution:
    def largestOverlap(self, img1: List[List[int]], img2: List[List[int]]) -> int:
        pA = set()
        pB = set()
        n = len(img1)

        for i in range(n*n):
            r, c = i // n, i % n
            if img1[r][c]:
                pA.add((r,c))
            if img2[r][c]:
                pB.add((r,c))

        count = defaultdict(int)
        for pointA in pA:
            for pointB in pB:
                diff = str(pointA[0] - pointB[0]) + " " + str(pointA[1] - pointB[1])
                count[diff] += 1
        # print(count)

        return max(count.values(), default=0)