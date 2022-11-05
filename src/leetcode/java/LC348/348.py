# Time: O(1)
# Space: O(N)
class TicTacToe:

    def __init__(self, n: int):
        self.rows = [0] * n
        self.cols = [0] * n
        self.diagonal = 0
        self.antidiagonal = 0
        self.n = n

    def move(self, row: int, col: int, player: int) -> int:
        df = 1 if player == 1 else -1
        self.rows[col] += df
        self.cols[row] += df

        if row == col:
            self.diagonal += df
        if row == (self.n - col - 1):
            self.antidiagonal += df

        win = abs(self.rows[col]) == self.n or \
              abs(self.cols[row]) == self.n or \
              abs(self.diagonal) == self.n or \
              abs(self.antidiagonal) == self.n

        if win:
            return player
        else:
            return 0



# Your TicTacToe object will be instantiated and called as such:
# obj = TicTacToe(n)
# param_1 = obj.move(row,col,player)