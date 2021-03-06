package learn.chapter4;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;
import learn.chapter1.Bag;

public class Graph {
    private final int v;  //顶点数
    private int e;  //边数
    private Bag<Integer>[] adj; //邻接表

    public Graph(int v) {
        this.v = v;
        this.e = 0;
        adj = (Bag<Integer>[]) new Bag[v];
        for (int i = 0; i < v; i++) {
            adj[i] = new Bag<Integer>();
        }
    }

    public Graph(In in) {
        this(in.readInt());
        int e = in.readInt();
        for (int i = 0; i < e; i++) {
            int v = in.readInt();
            int w = in.readInt();
            addEdge(v, w);
        }
    }

    /**
     * 练习4.1.3：
     * 为Graph添加一个复制构造函数，它接受一副图G然后创建并初始化这幅图的一个副本。
     * G的用例对它作出的任何改动都不应该影响到它的副本。
     *
     * @param g
     */
    public Graph(Graph g) {
        this(g.v());
        this.e = g.e();
        for (int v = 0; v < g.v(); v++) {
            Stack<Integer> stack = new Stack<>();
            for (int w : g.adj(v)) {
                stack.push(w);
            }
            for (int w : stack) {
                adj[v].add(w);
            }
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
        adj[w].add(v);
        this.e++;
    }

    public Iterable<Integer> adj(int v) {
        return adj[v];
    }


}
