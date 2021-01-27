package learn.chapter4;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;
import learn.chapter1.Queue;

import java.io.File;

/**
 * 广度优先路径
 */
public class BreadthFirstPaths {
    private boolean[] marked;       //到达该顶点的最短路径已知吗？
    private int[] edgeTo;           //到达该顶点的已知路径上的最后一个顶点
    private int s;                  //起点

    public BreadthFirstPaths(Graph g, int s) {
        marked = new boolean[g.v()];
        edgeTo = new int[g.v()];
        this.s = s;
        bfs(g, s);
    }

    public void bfs(Graph g, int s) {
        Queue<Integer> queue = new Queue<>();
        marked[s] = true;               //标记起点
        queue.enqueue(s);               //将它加入队列
        while (!queue.isEmpty()) {
            int v = queue.dequeue();    //从队列中删去下一顶点
            for (int w : g.adj(v)) {
                if (!marked[w]){        //对于每个未被标记的相邻顶点
                    edgeTo[w] = v;      //保存最短路径的最后一条边
                    marked[w] = true;   //标记它，因为最短路径已知
                    queue.enqueue(w);   //并将它添加到队列中
                }
            }
        }
    }

    public boolean hasPathTo(int v){
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
        BreadthFirstPaths search = new BreadthFirstPaths(g, s);
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
