class Solution:
    def haveConflict(self, event1: List[str], event2: List[str]) -> bool:

        def get_time(time: str) -> tuple:
            time = time.split(":")
            hour = int(time[0])
            minute = int(time[1])
            return (hour, minute)

        start1 = get_time(event1[0])
        start2 = get_time(event2[0])
        if start1 > start2:
            event1, event2 = event2, event1

        return event1[1] >= event2[0]