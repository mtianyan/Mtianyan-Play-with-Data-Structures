package cn.mtianyan.array;

public class Array<E> {
    private E[] data;
    private int size;

    /**
     * 带容量参数构造函数
     *
     * @param capacity 数组容量
     */
    public Array(int capacity) {
        data = (E[]) new Object[capacity];
        size = 0;
    }

    /**
     * 默认构造函数(默认容量为10)
     */
    public Array() {
        this(10);
    }

    /**
     * 静态数组入参构造函数
     *
     * @param arr 传入静态数组
     */
    public Array(E[] arr) {
        data = (E[]) new Object[arr.length];
        for (int i = 0; i < arr.length; i++)
            data[i] = arr[i];
        size = arr.length;
    }

    /**
     * 获取数组元素个数
     *
     * @return size 数组元素个数
     */
    public int getSize() {
        return size;
    }

    /**
     * 获取数组的容量
     *
     * @return capacity 获取容量
     */
    public int getCapacity() {
        return data.length;
    }

    /**
     * 判断数组是否为空
     *
     * @return 是否为空
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 判断数组是否满了
     *
     * @return 是否为满
     */
    public boolean isFull() {
        return size == data.length;
    }

    /**
     * 向所有元素末尾添加一个新元素。
     *
     * @param e 添加的元素
     */
    public void addLast(E e) {
        //        if (isFull()){
        //            throw new IllegalArgumentException("AddLast failed. Array is Full");
        //        }else {
        //            data[size] =e; // data[size++] =e;
        //            size++;
        //        }
        add(size, e);
    }

    /**
     * 向索引0号位置添加元素
     *
     * @param e 添加的元素
     */
    public void addFirst(E e) {
        add(0, e);
    }

    /**
     * 在index位置插入一个新元素e
     *
     * @param index 插入位置索引
     * @param e     插入元素
     */
    public void add(int index, E e) {
        if (isFull())
            // throw new IllegalArgumentException("AddLast failed. Array is Full");
            resize(2 * data.length);
        // 大于size就不是紧密排列了
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("AddLast failed. Required index<0 or index>size ");
        } else {
            // 从哪开始挪呢: 从size-1这个元素(size本身是指向空的),挪到哪个呢，index位置的这个元素也是要挪的。
            for (int i = size - 1; i >= index; i--) {
                data[i + 1] = data[i]; // 后一个等于前一个，从数组最后一个元素开始。
                // 极端值验证: size 1 index 0;(i=0;i>=0;i--)会执行一次data[1]=data[0].正确。
            }
            data[index] = e;
            size++;
        }
    }

    /**
     * 改变数组容量，创建新数组并复制原本元素。
     *
     * @param newCapacity
     */
    private void resize(int newCapacity) {
        E[] newData = (E[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newData[i] = data[i];
        }
        data = newData;
    }


    /**
     * 打印数组信息及遍历元素。
     *
     * @return 数组信息和元素遍历结果
     */
    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(String.format("Array: size = %d, capacity = %d\n", size, data.length));
        res.append('[');
        for (int i = 0; i < size; i++) {
            res.append(data[i]);
            if (i != size - 1) // 最后一个元素不要加，
                res.append(", ");
        }
        res.append(']');
        return res.toString();
    }

    /**
     * 传入索引，获取该位置元素
     *
     * @param index 要获取的元素索引
     * @return 返回该位置数组元素
     */
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Get failed. Required index<0 or index>=size ");
        } else {
            return data[index];
        }
    }

    /**
     * 获取第一个元素
     *
     * @return
     */
    public E getFirst() {
        return get(0);
    }

    /**
     * 获取最后一个元素
     *
     * @return
     */
    public E getLast() {
        return get(size - 1);
    }

    /**
     * 传入索引和元素值，将该位置元素设置为传入值
     *
     * @param index 索引
     * @param e     传入值
     */
    public void set(int index, E e) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Set failed. Required index<0 or index>=size ");
        } else {
            data[index] = e;
        }
    }

    /**
     * 查找数组中是否有元素e
     *
     * @param e
     * @return 包含 true; 不包含 false
     */
    public boolean contains(E e) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(e)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 查找数组中元素，返回其索引(第一个遇到)
     *
     * @param e 元素
     * @return 不存在 -1; 存在 i
     */
    public int find(E e) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(e)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 查找数组中元素，返回所有与该元素相同的元素索引。
     *
     * @param e
     * @return 以索引为元素的int数组
     */
    public int[] findAll(E e) {
        int[] tempArray = new int[size];
        int index = 0;
        for (int i = 0; i < size; i++) {
            if (data[i].equals(e)) {
                tempArray[index] = i;
                index++;
            }
        }
        int[] indexArray = new int[index];
        for (int i = 0; i < index; i++) {
            indexArray[i] = tempArray[i];
        }
        return indexArray;
    }

    /**
     * 删除数组元素，返回删除的元素值
     *
     * @param index 索引
     * @return 该索引位置元素值
     */
    public E remove(int index) {
        // 判空，空数组 removeFirst index=0,size=0，index>=size异常。空数组 removeLast index=-1 index<0异常;
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Remove failed. Required index<0 or index>=size ");
        } else {
            E ret = data[index];
            for (int i = index + 1; i < size; i++) {
                // 从哪个元素开始挪，从index位置的后一个元素开始，挪到哪个元素结束，挪到size-1(因此没等号)
                data[i - 1] = data[i]; // 前一个等于后一个
            }
            size--;
            data[size] = null;

            if (size == data.length / 4 && data.length / 2 != 0)
                resize(data.length / 2);
            return ret;
        }
    }

    /**
     * 删除第一个(索引0)元素
     *
     * @return 删除的元素值
     */
    public E removeFirst() {
        return remove(0);
    }

    /**
     * 删除最后一个(索引size-1)元素
     *
     * @return 删除的元素值
     */
    public E removeLast() {
        return remove(size - 1);
    }

    /**
     * 删除数组中某一个元素值（删除数组中第一个找到的）
     *
     * @param e 元素值
     * @return 删除是否成功
     */
    public boolean removeElement(E e) {
        int index = find(e);
        if (index != -1) {
            remove(index);
            return true;
        }
        return false;
    }

    /**
     * 删除数组中包含的所有该元素值
     *
     * @param e 元素值
     * @return 删除成功与否
     */
    public boolean removeAllElement(E e) {
        int[] indexArray = findAll(e);
        if (indexArray.length != 0) {
            for (int i = 0; i < indexArray.length; i++) {
                remove(indexArray[i] - i); // 此处注意-i的巧妙，因为每删除一个，index就会重新变化。
            }
            return true;
        }
        return false;
    }

    /**
     * 交换传入的索引两个位置的元素值
     *
     * @param i
     * @param j
     */
    public void swap(int i, int j) {
        if (i < 0 || i >= size || j < 0 || j >= size)
            throw new IllegalArgumentException("Index is illegal.");

        E temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }

}
