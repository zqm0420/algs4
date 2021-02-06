package learn.chapter4;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

import java.io.File;

/**
 * 无环有向加权图的最长路径算法
 * 简单修改最短路径算法就可以实现最长路径算法：
 * 1. 将distTo[]的初始值变为Double.NEGATIVE_INFINITY
 * 2. 将relax()方法中的不等式的方向改变。
 */
public class AcyclicLP {
    private DirectedEdge[] edgeTo;
    private double[] distTo;

    public AcyclicLP(EdgeWeightedDigraph g, int s) {
        edgeTo = new DirectedEdge[g.v()];
        distTo = new double[g.v()];

        for (int v = 0; v < g.v(); v++) {
            distTo[v] = Double.NEGATIVE_INFINITY;   //初始化为负无穷
        }
        distTo[s] = 0.0;

        Topological top = new Topological(g);
        for (int v : top.order()) {
            relax(g, v);
        }
    }

    private void relax(EdgeWeightedDigraph g, int v) {
        for (DirectedEdge e : g.adj(v)) {
            int w = e.to();
            if (distTo[w] < distTo[v] + e.weight()) {   //改变此不等式的方向
                distTo[w] = distTo[v] + e.weight();
                edgeTo[w] = e;
            }
        }
    }

    public double distTo(int v) {
        return distTo[v];
    }

    public boolean hasPathTo(int v) {
        return distTo[v] < Double.POSITIVE_INFINITY;
    }

    public Iterable<DirectedEdge> pathTo(int v) {
        if (!hasPathTo(v)) {
            return null;
        }
        Stack<DirectedEdge> path = new Stack<>();
        for (DirectedEdge e = edgeTo[v]; e != null; e = edgeTo[e.from()]) {
            path.push(e);
        }
        return path;
    }

    public static void main(String[] args) {
        EdgeWeightedDigraph g;
        File file = new File("D:\\projects\\java\\learnalgorithm\\algs4\\" +
                "src\\algs4-data\\tinyEWDAG.txt");
        g = new EdgeWeightedDigraph(new In(file));
        int s = Integer.parseInt("5");
        AcyclicLP sp = new AcyclicLP(g, s);

        for (int t = 0; t < g.v(); t++) {
            StdOut.print(s + " to " + t);
            StdOut.printf("(%4.2f):", sp.distTo(t));
            if (sp.hasPathTo(t)) {
                for (DirectedEdge e : sp.pathTo(t)) {
                    StdOut.print(e + "   ");
                }
            }
            StdOut.println();
        }
    }
}
