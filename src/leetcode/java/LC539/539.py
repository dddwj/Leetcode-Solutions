# Reference: https://leetcode.com/problems/minimum-time-difference/discuss/474787/Python3-O(nlogn)-time-with-detailed-explanation-%3A)
# Time: O(NlgN)
# Space: O(1)
class Solution:
    def toMin(self, time_point) -> int:
        hour, minute = time_point.split(":")
        return int(hour) * 60 + int(minute)


    def findMinDifference(self, timePoints: List[str]) -> int:
        times = [self.toMin(timePoint) for timePoint in timePoints]
        times.sort()

        min_diff = math.inf
        for i in range(1, len(times)):
            diff = times[i] - times[i-1]
            min_diff = min(diff, min_diff)

        min_diff = min(min_diff, times[0] - times[-1] + 24 * 60)

        return min_diff