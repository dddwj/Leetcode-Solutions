class HitCounter:

    def __init__(self):
        self.hits = []    # list of timestamp
        self.start = 0

    def hit(self, timestamp: int) -> None:
        self.hits.append(timestamp)

    def getHits(self, timestamp: int) -> int:
        self.move_window(timestamp)
        return len(self.hits)

    def move_window(self, timestamp):
        self.start = timestamp - 300 + 1
        self.hits = [hit for hit in self.hits if hit >= self.start]

# Your HitCounter object will be instantiated and called as such:
# obj = HitCounter()
# obj.hit(timestamp)
# param_2 = obj.getHits(timestamp)