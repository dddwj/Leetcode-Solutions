class Solution:
    def minAddToMakeValid(self, s: str) -> int:

        # (((
        # stack: [(, (, (]

        # ((()
        # stack: [(, (]

        # )((())))
        # stack: [((]
        # balance: 1

        # )(()(
        # stack: [(, (]
        # balance: 1

        balance = 0
        s = list(s)
        stack = []
        for char in s:
            if char == '(':
                stack.append('(')
            elif char == ')':
                if stack:
                    stack.pop()
                else:
                    balance += 1
            else:
                # Error
                pass

        return balance + len(stack)

