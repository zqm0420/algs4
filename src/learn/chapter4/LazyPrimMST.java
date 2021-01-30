package learn.chapter4;
/**
 * 最小生成树的Prim算法的延时实现
 * 图的生成树是它的一棵含有其所有顶点的无环连接子图。
 * 最小生成树（MST）是一幅加权图的一棵权值（树中所有边的权值之和）最小的生成树。
 * 所需空间与E成正比，所需时间与ElogE成正比
 */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import learn.chapter1.Queue;
import learn.chapter2.MinPQ;

public class LazyPrimMST {
    private boolean[] marked;
    private Queue<Edge> mst;
    private MinPQ<Edge> pq;

    public LazyPrimMST(EdgeWeightedGraph g) {
        pq = new MinPQ<>();
        marked = new boolean[g.v()];
        mst = new Queue<>();

        visit(g, 0);
        while (!pq.isEmpty()) {
            Edge e = pq.delMin();

            int v = e.either();
            int w = e.other(v);
            if (marked[v] && marked[w]) {
                continue;
            }
            mst.enqueue(e);
            if (!marked[v]) {
                visit(g, v);
            }
            if (!marked[w]) {
                visit(g, w);
            }
        }
    }

    private void visit(EdgeWeightedGraph g, int v) {
        marked[v] = true;
        for (Edge e : g.adj(v)) {
            if (!marked[e.other(v)]) {
                pq.insert(e);
            }
        }
    }

    public Iterable<Edge> edges() {
        return mst;
    }

    public double weight() {
        double count = 0.0;
        for (Edge e : mst) {
            count += e.weight();
        }
        return count;
    }

    public static void main(String[] args) {
        String filename = "D:\\projects\\java\\learnalgorithm\\algs4\\" +
                "src\\algs4-data\\tinyEWG.txt";
        In in = new In(filename);
        EdgeWeightedGraph g;
        g = new EdgeWeightedGraph(in);

        LazyPrimMST mst = new LazyPrimMST(g);
        for (Edge e : mst.edges()) {
            StdOut.println(e);
        }
        StdOut.println(mst.weight());
    }
}
