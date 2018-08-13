package cn.mtianyan;

public class Main {

    public static void main(String[] args) {

        BST<Integer> bst = new BST<>();
        int[] nums = {5, 3, 6, 8, 4, 2};
        for (int num : nums)
            bst.add(num);
        /////////////////
        //      5      //
        //    /   \    //
        //   3    6    //
        //  / \    \   //
        // 2  4     8  //
        /////////////////
        bst.preOrder();
        System.out.println();

        bst.inOrder(); // 2 3 4 5 6 8
        System.out.println();

        bst.postOrder(); // 2 4 3 8 6 5
        System.out.println();

        bst.preOrderNR(); // 5 3 2 4 6 8
        System.out.println();

        System.out.printf("非递归中序: ");
        bst.inOrderNR();
        System.out.println();

        System.out.printf("非递归后序: ");
        bst.postOrderNR();
        System.out.println();

        System.out.printf("队列实现层序遍历: ");
        bst.levelOrder();
        System.out.println();

        System.out.println(bst);
    }
}

