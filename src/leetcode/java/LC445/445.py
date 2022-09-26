from typing import *


# Definition for singly-linked list.
class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next


class Solution_two_stacks:
    def addTwoNumbers(self, l1: Optional[ListNode], l2: Optional[ListNode]) -> Optional[ListNode]:
        ptr1, ptr2 = l1, l2
        q1, q2 = [], []
        while ptr1:
            q1.append(ptr1)
            ptr1 = ptr1.next
        while ptr2:
            q2.append(ptr2)
            ptr2 = ptr2.next
        # if len(q1) < len(q2):
        #     q1, q2 = q2, q1

        carry = 0
        node = ListNode(-1)
        while q1 or q2:
            digit1 = q1.pop().val if q1 else 0
            digit2 = q2.pop().val if q2 else 0
            digit = digit1 + digit2 + carry
            carry = digit // 10
            digit = digit % 10
            prevNode = ListNode(digit)
            prevNode.next = node
            if node.val == -1:
                prevNode.next = None
            node = prevNode

        if carry:
            prevNode = ListNode(1)
            prevNode.next = node

        return prevNode



class Solution_brute_force:
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