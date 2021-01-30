package learn.chapter4;
/**
 * 最小生成树的Prim算法的即时版本
 * 所需空间与V成正比，所需时间与ElogV成正比
 */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.IndexMinPQ;

public class PrimMST {
    private Edge[] edgeTo;
    private double[] distTo;
    private boolean[] marked;
    private IndexMinPQ<Double> pq;

    public PrimMST(EdgeWeightedGraph g){
        edgeTo = new Edge[g.v()];
        distTo = new double[g.v()];
        marked = new boolean[g.v()];
        for (int v = 0; v < g.v(); v++) {
            distTo[v] = Double.POSITIVE_INFINITY;
        }
        pq = new IndexMinPQ<>(g.v());

        distTo[0] = 0.0;
        pq.insert(0, 0.0);
        while (!pq.isEmpty()){
            visit(g, pq.delMin());
        }
    }

    private void visit(EdgeWeightedGraph g, int v){
        marked[v] = true;
        for (Edge e:g.adj(v)){
            int w = e.other(v);

            if (marked[w]){
                continue;
            }
            if (e.weight()<distTo[w]){
                edgeTo[w] = e;

                distTo[w] = e.weight();
                if (pq.contains(w)){
                    pq.changeKey(w, distTo[w]);
                }else{
                    pq.insert(w, distTo[w]);
                }
            }
        }
    }

    public static void main(String[] args) {
        String filename = "D:\\projects\\java\\learnalgorithm\\algs4\\" +
                "src\\algs4-data\\tinyEWG.txt";
        In in = new In(filename);
        EdgeWeightedGraph g;
        g = new EdgeWeightedGraph(in);

        PrimMST mst = new PrimMST(g);
    }
}
