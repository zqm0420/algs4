package learn.chapter4;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;

import java.io.File;

/**
 * 有向环的API
 */
public class DirectedCycle {
    private boolean[] marked;
    private int[] edgeTo;
    private Stack<Integer> cycle;
    private boolean[] onStack;

    public DirectedCycle(Digraph g){
        onStack = new boolean[g.v()];
        marked = new boolean[g.v()];
        edgeTo = new int[g.v()];
        for (int v = 0; v < g.v(); v++) {
            if (!marked[v]){
                dfs(g, v);
            }
        }
    }

    private void dfs(Digraph g, int v){
        onStack[v] = true;
        marked[v] = true;
        for (int w:g.adj(v)){
            if (this.hasCycle()){
                return;
            }else if(!marked[w]){
                edgeTo[w] = v;
                dfs(g, w);
            }else if(onStack[w]){
                cycle = new Stack<Integer>();
                for (int x = v; x!=w; x=edgeTo[x]) {
                    cycle.push(x);
                }
                cycle.push(w);
                cycle.push(v);
            }
        }
        onStack[v] = false;
    }

    public boolean hasCycle(){
        return cycle!=null;
    }

    public Iterable<Integer> cycle(){
        return cycle;
    }

    public static void main(String[] args) {
        File file = new File("D:\\projects\\java\\learnalgorithm\\algs4\\" +
                "src\\algs4-data\\tinyDG.txt");
        Digraph g = new Digraph(new In(file));
        DirectedCycle dc = new DirectedCycle(g);
        for (int i:dc.cycle()){
            System.out.println(i);
        }
    }
}
