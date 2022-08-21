class UndergroundSystem:
    '''
    {
    id: (inStation, inTime)
    }

    {
    A->B: (avg, count)
    B->C: (avg, count)
    C->B: (avg, count)
    }
    '''

    def __init__(self):
        self.outMap = {}
        self.inMap = {}

    def checkIn(self, id: int, stationName: str, t: int) -> None:
        self.inMap[id] = (stationName, t)

    def checkOut(self, id: int, stationName: str, t: int) -> None:
        if id not in self.inMap:
            return
        inStation, inTime = self.inMap[id]
        index = inStation + '->' + stationName
        if index not in self.outMap:
            self.outMap[index] = (None, 0)
        avg, count = self.outMap[index]
        if not avg:
            count = 1
            avg = t - inTime
        else:
            avg = (avg * count + t - inTime) / (count + 1)
            count = count + 1
        self.outMap[index] = (avg, count)
        self.inMap[id] = None

    def getAverageTime(self, startStation: str, endStation: str) -> float:
        index = startStation + '->' + endStation
        return self.outMap[index][0]

# Your UndergroundSystem object will be instantiated and called as such:
# obj = UndergroundSystem()
# obj.checkIn(id,stationName,t)
# obj.checkOut(id,stationName,t)
# param_3 = obj.getAverageTime(startStation,endStation)