# Reference: https://leetcode.com/problems/time-based-key-value-store/discuss/408651/Python-clean-solution-binary-search
class TimeMap_Binary_Search:

    def __init__(self):
        self.map = {}

    def set(self, key: str, value: str, timestamp: int) -> None:
        if key not in self.map:
            self.map[key] = list()
        self.map[key].append((timestamp, value))


    # Time: O(L + logM) where O(L) for hashing, L = len(key).  O(logM) for binary search, M = the number of set calls
    def get(self, key: str, timestamp: int) -> str:
        if key not in self.map:
            return ""
        values = self.map[key]
        left, right = 0, len(values) - 1
        while left < right:
            mid = (left + right + 1) // 2
            pre_time, value = values[mid]
            if pre_time > timestamp:
                right = mid - 1
            else:
                left = mid
        # return values[left][1]
        return values[left][1] if values[left][0] <= timestamp else ""
