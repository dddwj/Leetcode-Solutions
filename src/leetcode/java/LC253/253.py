class Solution:
    def minMeetingRooms(self, intervals: List[List[int]]) -> int:
        if not intervals:
            return 0

        room = []

        intervals.sort(key=lambda x: x[0])
        heapq.heappush(room, intervals[0][1])
        for i in intervals[1:]:
            if room[0] <= i[0]:
                heapq.heappop(room)
            heapq.heappush(room, i[1])

        return len(room)