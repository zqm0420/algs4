package learn.chapter2;

public class MinPQ<Key extends Comparable<Key>> {
    private Key[] pq;           //基于堆的完全二叉树
    private int n = 0;          //存储于pq[1..N]中, pq[0]没有使用

    /**
     * 初始化一个空的优先队列
     */
    public MinPQ() {            //创建一个优先队列
        this(1);
    }

    /**
     * 创建一个初始容量为max的优先队列
     *
     * @param maxN
     */
    public MinPQ(int maxN) {
        pq = (Key[]) new Comparable[maxN + 1];
    }

    /**
     * 用a[]中的元素创建一个优先队列
     *
     * @param a
     */
    public MinPQ(Key[] a) {
        pq = (Key[]) new Comparable[a.length + 1];
        for (int i = 1; i <= a.length; i++) {
            pq[i] = a[i - 1];
        }
        for (int i = n/2; i >=1 ; i--) {
            sink(i);
        }
    }

    /**
     * 返回队列是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return n == 0;
    }

    /**
     * 返回优先队列中的元素个数
     *
     * @return
     */
    public int size() {
        return n;
    }

    /**
     * 动态调整数组的大小
     *
     * @param max
     */
    private void resize(int max) {
        Key[] temp = (Key[]) new Comparable[max];
        for (int i = 0; i <= n; i++) temp[i] = pq[i];
        pq = temp;
    }

    /***************************************
     * 用于比较和交换的辅助函数
     ***************************************/
    private boolean less(int i, int j) {
        return pq[i].compareTo(pq[j]) < 0;
    }

    private void exch(int i, int j) {
        Key t = pq[i];
        pq[i] = pq[j];
        pq[j] = t;
    }

    /***************************************
     * 用于恢复堆有序的辅助函数
     ***************************************/
    private void swim(int i) {
        while (i > 1 && less(i, i / 2)) {
            exch(i, i / 2);
            i = i / 2;
        }
    }

    private void sink(int i) {
        while (2 * i <= n) {
            int j = 2 * i;
            if (j < n && less(j + 1, j)) j++;
            if (less(i, j)) break;
            exch(i, j);
            i = j;
        }
    }

    /**
     * 向优先队列中插入一个元素
     *
     * @param v
     */
    public void insert(Key v) {
        if (pq.length == n + 1) resize(2 * pq.length);
        pq[++n] = v;
        swim(n);
    }

    /**
     * 返回最小元素
     *
     * @return
     */
    public Key min() {
        return pq[1];
    }

    /**
     * 删除并返回最小元素
     *
     * @return
     */
    public Key delMin() {
        Key min = pq[1];
        exch(1, n);
        pq[n--] = null;
        sink(1);
        if (n > 1 && n == (pq.length - 1) / 4) resize(pq.length / 2);
        return min;
    }

}
