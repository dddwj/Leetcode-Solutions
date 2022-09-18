# Reference: https://labuladong.github.io/algo/2/23/59/

class DLinkedNode():
    def __init__(self):
        self.key = 0
        self.value = 0
        self.prev = None
        self.next = None


class LRUCache:
    def _pop_tail(self):
        """
        Pop the current tail.
        """
        node = self.tail.prev
        self.tail.prev = node.prev
        node.prev.next = self.tail
        return node

    def _add_node(self, node):
        """
        Always add the new node right after head.
        """
        node.next = self.head.next
        self.head.next.prev = node

        node.prev = self.head
        self.head.next = node

    def _remove_node(self, node):
        """
        Remove an existing node from the linked list.
        """
        prev = node.prev
        new = node.next

        prev.next = new
        new.prev = prev

    def _move_to_head(self, node):
        """
        Move certain node in between to the head.
        """
        self._remove_node(node)
        self._add_node(node)


    def __init__(self, capacity: int):
        self.cache = {}   # key -> Node
        self.size = 0
        self.capacity = capacity
        self.head = DLinkedNode()
        self.tail = DLinkedNode()

        self.head.next = self.tail
        self.tail.prev = self.head


    def get(self, key: int) -> int:
        if key not in self.cache:
            return -1
        node = self.cache.get(key)
        self._move_to_head(node)
        return node.value


    def put(self, key: int, value: int) -> None:
        if key in self.cache:
            node = self.cache.get(key)
            node.value = value
            self._move_to_head(node)

        elif key not in self.cache:
            newNode = DLinkedNode()
            newNode.value = value
            newNode.key = key

            self.cache[key] = newNode
            self._add_node(newNode)
            self.size += 1
            if self.size > self.capacity:
                tailNode = self._pop_tail()
                del self.cache[tailNode.key]
                self.size -= 1




# Your LRUCache object will be instantiated and called as such:
# obj = LRUCache(capacity)
# param_1 = obj.get(key)
# obj.put(key,value)