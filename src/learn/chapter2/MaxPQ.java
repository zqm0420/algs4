package learn.chapter2;

/**
 * 最优队列
 */
public class MaxPQ<Key extends Comparable<Key>> {
    private Key[] pq;           //基于堆的完全二叉树
    private int N = 0;          //存储于pq[1..N]中, pq[0]没有使用

    /**
     * 初始化一个空的优先队列
     */
    public MaxPQ() {            //创建一个优先队列
        this(1);
    }

    /**
     * 创建一个初始容量为max的优先队列
     *
     * @param maxN
     */
    public MaxPQ(int maxN) {
        pq = (Key[]) new Comparable[maxN + 1];
    }

    /**
     * 用a[]中的元素创建一个优先队列
     *
     * @param a
     */
    public MaxPQ(Key[] a) {
        pq = (Key[]) new Comparable[a.length + 1];
        for (int i = 1; i <= a.length; i++) {
            pq[i] = a[i - 1];
        }
        for (int i = N / 2; i >= 1; i--) {
            sink(i);
        }
    }

    /**
     * 返回队列是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return N == 0;
    }

    /**
     * 返回优先队列中的元素个数
     *
     * @return
     */
    public int size() {
        return N;
    }

    /***************************************
     * 用于动态调整数组大小的辅助函数
     **************************************/
    private void resize(int max) {
        Key[] temp = (Key[]) new Comparable[max];
        for (int i = 0; i <= N; i++) {
            temp[i] = pq[i];
        }
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
    private void swim(int k) {      //由下至上的堆有序化(上浮)
        while (k > 1 && less(k / 2, k)) {
            exch(k / 2, k);
            k = k / 2;
        }
    }

    private void sink(int k) {      //由上至下的堆有序化(下沉)
        while (2 * k <= N) {
            int j = 2 * k;
            if (j < N && less(j, j + 1)) j++;
            if (!less(k, j)) break;
            exch(j, k);
            k = j;
        }
    }

    /**
     * 向优先队列中插入一个元素
     *
     * @param v
     */
    public void insert(Key v) {
        if (N == pq.length - 1) {
            resize(pq.length);
        }
        pq[++N] = v;
        swim(N);
    }

    /**
     * 返回最大元素
     *
     * @return
     */
    public Key max() {
        return pq[1];
    }

    /**
     * 删除并返回最大元素
     *
     * @return
     */
    public Key delMax() {
        Key max = pq[1];
        exch(1, N);
        pq[N--] = null;
        sink(1);
        if (N > 1 && N == (pq.length - 1) / 4) {
            resize(pq.length / 2);
        }
        return max;
    }

    public static void main(String[] args) {
        String str = "D I E M C P Q L Z";
        String[] a = str.split(" ");
        MaxPQ<String> maxPQ = new MaxPQ<>();
        for (int i = 0; i < a.length; i++) {
            maxPQ.insert(a[i]);
            if (i == 2 || i == 7) {
                maxPQ.delMax();
            }
        }
    }
}
