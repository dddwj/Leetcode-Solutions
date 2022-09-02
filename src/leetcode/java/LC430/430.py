class Solution:
    def flatten(self, head: 'Optional[Node]') -> 'Optional[Node]':
        if not head:
            return None

        head, last = self.dfs(head)
        return head

    def dfs(self, head: 'Optional[Node]') -> ('Optional[Node]', 'Optional[Node]'):
        curr = head
        last = head
        while curr:
            last = curr
            if curr.child:
                child = curr.child
                curr_next = curr.next
                child_head, child_last = self.dfs(child)

                curr.next = child_head
                child_head.prev = curr

                child_last.next = curr_next
                if curr_next:
                    curr_next.prev = child_last
                else:
                    last = child_last

                curr.child = None
                curr = curr_next
            else:
                curr = curr.next

        return (head, last)


class Solution_concise:
    # Reference: https://leetcode.com/problems/flatten-a-multilevel-doubly-linked-list/solution/
    def flatten(self, head: 'Optional[Node]') -> 'Optional[Node]':
        if not head:
            return

        dummy_head = Node(0, None, head, None)
        prev = dummy_head

        stack = []
        stack.append(head)

        while stack:
            curr = stack.pop()

            prev.next = curr
            curr.prev = prev

            if curr.next:
                stack.append(curr.next)

            if curr.child:
                stack.append(curr.child)
                curr.child = None

            prev = curr

        dummy_head.next.prev = None
        return dummy_head.next