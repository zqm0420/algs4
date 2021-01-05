package ex.chapter2.section2;

import edu.princeton.cs.algs4.StdOut;

import java.util.Comparator;


/**
 * 2.2.11 改进。
 * 实现 2.2.2 节所述的对归并排序的三项改进:
 * 1. 加快小数组的排序速度,
 * 2. 检测数组是否已经有序,
 * 3. 通过在递归中交换参数来避免数组复制。
 */
public class Ex11 {
    private static final int CUTOFF = 7;

    public static void sort(Comparable[] src) {
        Comparable[] aux = src.clone();
        sort(aux, src, 0, src.length - 1);
    }

    private static void sort(Comparable[] src, Comparable[] dst, int lo, int hi) {    //将数组a[lo..hi]排序
//        if (hi <= lo) return;
        //1. 加快小数组的排序速度
        if (hi <= CUTOFF + lo - 1) {
            insertSort(dst, lo, hi);
            return;
        }
        int mid = lo + (hi - lo) / 2;

        sort(dst, src, lo, mid);   //将左半边排序
        sort(dst, src, mid + 1, hi);   //将右半边排序
//        if (!less(src[mid + 1], src[mid])) return;   //2. 检测数组是否已经有序
        if (!less(src[mid + 1], src[mid])) {            //2. 检测数组是否已经有序
            System.arraycopy(src, lo, dst, lo, hi - lo + 1);
            return;
        }
        merge(src, dst, lo, mid, hi);
    }

    public static void merge(Comparable[] src, Comparable[] dst, int lo, int mid, int hi) {
        assert isSorted(src, lo, mid);
        assert isSorted(src, mid+1, hi);

        //将a[lo..mid]和a[mid+1..hi]归并
        int i = lo;
        int j = mid + 1;

        for (int k = lo; k <= hi; k++) {    //归并回到a[lo..hi]
            if (i > mid) dst[k] = src[j++];
            else if (j > hi) dst[k] = src[i++];
            else if (less(src[j], src[i])) dst[k] = src[j++];
            else dst[k] = src[i++];
        }
        assert isSorted(dst);
    }

    /**
     * 使用插入排序处理小规模的子数组
     *
     * @param a  待排序的数组
     * @param lo
     * @param hi
     */
    private static void insertSort(Comparable[] a, int lo, int hi) {
        for (int i = lo; i <= hi; i++) {
            for (int j = i; j > lo && less(a[j], a[j - 1]); j--) {
                exch(a, j, j - 1);
            }
        }
    }




    // exchange a[i] and a[j]
    private static void exch(Object[] a, int i, int j) {
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    // is a[i] < a[j]?
    private static boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }

    // is a[i] < a[j]?
    private static boolean less(Object a, Object b, Comparator comparator) {
        return comparator.compare(a, b) < 0;
    }

    private static boolean isSorted(Comparable[] a) {
        return isSorted(a, 0, a.length - 1);
    }

    private static boolean isSorted(Comparable[] a, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++)
            if (less(a[i], a[i - 1])) return false;
        return true;

    }

    // print array to standard output
    private static void show(Object[] a) {
        for (int i = 0; i < a.length; i++) {
            StdOut.print(a[i]+" ");
        }
        System.out.println();
    }


    public static void main(String[] args) {    //从标准输入读取字符串, 将它们排序并输出
//        String[] a = In.readStrings();
        String str = "EASYQUESTIONTRANSFERTRANSFERTRANSFERTRANSFER";
        String[] a = new String[12 + 32];
        for (int i = 0; i < a.length; i++) {
            a[i] = str.substring(i, i + 1);
        }
        show(a);
        sort(a);

        assert isSorted(a);
        show(a);
    }
}
