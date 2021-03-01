package learn.chapter5;

import learn.chapter1.Queue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Quick3String {
    private static int charAt(String s, int d){
        if (d<s.length()){
            return s.charAt(d);
        }else{
            return -1;
        }
    }

    public static void sort(String[] a){
        sort(a, 0, a.length-1, 0);
    }

    private static void sort(String[] a, int lo, int hi, int d){
        if (hi<=lo){
            return;
        }
        int lt = lo;
        int gt = hi;
        int v = charAt(a[lo], d);
        int i = lo+1;
        while (i <= gt) {
            int t = charAt(a[i], d);
            if (t<v){
                exch(a, lt++,i++);
            }else if(t>v){
                exch(a, i, gt--);
            }else{
                i++;
            }
        }
        sort(a, lo, lt-1, d);
        if (v>=0){
            sort(a, lt, gt, d+1);
        }
        sort(a, gt+1, hi, d);
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
