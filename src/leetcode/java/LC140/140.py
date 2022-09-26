# Ref: https://leetcode.wang/leetcode-140-Word-BreakII.html
class Solution_dynamic_programming:
    def wordBreak(self, s: str, wordDict: List[str]) -> List[str]:
        n = len(s)
        dp = [None] * (n+1)
        dp[0] = [""]
        word_set = set(wordDict)

        for end in range(1, n+1):
            temp = []
            for start in range(0, end):
                sub = s[start:end]
                if sub in word_set:
                    # print(dp[start])
                    for sentence in dp[start]:
                        if sentence == "":
                            temp.append(sub)
                        else:
                            temp.append(sentence + " " + sub)
            dp[end] = temp

        # print(dp)
        return dp[n]


# Ref: https://leetcode.com/problems/word-break-ii/discuss/44167/My-concise-JAVA-solution-based-on-memorized-DFS
class Solution_recursion_bottom_up:
    def wordBreak(self, s: str, wordDict: List[str]) -> List[str]:
        self.n = len(s)
        self.word_set = set(wordDict)
        self.memo = defaultdict(list)

        return self.helper(s)

    def helper(self, s: str) -> List[str]:
        if s in self.memo:
            return self.memo[s]

        res = []
        if len(s) == 0:
            res.append("")
            return res
        for word in self.word_set:
            if s.startswith(word):
                sub_list = self.helper(s[len(word):])
                for sub in sub_list:
                    res.append(word + (" " if sub else "") + sub)
        self.memo[s] = res
        return res


# Ref: https://leetcode.wang/leetcode-140-Word-BreakII.html
class Solution_recursion_top_down:
    def wordBreak(self, s: str, wordDict: List[str]) -> List[str]:
        self.n = len(s)
        self.word_set = set(wordDict)
        self.memo = defaultdict(list)

        return self.helper(s)

    def helper(self, s: str) -> List[str]:
        n = len(s)
        if n == 0:
            return []
        if s in self.memo:
            # print(self.memo[s])
            return self.memo[s]

        res = []
        for j in range(0, n):
            sub = s[j:n]
            if sub in self.word_set:
                if j == 0:
                    res.append(sub)
                else:
                    temp = self.helper(s[0:j])
                    for t in temp:
                        res.append(t + " " + sub)
        self.memo[s] = res
        # print(self.memo)
        return res