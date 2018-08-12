package cn.mtianyan;

/**
 * 203 Leetcode
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode(int x) { val = x; }
 * }
 */
class Solution {

    /**
     * 最传统的根据传入值链表删除某些节点的方法(没有使用虚拟头结点，要对head做单独处理)
     *
     * @param head
     * @param val
     * @return
     */
    public ListNode removeElements(ListNode head, int val) {
        while (head != null && head.val == val) {
            ListNode delNode = head;    // 保存head对象
            head = head.next;           // head后移
            delNode.next = null;        // head脱离链表
        }
        if (head == null)
            return null;
        ListNode prev = head;
        while (prev.next != null) {
            if (prev.next.val == val) {
                ListNode delNode = prev.next;
                prev.next = prev.next.next; // prev.next.next
                delNode.next = null;
                // 这里prev不需要后挪，因为删除之后，prev.next节点已经变了，有可能还是val要删除。
            } else {
                prev = prev.next;
            }
        }
        return head;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 6, 3, 4, 5, 6};
        ListNode head = new ListNode(nums);
        System.out.println(head);

        ListNode res = new Solution().removeElements(head, 6);
        System.out.println(res);
    }
}