# Reference: https://leetcode.com/problems/verbal-arithmetic-puzzle/discuss/463920/Python-Backtracking/726748
class Solution_Backtrack:
    def isSolvable(self, words: List[str], result: str) -> bool:
        words.append(result)
        ROW, COL = len(words), max(map(len, words))

        assigned = {}
        assigned_inv = [None] * 10

        def search(column, row, bal):
            if column > COL:
                return bal == 0
            if row == ROW:
                return bal % 10 == 0 and search(column + 1, 0, bal // 10)

            word = words[row]
            if column >= len(word):
                return search(column, row + 1, bal)

            letter = word[~column]  # word[~column] aka word[-column-1]
            sign = 1 if row < ROW - 1 else -1
            if letter in assigned:
                if assigned[letter] or len(word) == 1 or column != len(word) - 1:
                    return search(column, row + 1, bal + sign * assigned[letter])
                return False
            else:
                for d, ad in enumerate(assigned_inv):
                    if ad is None and (d or len(word) == 1 or column != len(word) - 1):
                        assigned_inv[d] = letter
                        assigned[letter] = d
                        if search(column, row + 1, bal + sign * d):
                            return True
                        assigned_inv[d] = None
                        del assigned[letter]

            return False



        return search(0, 0, 0)
