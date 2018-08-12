package cn.mtianyan;

public class SolutionRecusionCopySmallPrint {
    /**
     * mtianyan链表删除节点简化版
     *
     * @param head
     * @param val
     * @return
     */
    public ListNode removeElements(ListNode head, int val, int depth) {
        String depthString = generateDepthString(depth);
        System.out.print(depthString);
        System.out.println("Call: remove " + val + " in " + head);
        // 对于问题规模最小情况
        if (head == null) {
            System.out.print(depthString);
            System.out.println("Return: " + head);
            return null;
        }
        // 问题不断小化,头结点分离，对于头结点后面的链表进行删除元素操作
        // 无论head如何，都将head与后面的红色部分连接起来。
        ListNode res = removeElements(head.next, val, depth + 1);
        System.out.print(depthString);
        System.out.println("After remove " + val + ": " + res);

        ListNode ret;
        if (head.val == val)
            ret = res;
        else {
            head.next = res;
            ret = head;
        }
        System.out.print(depthString);
        System.out.println("Return: " + ret);
        return ret;
    }

    private String generateDepthString(int depth) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            res.append("--"); // 深度越深，字符串中--数量越多。
        }
        return res.toString();
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 6, 3, 4, 5, 6};
        ListNode head = new ListNode(nums);
        System.out.println(head);

        ListNode res = new SolutionRecusionCopySmallPrint().removeElements(head, 6, 0);
        System.out.println(res);
    }
}
