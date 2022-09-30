# Ref: https://leetcode.com/problems/invalid-transactions/solutions/1230813/python-o-n-solution-o-n-space/
# Time: O(N)
# Space: O(N)
class Solution:
    def invalidTransactions(self, transactions: List[str]) -> List[str]:
        res = []
        trans_map = {}

        for i in transactions:
            split = i.split(",")
            name, time, amount, city = str(split[0]), int(split[1]), int(split[2]), str(split[3])

            if time not in trans_map:
                trans_map[time] = {
                    name: set([city])
                }
            else:
                if name not in trans_map[time]:
                    trans_map[time][name] = set([city])
                else:
                    trans_map[time][name].add(city)

        for i in transactions:
            split = i.split(",")
            name, time, amount, city = str(split[0]), int(split[1]), int(split[2]), str(split[3])
            if amount > 1000:
                res.append(i)
                continue

            for time_stamp in range(time-60, time+61):
                if time_stamp not in trans_map:
                    continue
                if name not in trans_map[time_stamp]:
                    continue
                # if len(trans_map[j][name]) > 1 or (next(iter(trans_map[j][name])) != city):
                if len(trans_map[time_stamp][name]) == 1 and (city in trans_map[time_stamp][name]):
                    continue
                res.append(i)
                break

        return res