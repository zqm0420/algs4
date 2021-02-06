package learn.chapter4;
/**
 * 基于队列的Bellman-Ford算法
 */
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;
import learn.chapter1.Queue;

import java.io.File;

public class BellmanFordSP {
    private double[] distTo;
    private DirectedEdge[] edgeTo;
    private boolean[] onQ;
    private Queue<Integer> queue;
    private int cost;
    private Iterable<DirectedEdge> cycle;

    public BellmanFordSP(EdgeWeightedDigraph g, int s){
        distTo = new double[g.v()];
        edgeTo = new DirectedEdge[g.v()];
        onQ = new boolean[g.v()];
        queue = new Queue<>();
        for (int v = 0; v < g.v(); v++) {
            distTo[v] = Double.POSITIVE_INFINITY;
        }
        distTo[s] = 0.0;
        queue.enqueue(s);
        onQ[s] = true;
        while (!queue.isEmpty() && !hasNegativeCycle()){
            int v = queue.dequeue();
            onQ[v] = false;
            relax(g, v);
        }
    }

    private void relax(EdgeWeightedDigraph g, int v){
        for (DirectedEdge e:g.adj(v)){
            int w = e.to();
            if (distTo[w]>distTo[v]+e.weight()){
                distTo[w] = distTo[v]+e.weight();
                edgeTo[w] = e;
                if (!onQ[w]){
                    queue.enqueue(w);
                    onQ[w] = true;
                }
            }
        }
        if (cost++ % g.v()==0){
            findNegativeCycle();
        }
    }

    public double distTo(int v){
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

    private void findNegativeCycle(){
        int v = edgeTo.length;
        EdgeWeightedDigraph spt= new EdgeWeightedDigraph(v);
        for (int i= 0; i < v; i++) {
            if (edgeTo[i]!=null){
                spt.addEdge(edgeTo[i]);
            }
        }

        EdgeWeightedDirectedCycle finder = new EdgeWeightedDirectedCycle(spt);
        cycle = finder.cycle();
    }

    private boolean hasNegativeCycle(){
        return cycle!=null;
    }

    public Iterable<DirectedEdge> negativeCycle(){
        return cycle;
    }

    public static void main(String[] args) {
        EdgeWeightedDigraph g;
        File file = new File("D:\\projects\\java\\learnalgorithm\\algs4\\" +
                "src\\algs4-data\\tinyEWDnc.txt");
        g = new EdgeWeightedDigraph(new In(file));
        int s = Integer.parseInt("0");
        BellmanFordSP sp = new BellmanFordSP(g, s);

//        for (int t = 0; t < g.v(); t++) {
//            StdOut.print(s + " to " + t);
//            StdOut.printf("(%4.2f):", sp.distTo(t));
//            if (sp.hasPathTo(t)) {
//                for (DirectedEdge e : sp.pathTo(t)) {
//                    StdOut.print(e + "   ");
//                }
//            }
//            StdOut.println();
//        }
        for (DirectedEdge e:sp.negativeCycle()){
            System.out.println(e);
        }
    }
}
