package learn.chapter5;

import learn.chapter1.Queue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * 高位优先的字符串排序方法
 */
public class MSD {
    private static final int R = 128;
    private static final int M = 1;
    private static String[] aux;

    private static int charAt(String s, int d) {
        if (d < s.length()) {
            return s.charAt(d);
        } else {
            return -1;
        }
    }

    public static void sort(String[] a) {
        int n = a.length;
        aux = new String[n];
        sort(a, 0, n - 1, 0);
    }

    public static void sort(String[] a, int lo, int hi, int d) {
        if (hi <= lo + M) {
            insertion(a, lo, hi, d);
            return;
        }
        int[] count = new int[R + 2];
        for (int i = lo; i <= hi; i++) {    //计算频率
            count[charAt(a[i], d) + 2]++;
        }
        for (int r = 0; r < R + 1; r++) {   //将频率转换为索引
            count[r + 1] += count[r];
        }
        for (int i = lo; i <= hi; i++) {    //数据分类
            aux[count[charAt(a[i], d)+1]++] = a[i];
        }
        for (int i = lo; i <= hi; i++) {    //回写
            a[i] = aux[i - lo];
        }
        //递归的以每个字符为键进行排序
        for (int r = 0; r < R; r++) {
            sort(a, lo + count[r], lo + count[r + 1] - 1, d + 1);
        }
    }

    // insertion sort a[lo..hi], starting at dth character
    private static void insertion(String[] a, int lo, int hi, int d) {
        for (int i = lo; i <= hi; i++)
            for (int j = i; j > lo && less(a[j], a[j-1], d); j--)
                exch(a, j, j-1);
    }

    // exchange a[i] and a[j]
    private static void exch(String[] a, int i, int j) {
        String temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    // is v less than w, starting at character d
    private static boolean less(String v, String w, int d) {
        // assert v.substring(0, d).equals(w.substring(0, d));
        for (int i = d; i < Math.min(v.length(), w.length()); i++) {
            if (v.charAt(i) < w.charAt(i)) return true;
            if (v.charAt(i) > w.charAt(i)) return false;
        }
        return v.length() < w.length();
    }

    public static void main(String[] args) {
        File file = new File("D:\\projects\\java\\learnalgorithm\\" +
                "algs4\\src\\learn\\chapter5\\testfiles\\file2.txt");
        String line;
        String[] a;
        Queue<String> queue = new Queue<>();
        try(FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr)){
            while ((line=br.readLine())!=null){
                queue.enqueue(line);
            }
        }catch(IOException e){
            e.printStackTrace();
        }
        a = new String[queue.size()];
        for (int i = 0; i < a.length; i++) {
            a[i] = queue.dequeue();
        }
        sort(a);
        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i]);
        }
    }
}
