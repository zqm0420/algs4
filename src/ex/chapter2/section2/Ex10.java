package ex.chapter2.section2;

import edu.princeton.cs.algs4.StdOut;

/**
 * 2.2.10 快速归并。
 * 实现一个merge()方法,按降序将 a[]的后半部分复制到 aux[],然后将其归并回a[]中。
 * 这样就可以去掉内循环中检测某半边是否用尽的代码。
 * 注意:这样的排序产生的结果是不稳定的(请见2.5.1.8节)。
 */

public class Ex10 {
    private static Comparable[] aux;    //归并所需的辅助数组

    public static void sort(Comparable[] a) {
        aux = new Comparable[a.length]; //一次性分配空间
        sort(a, 0, a.length - 1);
    }

    private static void sort(Comparable[] a, int lo, int hi) {    //将数组a[lo..hi]排序
        if (hi <= lo) return;
        int mid = lo + (hi - lo) / 2;
        sort(a, lo, mid);   //将左半边排序
        sort(a, mid + 1, hi);   //将右半边排序
        merge(a, lo, mid, hi);
    }

    public static void merge(Comparable[] a, int lo, int mid, int hi) {
        //将a[lo..mid]和a[mid+1..hi]归并
        for (int i = lo; i <= mid; i++) {
            aux[i] = a[i];
        }

        for (int j = mid + 1; j <= hi; j++) {
            aux[j] = a[hi - j + mid + 1];
        }

        int i = lo;
        int j = hi;
        for (int k = lo; k <= hi; k++) {    //归并回到a[lo..hi]
            if (less(aux[j], aux[i])) a[k] = aux[j--];
            else a[k] = aux[i++];
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
        String str = "EASYQUESTION";
        String[] a = new String[12];
        for (int i = 0; i < a.length; i++) {
            a[i] = str.substring(i, i + 1);
        }
        sort(a);
        assert isSorted(a);
        show(a);
    }
}
