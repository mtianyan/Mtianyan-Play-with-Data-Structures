package cn.mtianyan;

public class SolutionRecusionCopy {

    /**
     * mtianyan个人注释版。递归实现链表删除节点。
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
        ListNode res = removeElements(head.next, val);
        if (head.val == val) {
            // 如果此时的head等于，那么它是要被删除的。它就不需要连上结果res，
            return res;
        } else {
            head.next = res; // 此时head应该被连接上res
            return head;
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 6, 3, 4, 5, 6};
        ListNode head = new ListNode(nums);
        System.out.println(head);

        ListNode res = new SolutionRecusionCopy().removeElements(head, 6);
        System.out.println(res);
    }
}
