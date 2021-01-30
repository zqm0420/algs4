package learn.chapter1;
/**
 * union-find算法的实现（加权quick-union算法）
 */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class WeightedQuickUnionUF {
    private int[] id;   //分量数组
    private int count;  //分量数量
    private int[] sz;   //根节点所对应的分量的大小

    public WeightedQuickUnionUF(int num) {   //初始化分量数组
        count = num;
        id = new int[num];
        for (int i = 0; i < num; i++) {
            id[i] = i;
        }
        for (int i = 0; i < num; i++) {
            sz[i] = 1;
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

        //将小树的根节点连接到大树的根节点
        if (sz[pRoot] < sz[qRoot]) {
            id[pRoot] = qRoot;
            sz[qRoot] += sz[pRoot];
        }else{
            id[qRoot] = pRoot;
            sz[pRoot] += sz[qRoot];
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
