package learn.chapter4;

import edu.princeton.cs.algs4.In;
import learn.chapter1.Bag;

public class Graph {
    private int v;  //顶点数
    private int e;  //边数
    private Bag<Integer>[] adj; //邻接表

    public Graph(int v) {
        this.v = v;
        e = 0;
        adj = new Bag[v];
        for (int i = 0; i < v; i++) {
            adj[i] = new Bag<Integer>();
        }
    }

    public Graph(In in){
        this(in.readInt());
        e = in.readInt();
        for (int i = 0; i < this.e; i++) {
            int v = in.readInt();
            int w = in.readInt();
            addEdge(v, w);
        }
    }

    public int v(){
        return this.v;
    }

    public int e(){
        return this.e;
    }

    public void addEdge(int v, int w){
        adj[v].add(w);
        adj[w].add(v);
        this.e++;
    }

    public Iterable<Integer> adj(int v){
        return (Iterable<Integer>) adj[v].iterator();
    }


}
