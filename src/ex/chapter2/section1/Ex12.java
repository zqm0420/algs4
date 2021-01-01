package ex.chapter2.section1;

/**
 * 令希尔排序打印出递增序列的每个元素所带来的比较次数和数组大小的比值。
 * 编写一个测试用例对随机Double数组进行希尔排序，验证该值是一个小常数，数组大小按照10的幂次递增，不小于100
 */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Ex12 {
    private static int compareTimes;

    public static void shellSort(Comparable[] a) {
        //将a[]按升序排列
        int N = a.length;
        int h = 1;
        int arrSize = 0;
        while (h < N / 3) h = 3 * h + 1;
        while (h >= 1) {    //将数组变为h有序
            //求对应h的数组大小
            if (N / h == 0) {
                arrSize = N / h;
            } else {
                arrSize = N / h + 1;
            }
            for (int i = h; i < N; i++) {
                //将a[i]插入到a[i-h]、a[i-2*h]、a[i-3*h]...之中
                for (int j = i; j >= h && less(a[j], a[j - h]); j -= h) {
                    exch(a, j, j - h);
                }
            }
            System.out.println("h为"+h+"时，比较次数和数组大小的比值为："+compareTimes/arrSize);
            compareTimes = 0;
            h = h / 3;
        }
    }

    private static boolean less(Comparable v, Comparable w) {    //比较元素的大小
        compareTimes++;
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
        Double[] a = new Double[100000];
        for (int i = 0; i < a.length; i++) {
            a[i] = StdRandom.uniform();
        }
        shellSort(a);
        assert isSorted(a);
//        show(a);
    }
}
