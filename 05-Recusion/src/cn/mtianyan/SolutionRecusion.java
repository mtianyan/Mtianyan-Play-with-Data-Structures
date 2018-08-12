package cn.mtianyan;

class SolutionRecusion {

    /**
     * 使用递归方式实现链表去除节点
     *
     * @param head
     * @param val
     * @return
     */
    public ListNode removeElements(ListNode head, int val) {
        if (head == null) // 求解最基本问题
            return null;
        ListNode res = removeElements(head.next, val); // 将原问题转换为更小问题
        if (head.val == val) {
            return res; // 继续调用更小问题求解。
        } else {
            head.next = res; // 这个head不需要删除，继续连接上链表。
            return head;
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 6, 3, 4, 5, 6};
        ListNode head = new ListNode(nums);
        System.out.println(head);

        ListNode res = new SolutionRecusion().removeElements(head, 6);
        System.out.println(res);
    }
}