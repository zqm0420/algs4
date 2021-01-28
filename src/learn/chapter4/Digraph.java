package learn.chapter4;

import edu.princeton.cs.algs4.In;
import learn.chapter1.Bag;

/**
 * 有向图的基本实现
 * 有向图的构造和无向图基本一致，差别就在于addEdge方法只调用了一次add()。
 */
public class Digraph {
    private final int v;  //顶点数
    private int e;  //边数
    private Bag<Integer>[] adj; //邻接表

    public Digraph(int v) {
        this.v = v;
        this.e = 0;
        adj = (Bag<Integer>[]) new Bag[v];
        for (int i = 0; i < v; i++) {
            adj[i] = new Bag<Integer>();
        }
    }

    public Digraph(In in) {
        this(in.readInt());
        int e = in.readInt();
        for (int i = 0; i < e; i++) {
            int v = in.readInt();
            int w = in.readInt();
            addEdge(v, w);
        }
    }

    public int v() {
        return this.v;
    }

    public int e() {
        return this.e;
    }

    public void addEdge(int v, int w) {
        adj[v].add(w);
        this.e++;
    }

    public Iterable<Integer> adj(int v) {
        return adj[v];
    }

    public Digraph reverse(){
        Digraph r = new Digraph(v);
        for (int v = 0; v < this.v; v++) {
            for (int w:adj(v)){
                r.addEdge(w, v);
            }
        }
        return r;
    }
}
