package ex.chapter2.section2;

import edu.princeton.cs.algs4.StdOut;

/**
 * 2.2.11 改进。
 * 实现 2.2.2 节所述的对归并排序的三项改进:
 * 1. 加快小数组的排序速度,
 * 2. 检测数组是否已经有序,
 * 3. 通过在递归中交换参数来避免数组复制。
 */
public class Ex11 {
    private static Comparable[] aux;    //归并所需的辅助数组

    public static void sort(Comparable[] a) {
        aux = new Comparable[a.length]; //一次性分配空间
        if (a.length < 15) {
            insertSort(a, 0, a.length - 1);
        } else {
            sort(a, 0, a.length - 1);
        }

    }

    private static void sort(Comparable[] a, int lo, int hi) {    //将数组a[lo..hi]排序
        if (hi <= lo) return;
        int mid = lo + (hi - lo) / 2;
        //1. 加快小数组的排序速度
        if (hi - lo < 10) {
            insertSort(a, lo, hi);
            return;
        }
        sort(a, lo, mid);   //将左半边排序
        sort(a, mid + 1, hi);   //将右半边排序
        if (less(a[mid], a[mid + 1])) return;   //2. 检测数组是否已经有序
        merge(a, lo, mid, hi);
    }

    public static void merge(Comparable[] a, int lo, int mid, int hi) {
        //将a[lo..mid]和a[mid+1..hi]归并
        int i = lo;
        int j = mid + 1;
        for (int k = lo; k <= hi; k++) {    //将a[lo..hi]复制到aux[lo..hi]
            aux[k] = a[k];
        }
        for (int k = lo; k <= hi; k++) {    //归并回到a[lo..hi]
            if (i > mid) a[k] = aux[j++];
            else if (j > hi) a[k] = aux[i++];
            else if (less(aux[j], aux[i])) a[k] = aux[j++];
            else a[k] = aux[i++];
        }
    }

    /**
     * 使用插入排序处理小规模的子数组
     *
     * @param a  待排序的数组
     * @param lo
     * @param hi
     */
    private static void insertSort(Comparable[] a, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++) {
            for (int j = i; j > lo && less(a[j], a[j - 1]); j--) {
                exch(a, j, j - 1);
            }
        }
    }

    private static boolean less(Comparable v, Comparable w) {    //比较元素的大小
        return v.compareTo(w) < 0;
    }

    private static void exch(Comparable[] a, int i, int j) { //将元素交换位置
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    private static void show(Comparable[] a) {  //在单行打印数组
        for (int i = 0; i < a.length; i++) {
            StdOut.print(a[i] + " ");
        }
        StdOut.println();
    }

    private static boolean isSorted(Comparable[] a) {   //测试数组元素是否有序
        for (int i = 1; i < a.length; i++) {
            if (less(a[i], a[i - 1])) {
                return false;
            }
        }
        return true;
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
