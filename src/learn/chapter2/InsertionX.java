package learn.chapter2;

/**
 * 2.1.24 插入排序的哨兵。在插人排序的实现中先找出最小的元素并将其置于数组的最左边,这样就能
 * 去押内循环的判断条件 j>0。使用 SortCompare来评估这种做法的效果。
 * 注意:这是一种常见的规避边界测试的方法,能够省略判断条件的元素通常被称为哨兵。
 */

import edu.princeton.cs.algs4.StdOut;

public class InsertionX {
    public static void sort(Comparable[] a) {
        //我的插入排序的哨兵
//        int n = a.length;
//
//        //先找到值最小的元素放在最左边
//        int min = 0;
//        for (int i = 1; i < n; i++) {
//            if (less(a[i], a[min])){
//                min = i;
//            }
//        }
//        exch(a, min, 0);
//
//        for (int i = 2; i < n; i++) {
//            while (less(a[i], a[i-1])){
//                exch(a, i, i-1);
//                i--;
//            }
//        }

        //大神的插入排序的哨兵
//        int n = a.length;
//        // put smallest element in position to serve as sentinel
//        int exchanges = 0;
//        for (int i = n-1; i > 0; i--) {
//            if (less(a[i], a[i-1])) {
//                exch(a, i, i-1);
//                exchanges++;
//            }
//        }
//        if (exchanges == 0) return;
//
//
//        // insertion sort with half-exchanges
//        for (int i = 2; i < n; i++) {
//            Comparable v = a[i];
//            int j = i;
//            while (less(v, a[j-1])) {
//                a[j] = a[j-1];
//                j--;
//            }
//            a[j] = v;
//        }

        //25题不需要交换的插入排序
        int n = a.length;
        for(int i = 0; i < n; i++) {
            Comparable aux = a[i];

            int j;

            for(j = i; j > 0 && less(aux, a[j-1]); j--) {
                a[j] = a[j - 1];
            }

            a[j] = aux;
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
        Comparable[] a = {4,1,0,3,0,2,-2,5,-12,6,8,-2,0,-8};
        sort(a);
        assert isSorted(a);
        show(a);
    }
}
