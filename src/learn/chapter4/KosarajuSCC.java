package learn.chapter4;
/**
 * Kosaraju算法解决了有向图中的强连通性问题
 * 回答了“给定的两个顶点是强连通的吗？这幅有向图中含有多少个强连通分量？”等类似问题。
 */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import learn.chapter1.Bag;

import java.io.File;

public class KosarajuSCC {
    private boolean[] marked;
    private int[] id;
    private int count;

    public KosarajuSCC(Digraph g) {
        marked = new boolean[g.v()];
        id = new int[g.v()];
        DepthFirstOrder order = new DepthFirstOrder(g.reverse());
        for (int s : order.reversePost()) {
            if (!marked[s]) {
                dfs(g, s);
                count++;
            }
        }
    }

    private void dfs(Digraph g, int v) {
        marked[v] = true;
        id[v] = count;
        for (int w : g.adj(v)) {
            if (!marked[w]) {
                dfs(g, w);
            }
        }
    }

    public boolean stronglyConnected(int v, int w) {
        return id[v] == id[w];
    }

    public int count() {
        return count;
    }

    public int id(int v) {
        return id[v];
    }

    public static void main(String[] args) {
        File file = new File("D:\\projects\\java\\learnalgorithm\\algs4\\" +
                "src\\algs4-data\\tinyDG.txt");
        Digraph g = new Digraph(new In(file));
        KosarajuSCC cc = new KosarajuSCC(g);

        int m = cc.count();
        StdOut.println(m + " components");

        Bag<Integer>[] components;
        components = (Bag<Integer>[]) new Bag[m];
        for (int i = 0; i < m; i++) {
            components[i] = new Bag<Integer>();
        }
        for (int v = 0; v < g.v(); v++) {
            components[cc.id(v)].add(v);
        }
        for (int i = 0; i < m; i++) {
            for (int v : components[i]) {
                StdOut.print(v + " ");
            }
            StdOut.println();
        }
    }
}
