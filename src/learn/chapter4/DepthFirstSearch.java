package learn.chapter4;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.io.File;

public class DepthFirstSearch {
    private boolean[] marked;
    private int count;

    /**
     * 找出和起点s连通的所有顶点
     */
    public DepthFirstSearch(Graph g, int s) {
        marked = new boolean[g.v()];
        dfs(g, s);
    }

    private void dfs(Graph g, int v) {
        marked[v] = true;
        count++;
        for (int w : g.adj(v)) {
            if (!marked(w)) dfs(g, w);
        }
    }

    /**
     * 判断v和s是连通的吗？
     *
     * @param v
     * @return
     */
    public boolean marked(int v) {
        return marked[v];
    }

    /**
     * 与s连通的顶点总数
     *
     * @return
     */
    public int count() {
        return count;
    }

    public static void main(String[] args) {
        File file = new File("D:\\projects\\java\\learnalgorithm\\algs4\\" +
                "src\\algs4-data\\tinyCG.txt");
        Graph g = new Graph(new In(file));
        int s = Integer.parseInt("0");
        DepthFirstSearch depthFirstSearch = new DepthFirstSearch(g, s);

        for (int v = 0; v < g.v(); v++) {
            if (depthFirstSearch.marked(v)) {
                StdOut.print(v + " ");
            }
        }
        StdOut.println();

        if (depthFirstSearch.count() != g.v()) {
            System.out.print("NOT ");
        }
        System.out.println("connected");
    }
}
