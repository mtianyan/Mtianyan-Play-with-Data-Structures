package cn.mtianyan;

public interface UF {
    int getSize(); // 对当下这些元素

    boolean isConnected(int p, int q); // id为p id为q是否相连

    void unionElements(int p, int q);
}
