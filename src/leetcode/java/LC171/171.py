class Solution:
    def titleToNumber(self, columnTitle: str) -> int:
        mapping = {}
        for i in range(1, 27):
            c = chr(ord('A') + i - 1)
            mapping[c] = i

        idx = 0
        while columnTitle:
            char = columnTitle[0]
            # print(mapping[char])
            idx = (idx * 26 + mapping[char])
            columnTitle = columnTitle[1:]

        return idx