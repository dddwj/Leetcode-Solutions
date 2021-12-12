class Solution:

    def validWordAbbreviation(self, word: str, abbr: str) -> bool:
        if not word or not abbr:
            return False

        i = j = 0
        max_i, max_j = len(word), len(abbr)

        while i < max_i and j < max_j:
            if word[i] == abbr[j]:
                i += 1
                j += 1
            elif abbr[j] == "0":
                return False
            elif abbr[j].isnumeric():
                k = j
                while k < max_j and abbr[k].isnumeric():
                    k += 1
                i += int(abbr[j:k])
                j = k
            else:
                return False
        return i == max_i and j == max_j
