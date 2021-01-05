package learn.chapter2;

import java.util.Arrays;

public class IndexMaxPQ<Item extends Comparable<Item> {
    private int[] pq;
    private int[] qp;
    private Item[] elements;
    private int n;


    public IndexMaxPQ() {
        this(1);
    }

    /**
     * 构造函数：初始化一个初始容量为capacity+1的索引队列
     *
     * @param capacity
     */
    public IndexMaxPQ(int capacity) {
        pq = new int[capacity + 1];
        qp = new int[capacity + 1];
        elements = (Item[]) new Comparable[capacity + 1];
        Arrays.fill(qp, -1);
    }

    /**
     * 构造函数：使用一个数组来初始化索引队列
     *
     * @param a
     */
    public IndexMaxPQ(Item[] a) {
        elements = (Item[]) new Comparable[a.length + 1];
        for (int i = 0; i < a.length; i++) {
            elements[i + 1] = a[i];
            swim(i + 1);
        }
    }

    public void insert(int k, Item item) {
        if (qp[k] != -1) {
            return;
        }
        if (n == pq.length - 1) {
            resize(2 * pq.length);
        }
        n++;
        pq[n] = n;


    }

    public void change(int k, Item item) {
    }

    public boolean contains(int k) {

    }

    public void delete(int k) {
    }

    public Item max() {

    }

    public int minIndex() {
    }

    public int delMin() {
    }

    public boolean isEmpty() {
        return n == 1;
    }

    public int size() {
        return n;
    }

    /***************************************
     * 用于恢复堆有序的辅助函数
     ***************************************/
    private void swim(int k) {
        while (k > 1 && less(k / 2, k)) {
            exch(k / 2, k);
            k = k / 2;
        }
    }

    private void sink(int k) {
        while (2 * k <= n) {
            int j = 2 * k;
            if (less(j, j + 1)) j = j + 1;
            if (less(j, k)) break;
            exch(j, k);
            k = j;
        }
    }

    /***************************************
     * 用于动态调整数组大小的辅助函数
     **************************************/
    private void resize(int max) {
        int[] temp = new int[max];
        Item[] itemTemp = (Item[]) new Comparable[max];
        for (int i = 0; i <= pq.length; i++) {
            temp[i] = pq[i];
        }
        pq = temp;
        for (int i = 0; i < qp.length; i++) {
            temp[i] = qp[i];
        }
        qp = temp;
        for (int i = 0; i < elements.length; i++) {
            itemTemp[i] = elements[i];
        }
        elements = itemTemp;
    }

    /***************************************
     * 用于比较和交换的辅助函数
     ***************************************/
    private boolean less(int i, int j) {
        return elements[pq[i]].compareTo(elements[pq[j]]) < 0;
    }

    private void exch(int i, int j) {
        int t = pq[i];
        pq[i] = pq[j];
        pq[j] = t;
    }
}
