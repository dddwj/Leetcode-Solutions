# See also: LC227
# Reference: https://leetcode.com/problems/basic-calculator-iii/discuss/127881/Python-O(n)-Solution-using-recursion
class Solution:
    def calculate(self, s: str) -> int:
        if not s:
            return 0

        s = s.strip()
        s = s + "$"

        def helper(stack, i):

            op = '+'
            number = 0

            while i < len(s):
                c = s[i]
                if c == ' ':
                    i += 1
                    continue
                if c.isdigit():
                    number = number * 10 + int(c)
                    i += 1
                elif c == '(':
                    number, i = helper([], i+1)
                else:
                    if op == '+':
                        stack.append(number)
                    elif op == '-':
                        stack.append(-number)
                    elif op == '*':
                        stack.append(stack.pop() * number)
                    elif op == '/':
                        stack.append(int(stack.pop() / number))
                    i += 1
                    if c == ')':
                        return sum(stack), i
                    op = c
                    number = 0

            return sum(stack)

        return helper([], 0)