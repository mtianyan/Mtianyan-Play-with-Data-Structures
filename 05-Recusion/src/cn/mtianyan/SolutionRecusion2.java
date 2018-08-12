package cn.mtianyan;

public class SolutionRecusion2 {
    /**
     * 使用递归方式并简化之后的链表删除节点
     *
     * @param head
     * @param val
     * @return
     */
    public ListNode removeElements(ListNode head, int val) {

        if (head == null) // 求解最基本问题
            return null;

        head.next = removeElements(head.next, val); // 将原问题转换为更小问题
        return head.val == val ? head.next : head;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 6, 3, 4, 5, 6};
        ListNode head = new ListNode(nums);
        System.out.println(head);

        ListNode res = new SolutionRecusion2().removeElements(head, 6);
        System.out.println(res);
    }
}
