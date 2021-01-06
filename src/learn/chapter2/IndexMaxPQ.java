package learn.chapter2;


import java.util.Arrays;
import java.util.NoSuchElementException;

public class IndexMaxPQ<Item extends Comparable<Item>> {
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
        n = 0;
        pq = new int[n + 1];
        qp = new int[n + 1];
        elements = (Item[]) new Comparable[n + 1];

        Arrays.fill(qp, -1);
    }

    /**
     * 构造函数：使用一个数组来初始化索引队列
     *
     * @param a
     */
    public IndexMaxPQ(Item[] a) {
        elements = (Item[]) new Comparable[a.length + 1];
        pq = new int[elements.length];
        qp = new int[elements.length];
        Arrays.fill(qp, -1);
        for (int i = 0; i < a.length; i++) {
            insert(i+1, a[i]);
        }
    }

    public void insert(int k, Item item) {
        if (k <= 0) {
            throw new IllegalArgumentException("索引必须是大于等于1的整数");
        }
        if (n == pq.length - 1) {
            resizePQ(2 * pq.length);
        }
        if (k >= qp.length - 1) {
            resizeQP(2 * qp.length);
            resizeElements(2 * qp.length);
        }
        if (qp[k] != -1) {
            throw new IllegalArgumentException("当前索引已经有引用存在");
        }
        n++;
        pq[n] = k;
        elements[k] = item;
        qp[k] = n;
        swim(n);
    }

    public void change(int k, Item item) {
        if (!contains(k)){
            throw new IllegalArgumentException();
        }
        elements[k] = item;
        swim(qp[k]);
        sink(qp[k]);
    }

    public boolean contains(int k) {
        if (k<=0){
            return false;
        }else if(qp[k]==-1){
            return false;
        }
        return true;
    }

    public void delete(int k) {
        if (!contains(k)){
            throw new NoSuchElementException("索引不合法或队列中没有当前索引的元素");
        }
        int index = qp[k];  //要删除的元素对应pq数组的索引
        exch(index, n);
        elements[k] = null;
        qp[k] = -1;
        pq[n--] = 0;
        swim(index);
        sink(index);
    }

    public Item max() {
        if (isEmpty()){
            throw new NoSuchElementException("优先队列中已经没有值了");
        }
        return elements[pq[1]];
    }

    //
    public int maxIndex() {
        if (isEmpty()){
            throw new NoSuchElementException("优先队列为空");
        }
        return pq[1];
    }

    public int delMax() {
        if (isEmpty()){
            throw new NoSuchElementException("优先队列为空");
        }
        exch(1, n);
        int index = pq[n];
        elements[index] = null;
        qp[index] = -1;
        pq[n--] = 0;
        sink(1);
        return index;

    }

    public boolean isEmpty() {
        return n == 0;
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
            if (j != n && less(j, j + 1)) j = j + 1;
            if (less(j, k)) break;
            exch(j, k);
            k = j;
        }
    }

    /***************************************
     * 用于动态调整数组大小的辅助函数
     **************************************/
    private void resizePQ(int max) {
        int[] temp = new int[max];
        for (int i = 0; i < pq.length; i++) {
            temp[i] = pq[i];
        }
        pq = temp;
    }

    private void resizeQP(int max) {
        int[] temp = new int[max];
        Arrays.fill(temp, -1);
        for (int i = 0; i < qp.length; i++) {
            temp[i] = qp[i];
        }
        qp = temp;
    }

    private void resizeElements(int max) {
        Item[] temp = (Item[]) new Comparable[max];
        for (int i = 0; i < elements.length; i++) {
            temp[i] = elements[i];
        }
        elements = temp;
    }

    /***************************************
     * 用于比较和交换的辅助函数
     ***************************************/
    private boolean less(int i, int j) {
        return elements[pq[i]].compareTo(elements[pq[j]]) < 0;
    }


    private void exch(int i, int j) {
        int t1 = pq[i];
        pq[i] = pq[j];
        pq[j] = t1;
        int t2 = qp[pq[i]];
        qp[pq[i]] = qp[pq[j]];
        qp[pq[j]] = t2;
    }

    public static void main(String[] args) {
        String str = "D I E M C P A Q L Z";
        String[] a = str.split(" ");
        IndexMaxPQ<String> indexMaxPQ = new IndexMaxPQ<>(a);
//        for (int i = 0; i < a.length; i++) {
//            if (i == 3 || i == 6) continue;
//
//            indexMaxPQ.insert(i + 1, a[i]);
//            if (i == 5 || i == 7) {
//                indexMaxPQ.delMax();
//            }
//        }
        for (String s : a) {
            System.out.print(s + " ");
        }
        System.out.println();
        System.out.println("最大元素索引为：" + indexMaxPQ.maxIndex());
        System.out.println("最大元素为：" + indexMaxPQ.max());
        indexMaxPQ.change(10, "D");
        for (int i = 0; i < 10; i++) {
            System.out.println(indexMaxPQ.contains(i));
        }
        indexMaxPQ.delete(9);
        while (indexMaxPQ.max() != null) {
            System.out.println(indexMaxPQ.max());
            indexMaxPQ.delMax();
        }
    }
}
