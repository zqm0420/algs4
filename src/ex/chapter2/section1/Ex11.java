package ex.chapter2.section1;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

/**
 * 将希尔排序中实时计算递增序列改为预先计算并存储在一个数组内
 */

public class Ex11 {
    public static void shellSort(Comparable[] a) {
        int N = a.length;
        int h = 1;
        int[] hArray;   //存放递增序列的数组
        int count = 0;  //递增序列数组大小
        //计算count大小
        while (h < N / 3) {
            h = h * 3 + 1;
            count++;
        }
        hArray = new int[count];
        h = 1;
        //对数组进行赋值
        for (int i = 0; i < count; i++) {
            hArray[i] = h;
            h = h * 3 + 1;
            if (h >= N / 3) {
                break;
            }
        }
        //使用数组中的h进行希尔排序
        for (int i = count - 1; i >= 0; i--) {
            h = hArray[i];
            for (int j = h; j < N; j++) {
                for (int k = j; k >= h && less(a[k], a[k - h]); k -= h) {
                    exch(a, k, k - h);
                }
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

    public static void main(String[] args) {
        String[] a = In.readStrings();
        shellSort(a);
        assert isSorted(a);
        show(a);
    }

}
