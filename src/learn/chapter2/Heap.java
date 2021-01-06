package learn.chapter2;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * 堆排序
 */
public class Heap {
    private static void sort(Comparable[] a) {
        //首先构建堆
        int n = a.length;
        for (int i = n / 2; i > 0; i--) {
            sink(a, i, n);
        }
        //然后堆排序
        int j = n;
        while (j>1){
            exch(a, 1, j--);
            sink(a, 1, j);
        }
    }

    private static void sink(Comparable[] a, int i, int n) {
        while (2 * i <= n) {
            int j = 2 * i;
            if (j < n && less(a, j, j + 1)) j++;
            if (!less(a, i, j)) break;
            exch(a, j, i);
            i = j;
        }
    }

    /******************************
     *  辅助函数，因为堆从0开始，所以i和j都减一
     *****************************/
    private static boolean less(Comparable[] a, int i, int j) {
        return a[i - 1].compareTo(a[j - 1]) < 0;
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable temp = a[i - 1];
        a[i - 1] = a[j - 1];
        a[j - 1] = temp;
    }

    private static void show(Comparable[] a) {
        for (Comparable c : a) {
            System.out.print(c + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        String str = "S O R T E X A M P L E";
        String[] a = str.split(" ");
        show(a);
        sort(a);
        show(a);
    }
}
