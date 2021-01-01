package learn.chapter1;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class QuickUnionUF {
    private int[] id;   //分量数组
    private int count;  //分量数量

    public QuickUnionUF(int num) {   //初始化分量数组
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
        while (id[p] != p) p = id[p];
        return p;
    }

    public void union(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);

        if (pRoot == qRoot) return;

        id[qRoot] = pRoot;

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
