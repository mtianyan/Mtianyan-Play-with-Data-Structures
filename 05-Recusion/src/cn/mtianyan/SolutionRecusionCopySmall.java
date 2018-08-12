package cn.mtianyan;

public class SolutionRecusionCopySmall {
    /**
     * mtianyan链表删除节点简化版
     *
     * @param head
     * @param val
     * @return
     */
    public ListNode removeElements(ListNode head, int val) {
        // 对于问题规模最小情况
        if (head == null) {
            return null;
        }
        // 问题不断小化,头结点分离，对于头结点后面的链表进行删除元素操作
        // 无论head如何，都将head与后面的红色部分连接起来。
        head.next = removeElements(head.next, val);
        return head.val == val ? head.next : head;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 6, 3, 4, 5, 6};
        ListNode head = new ListNode(nums);
        System.out.println(head);

        ListNode res = new SolutionRecusionCopySmall().removeElements(head, 6);
        System.out.println(res);
    }
}
