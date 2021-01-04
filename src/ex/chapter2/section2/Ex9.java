package ex.chapter2.section2;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.io.*;

/**
 * 2.2.9 在库函数中使用 aux []这样的静态数组是不妥当的,因为可能会有多个程序同时使用这个类,
 * 实现一个不用静态数组的 Merge类,但也不要将 aux[] 变为 merge ()的局部变量(请见本节的答疑部分)。
 * 提示:可以将辅助数组作为参数传递给递归的 sort () 方法。
 */

public class Ex9 {

    private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
        int i = lo;
        int j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
        }
        for (int k = lo; k <= hi; k++) {
            if (i > mid) a[k] = aux[j++];
            else if (j > hi) a[k] = aux[i++];
            else if (less(aux[j], aux[i])) a[k] = aux[j++];
            else a[k] = aux[i++];
        }
    }

    private static void sort(Comparable[] a, Comparable[] aux, int lo, int hi) {
        if (hi <= lo) return;
        int mid = lo + (hi - lo) / 2;
        sort(a, aux, lo, mid);
        sort(a, aux, mid + 1, hi);
        merge(a, aux, lo, mid, hi);
    }

    public static void sort(Comparable[] a) {
        Comparable[] aux = new Comparable[a.length];
        sort(a, aux, 0, a.length - 1);
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
//        String[] a = In.readStrings();
//        show(a);
//        sort(a);
//        assert isSorted(a);
//        show(a);
        String path = "E:\\projects\\Java\\learnalgorithms\\algs4\\src\\algs4-data";
        String filename = "tiny.txt";
        File file = new File(path, filename);
        String line = null;
        String[] a = new String[11];
        try(BufferedReader br = new BufferedReader(new FileReader(file))) {
            while ((line = br.readLine())!=null){
                a = line.split(" ");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        show(a);
        sort(a);
        assert isSorted(a);
        show(a);
    }
}
