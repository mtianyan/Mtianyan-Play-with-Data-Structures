package cn.mtianyan.set;

import cn.mtianyan.BST;

public class BSTSet<E extends Comparable<E>> implements Set<E> {
    private BST<E> bst;

    public BSTSet() {
        bst = new BST<>();
    }

    @Override
    public void add(E e) {
        bst.add(e); // 本身就可以对于重复不理会
    }

    @Override
    public void remove(E e) {
        bst.remove(e);
    }

    @Override
    public boolean contanins(E e) {
        return bst.contains(e);
    }

    @Override
    public int getSize() {
        return bst.getSize();
    }

    @Override
    public boolean isEmpty() {
        return bst.isEmpty();
    }
}
