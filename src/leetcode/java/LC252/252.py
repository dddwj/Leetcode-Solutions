class Solution:
    def canAttendMeetings(self, intervals: List[List[int]]) -> bool:
        if not intervals or len(intervals) == 0:
            return True

        intervals.sort(key=lambda x: x[0])
        for i in range(len(intervals)-1):
            curr_i, next_i = intervals[i], intervals[i+1]
            if curr_i[1] > next_i[0]:
                return False

        return True