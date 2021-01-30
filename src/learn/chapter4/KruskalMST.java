package learn.chapter4;
/**
 * 最小生成树的Kruskal算法
 * 空间成本最多为E，时间成本最多为2lgE
 * 此算法一般比Prim算法慢，因为在处理每条边时除了两种算法都要完成的优先队列操作之外，
 * 它还需要进行一次connect()操作
 */

import edu.princeton.cs.algs4.In;
import learn.chapter1.Queue;
import learn.chapter1.UF;
import learn.chapter2.MinPQ;

public class KruskalMST {
    private Queue<Edge> mst;

    public KruskalMST(EdgeWeightedGraph g) {
        mst = new Queue<>();
        MinPQ<Edge> pq = new MinPQ<>();
        for (Edge e : g.edges()) {
            pq.insert(e);
        }
        UF uf = new UF(g.v());

        while (!pq.isEmpty() && mst.size() < g.v() - 1) {
            Edge e = pq.delMin();
            int v = e.either();
            int w = e.other(v);
            if (uf.connected(v, w)) {
                continue;
            }
            uf.union(v, w);
            mst.enqueue(e);
        }
    }

    public Iterable<Edge> edges() {
        return mst;
    }

    public double weight(){
        double count = 0.0;
        for (Edge e:mst){
            count+=e.weight();
        }
        return count;
    }

    public static void main(String[] args) {
        String filename = "D:\\projects\\java\\learnalgorithm\\algs4\\" +
                "src\\algs4-data\\tinyEWG.txt";
        In in = new In(filename);
        EdgeWeightedGraph g;
        g = new EdgeWeightedGraph(in);

        KruskalMST mst = new KruskalMST(g);
    }
}
