# Reference: https://leetcode.com/problems/find-median-from-data-stream/discuss/74047/JavaPython-two-heap-solution-O(log-n)-add-O(1)-find

class MedianFinder:

    def __init__(self):
        self.small = []   # max heap. Use negative numbers to implement max heap.
        self.large = []   # min heap. Holds 0 or 1 more numbers than self.small


    def addNum(self, num: int) -> None:
        if len(self.small) == len(self.large):
            top = -heapq.heappushpop(self.small, -num)
            heapq.heappush(self.large, top)
        else:
            top = -heapq.heappushpop(self.large, num)
            heapq.heappush(self.small, top)


    def findMedian(self) -> float:
        if len(self.small) == len(self.large):
            return float(self.large[0] - self.small[0]) / 2.0
        else:
            return float(self.large[0])



# Your MedianFinder object will be instantiated and called as such:
# obj = MedianFinder()
# obj.addNum(num)
# param_2 = obj.findMedian()