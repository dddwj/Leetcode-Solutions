class BrowserHistory:

    def __init__(self, homepage: str):
        self.stack = [homepage]
        self.current_idx = 0

    def visit(self, url: str) -> None:
        max_idx = len(self.stack) - 1
        pointer = self.current_idx + 1
        while pointer <= max_idx:
            self.stack.pop()
            pointer += 1

        self.stack.append(url)
        self.current_idx = len(self.stack) - 1


    def back(self, steps: int) -> str:
        if self.current_idx - steps >= 0:
            self.current_idx -= steps
        else:
            self.current_idx = 0
        return self.stack[self.current_idx]

    def forward(self, steps: int) -> str:
        max_idx = len(self.stack) - 1
        if self.current_idx + steps > max_idx:
            self.current_idx = max_idx
        else:
            self.current_idx = self.current_idx + steps
        return self.stack[self.current_idx]


# Your BrowserHistory object will be instantiated and called as such:
# obj = BrowserHistory(homepage)
# obj.visit(url)
# param_2 = obj.back(steps)
# param_3 = obj.forward(steps)