package learn.chapter4;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import learn.chapter1.Bag;

import java.io.File;

/**
 * 找出一幅图的所有连通分量
 */
public class CC {
    private boolean[] marked;
    private int[] id;
    private int count;

    public CC(Graph g) {
        marked = new boolean[g.v()];
        id = new int[g.v()];
        for (int s = 0; s < g.v(); s++) {
            if (!marked[s]) {
                dfs(g, s);
                count++;
            }
        }
    }

    private void dfs(Graph g, int v) {
        marked[v] = true;
        id[v] = count;
        for (int w : g.adj(v)) {
            if (!marked[w]) {
                dfs(g, w);
            }
        }
    }

    public boolean connected(int v, int w) {
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
                "src\\algs4-data\\tinyG.txt");
        Graph g = new Graph(new In(file));
        CC cc = new CC(g);

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
