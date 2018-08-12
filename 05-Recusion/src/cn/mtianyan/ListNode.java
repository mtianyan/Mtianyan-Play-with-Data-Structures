package cn.mtianyan;

public class ListNode {
    int val;
    ListNode next;

    /**
     * 带参构建函数，创建一个单个节点
     *
     * @param x
     */
    ListNode(int x) {
        val = x;
    }

    /**
     * 链表节点的构造函数
     * 使用arr作为参数，创建一个链表，当前的listNode为链表头结点。
     *
     * @param arr
     */
    public ListNode(int[] arr) {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("Arr can not be empty");
        }
        this.val = arr[0];
        ListNode cur = this;
        for (int i = 1; i < arr.length; i++) {
            cur.next = new ListNode(arr[i]);
            cur = cur.next;
        }
    }

    /**
     * 返回以当前节点为头结点的链表信息字符串
     *
     * @return
     */
    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("List :");
        ListNode cur = this;
        while (cur != null) {
            res.append(cur.val + "->");
            cur = cur.next;
        }
        res.append("NULL");
        return res.toString();
    }
}
