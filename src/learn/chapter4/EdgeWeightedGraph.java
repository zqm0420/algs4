package learn.chapter4;
/**
 * 加权无向图的API
 */

import edu.princeton.cs.algs4.In;
import learn.chapter1.Bag;

public class EdgeWeightedGraph {
    private final int v;
    private int e;
    private Bag<Edge>[] adj;

    public EdgeWeightedGraph(int v) {
        this.v = v;
        this.e = 0;
        adj = (Bag<Edge>[]) new Bag[v];
        for (int i = 0; i < v; i++) {
            adj[i] = new Bag<Edge>();
        }
    }

    public EdgeWeightedGraph(In in){
        this(in.readInt());
        int e = in.readInt();
        for (int i = 0; i < e; i++) {
            int v = in.readInt();
            int w = in.readInt();
            double weight = in.readDouble();
            Edge edge = new Edge(v, w, weight);
            addEdge(edge);
        }
    }

    public int v() {
        return this.v;
    }

    public int e() {
        return this.e;
    }

    public void addEdge(Edge e) {
        int v = e.either();
        int w = e.other(v);
        adj[v].add(e);
        adj[w].add(e);
        this.e++;
    }

    public Iterable<Edge> adj(int v) {
        return adj[v];
    }

    public Iterable<Edge> edges(){
        Bag<Edge> b = new Bag<Edge>();
        for (int v = 0; v < this.v; v++) {
            for (Edge e:adj[v]){
                if (e.other(v)>v){
                    b.add(e);
                }
            }
        }
        return b;
    }
}
