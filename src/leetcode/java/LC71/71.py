class Solution:
    def simplifyPath(self, path: str) -> str:
        if not path or len(path) < 1:
            return ''

        dircs = path.split('/')

        stack = []
        for dirc in dircs:
            if dirc in {'', '_', '.'}:  # No-op
                continue
            elif dirc == '..':          # Go upper
                if stack:
                    stack.pop()
            else:                       # valid directory
                stack.append(dirc)

        return "/" + "/".join(stack)