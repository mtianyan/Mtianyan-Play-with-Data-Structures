class Stack(object):
    """栈"""

    def __init__(self):
        self.items = []

    def is_empty(self):
        """判断是否为空"""
        return self.items == []

    def push(self, item):
        """加入元素"""
        self.items.append(item)

    def pop(self):
        """弹出元素"""
        return self.items.pop()

    def peek(self):
        """返回栈顶元素"""
        return self.items[len(self.items) - 1]

    def size(self):
        """返回栈的大小"""
        return len(self.items)


class Solution:
    def isValid(self, s):
        """
        :type s: str
        :rtype: bool
        """
        stack = Stack()
        for c in s:
            if c == '(' or c == '[' or c == '{':
                stack.push(c)
            else:
                if stack.size() == 0:
                    return False
                top_c = stack.pop()
                if c == ')' and top_c != '(':
                    return False
                if c == ']' and top_c != '[':
                    return False
                if c == '}' and top_c != '{':
                    return False
        return stack.size() == 0


if __name__ == '__main__':
    s = Solution()
    print(s.isValid("(]"))
    print(s.isValid("()"))
