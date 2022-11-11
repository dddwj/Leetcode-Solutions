# Reference: https://leetcode.com/problems/alien-dictionary/solution/

# Time: O(C) where C =  total length of all the words in the input list, added together
# Space: O(1)
class Solution_TopoSort_BFS:
    from collections import defaultdict, Counter, deque

    def alienOrder(self, words: List[str]) -> str:
        n = len(words)
        adj_list = defaultdict(set)
        in_degree = Counter({c: 0 for word in words for c in word})

        for first, second in zip(words, words[1:]):
            for c, d in zip(first, second):
                if c != d:
                    # print(c, d)
                    if d not in adj_list[c]:
                        adj_list[c].add(d)
                        in_degree[d] += 1
                    break
            else:
                if len(second) < len(first):
                    return ""

        output = []
        q = deque([c for c in in_degree if in_degree[c] == 0])
        while q:
            c = q.popleft()
            output.append(c)
            for d in adj_list[c]:
                in_degree[d] -= 1
                if in_degree[d] == 0:
                    q.append(d)

        if len(output) < len(in_degree):
            return ""
        return "".join(output)