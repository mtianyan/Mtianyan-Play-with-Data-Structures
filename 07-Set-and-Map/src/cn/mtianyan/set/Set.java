package cn.mtianyan.set;

public interface Set<E> {

    void add(E e);

    void remove(E e);

    boolean contanins(E e);

    int getSize();

    boolean isEmpty();
}
