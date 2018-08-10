package cn.mtianyan.array;

public class ArrayTestDynamic {
    public static void main(String[] args) {
        Array<Integer> array = new Array<>();
        for (int i = 0; i < 10; i++) {
            array.addLast(i);
        }
        System.out.println(array);
        array.addFirst(99);
        System.out.println(array);
        array.addLast(99);
        System.out.println(array);
        array.removeAllElement(99);
        System.out.println(array);
        for (int i = 0; i < 5; i++) {
            array.removeElement(i);
        }
        System.out.println(array);
    }
}
