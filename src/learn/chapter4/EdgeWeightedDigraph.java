package learn.chapter4;

import edu.princeton.cs.algs4.In;
import learn.chapter1.Bag;

public class EdgeWeightedDigraph {
    private final int v;
    private int e;
    private Bag<DirectedEdge>[] adj;

    public EdgeWeightedDigraph(int v) {
        this.v = v;
        this.e = 0;
        adj = (Bag<DirectedEdge>[]) new Bag[v];
        for (int i = 0; i < v; i++) {
            adj[i] = new Bag<>();
        }
    }

    public EdgeWeightedDigraph(In in){
        this(in.readInt());
        int e = in.readInt();
        for (int i = 0; i < e; i++) {
            int v = in.readInt();
            int w = in.readInt();
            double weight = in.readDouble();
            DirectedEdge edge = new DirectedEdge(v, w, weight);
            addEdge(edge);
        }
    }

    public int v() {
        return this.v;
    }

    public int e() {
        return this.e;
    }

    public void addEdge(DirectedEdge e) {
        adj[e.from()].add(e);
        this.e++;
    }

    public Iterable<DirectedEdge> adj(int v) {
        return adj[v];
    }

    public Iterable<DirectedEdge> edges(){
        Bag<DirectedEdge> b = new Bag<>();
        for (int v = 0; v < this.v; v++) {
            for (DirectedEdge e:adj[v]){
                b.add(e);
            }
        }
        return b;
    }
}
