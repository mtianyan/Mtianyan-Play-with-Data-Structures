package cn.mtianyan.linked;

public class LinkedListTest {

    public static void main(String[] args) {
        LinkedList<Integer> linkedList = new LinkedList<>();
        for (int i = 0; i < 5; i++) {
            linkedList.addFirst(i);
            System.out.println(linkedList);
        }
        linkedList.add(2, 888);
        System.out.println(linkedList);

        linkedList.remove(2);
        System.out.println(linkedList);
        linkedList.removeFirst();
        System.out.println(linkedList);
        linkedList.removeLast();
        System.out.println(linkedList);

        System.out.println("=====测试链表传入数组的构造方法=====");
        // 传入数组
        Integer[] arr = {1, 2, 3, 4};
        LinkedList<Integer> listFromArray = new LinkedList<Integer>(arr);
        System.out.println(listFromArray.getSize());
        System.out.println(listFromArray);
    }
}
