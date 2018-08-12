package cn.mtianyan;

class SolutionDummyHead {

    /**
     * 添加了虚拟头结点的链表删除节点的方法，不需要对head做特殊处理
     *
     * @param head
     * @param val
     * @return
     */
    public ListNode removeElements(ListNode head, int val) {
        ListNode dummyHead = new ListNode(-1); // 因为不会被访问，值随便。
        dummyHead.next = head;

        ListNode prev = dummyHead;
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
        return dummyHead.next;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 6, 3, 4, 5, 6};
        ListNode head = new ListNode(nums);
        System.out.println(head);

        ListNode res = new SolutionDummyHead().removeElements(head, 6);
        System.out.println(res);
    }
}
