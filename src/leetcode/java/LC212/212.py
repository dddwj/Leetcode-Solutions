# Reference: https://leetcode.com/problems/word-search-ii/solution/
class Solution:
    def findWords(self, board: List[List[str]], words: List[str]) -> List[str]:
        WORD_KEY = '$'
        trie = {}
        for word in words:
            node = trie
            for c in word:
                if c not in node:
                    node[c] = {}
                node = node[c]
            node[WORD_KEY] = word


        ROW = len(board)
        COL = len(board[0])
        res = []
        def backtrack(row, col, parent):
            c = board[row][col]
            node = parent[c]

            if WORD_KEY in node:
                res.append(node[WORD_KEY])
                node.pop(WORD_KEY)

            board[row][col] = '#'
            for row_offset, col_offset in [(-1, 0), (0, 1), (1, 0), (0, -1)]:
                new_row, new_col = row + row_offset, col + col_offset
                if new_row < 0 or new_row >= ROW or new_col < 0 or new_col >= COL:
                    continue
                if board[new_row][new_col] not in node:
                    continue
                backtrack(new_row, new_col, node)
            board[row][col] = c

            if not node:
                parent.pop(c)
                # print("Del")
            return



        for row in range(ROW):
            for col in range(COL):
                if board[row][col] in trie:
                    backtrack(row, col, trie)
        return res