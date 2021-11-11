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

        carry = 0
        dummy = ListNode()
        node = dummy
        while l1 or l2:
            v1 = l1.val if l1 else 0
            v2 = l2.val if l2 else 0
            val = v1 + v2 + carry
            if val >= 10:
                val -= 10
                carry = 1
            else:
                carry = 0

            node.next = ListNode(val)
            node = node.next

            l1 = l1.next if l1 else l1
            l2 = l2.next if l2 else l2

        if carry:
            node.next = ListNode(carry)

        return dummy.next