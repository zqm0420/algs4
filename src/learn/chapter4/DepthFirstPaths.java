package learn.chapter4;
/**
 * 深度优先路径
 */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

import java.io.File;

public class DepthFirstPaths {
    private boolean[] marked;
    private final int s;  //起点
    private int[] edgeTo;

    public DepthFirstPaths(Graph g, int s) {
        marked = new boolean[g.v()];
        edgeTo = new int[g.v()];
        this.s = s;
        dfs(g, s);
    }

    private void dfs(Graph g, int v) {
        marked[v] = true;
        for (int w : g.adj(v)) {
            if (!marked[w]) {
                edgeTo[w] = v;
                dfs(g, w);
            }
        }
    }

    public boolean hasPathTo(int v) {
        return marked[v];
    }

    public Iterable<Integer> pathTo(int v) {
        if (!hasPathTo(v)) {
            return null;
        }
        Stack<Integer> path = new Stack<>();
        for (int x = v; x != s; x = edgeTo[x]) {
            path.push(x);
        }
        path.push(s);
        return path;
    }

    public static void main(String[] args) {
        File file = new File("D:\\projects\\java\\learnalgorithm\\algs4\\" +
                "src\\algs4-data\\tinyCG.txt");
        Graph g = new Graph(new In(file));
        int s = Integer.parseInt("0");
        DepthFirstPaths search = new DepthFirstPaths(g, s);
        for (int v = 0; v < g.v(); v++) {
            StdOut.print(s + " to " + v + ": ");
            if (search.hasPathTo(v)) {
                for (int x : search.pathTo(v)) {
                    if (x == s) {
                        StdOut.print(x);
                    } else {
                        StdOut.print("-" + x);
                    }
                }
            }
            System.out.println();
        }
    }
}
