from typing import *


# Definition for singly-linked list.
class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next


class Solution:
    def addTwoNumbers(self, l1: Optional[ListNode], l2: Optional[ListNode]) -> Optional[ListNode]:
        if not l1:
            return l2
        if not l2:
            return l1

        node = l1
        v1 = 0
        while node:
            v1 *= 10
            v1 += node.val
            node = node.next

        node = l2
        v2 = 0
        while node:
            v2 *= 10
            v2 += node.val
            node = node.next

        res_val = v1 + v2
        res_val = str(res_val)
        dummy = ListNode()
        node = dummy
        for c in res_val:
            node.next = ListNode(int(c))
            node = node.next

        return dummy.next