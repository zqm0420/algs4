package learn.chapter1;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class QuickFindUF {
    private int[] id;   //分量数组
    private int count;  //分量数量

    public QuickFindUF(int num) {   //初始化分量数组
        count = num;
        id = new int[num];
        for (int i = 0; i < num; i++) {
            id[i] = i;
        }
    }

    public int count() {
        return count;
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    public int find(int p) {
        return id[p];
    }

    public void union(int p, int q) {
        int pID = find(p);
        int qID = find(q);

        if (connected(p, q)) return;

        for (int i = 0; i < id.length; i++) {
            if (id[i] == qID) id[i] = pID;
        }

        count--;
    }

    public static void main(String[] args) {
        int num = StdIn.readInt();
        QuickFindUF uf = new QuickFindUF(num);
        long start = System.currentTimeMillis();
        while (!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            if (uf.connected(p, q)) continue;
            uf.union(p, q);
            StdOut.println(p + " " + q);
        }
        long end = System.currentTimeMillis();
        StdOut.print((end - start) + "ms");
        StdOut.println("count: " + uf.count());
    }
}
