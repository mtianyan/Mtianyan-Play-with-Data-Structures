package cn.mtianyan;

public class SolutionRecusion2 {
    public ListNode removeElements(ListNode head, int val,int depth) {
        
        String depthString = generateDepthString(depth);
        System.out.println(depthString);
        System.out.println("Call: remove "+ val+"in"+head);

        if(head == null) // 求解最基本问题
            return null;
        
        head.next = removeElements(head.next,val,depth+1); // 将原问题转换为更小问题
        return head.val == val?head.next:head;
    }

    private String generateDepthString(int depth) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            res.append("--");
        }
        return res.toString();
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 6, 3, 4, 5, 6};
        ListNode head = new ListNode(nums);
        System.out.println(head);

        ListNode res = new SolutionRecusion2().removeElements(head, 6);
        System.out.println(res);
    }
}
