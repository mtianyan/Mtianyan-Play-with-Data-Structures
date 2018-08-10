package cn.mtianyan.array;

public class ArrayTest {
    public static void main(String[] args) {
        Array<Integer> array = new Array<>(20);
        for (int i = 0; i < 10; i++) {
            array.addLast(i);
        }
        System.out.println(array);
        array.add(1, 100);
        System.out.println(array);

        array.addFirst(-1);
        System.out.println(array);

        System.out.println(array.get(array.getSize() - 1));

        array.set(0, -99);
        System.out.println(array);

        System.out.println("===============加入重复元素后数组如下===================");
        array.add(3, 3);
        array.add(array.getSize() - 1, 9);
        System.out.println(array);
        System.out.println("================包含 寻找测试===========================");
        System.out.println(array.contains(-99));
        System.out.println(array.contains(-100));
        System.out.println("3的index: " + array.find(3));
        int[] tmpArr = array.findAll(3);
        for (int i : tmpArr) {
            System.out.print(i + " ");
        }
        System.out.println();
        System.out.println("================开始删除测试========================");
        System.out.println(array);
        array.remove(3); // 删除一个3
        System.out.println(array);
        array.removeElement(1); // 删除1
        System.out.println(array);
        System.out.println("=====删除index3 后 删除元素1如上====");

        array.removeAllElement(9);
        System.out.println(array);
        System.out.println("=====删除所有9后 结果如上=====");
        array.addFirst(-99);
        array.removeAllElement(-99);
        System.out.println(array);
        System.out.println("=====首位添加-99，后删除所有-99 结果如上=====");


        array.add(4, 99);
        array.add(5, 99);
        array.addFirst(99);
        array.addLast(99);
        array.add(7, 99);
        System.out.println(array);
        System.out.println("=====上面为删除99前，下面为删除99后=====");
        array.removeAllElement(99);
        System.out.println(array);
    }
}
