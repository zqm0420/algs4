package learn.chapter4;
/**
 * 有向图中基于深度优先搜索的顶点排序
 * 该类允许用例用各种顺序遍历深度优先搜索经过的所有顶点。
 * 这在高级的有向图处理算法中非常有用。
 */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;
import learn.chapter1.Queue;

import java.io.File;

public class DepthFirstOrder {
    private boolean[] marked;
    private Queue<Integer> pre;             //所有顶点的前序排列
    private Queue<Integer> post;            //所有顶点的后序排列
    private Stack<Integer> reversePost;     //所有顶点的逆后序排列

    public DepthFirstOrder(Digraph g) {
        pre = new Queue<>();
        post = new Queue<>();
        reversePost = new Stack<>();
        marked = new boolean[g.v()];

        for (int v = 0; v < g.v(); v++) {
            if (!marked[v]) {
                dfs(g, v);
            }
        }
    }

    public DepthFirstOrder(EdgeWeightedDigraph g){
        pre = new Queue<>();
        post = new Queue<>();
        reversePost = new Stack<>();
        marked = new boolean[g.v()];

        for (int v = 0; v < g.v(); v++) {
            if (!marked[v]) {
                dfs(g, v);
            }
        }
    }

    private void dfs(Digraph g, int v) {
        pre.enqueue(v);

        marked[v] = true;
        for (int w : g.adj(v)) {
            if (!marked[w]) {
                dfs(g, w);
            }
        }
        post.enqueue(v);
        reversePost.push(v);
    }

    private void dfs(EdgeWeightedDigraph g, int v) {
        pre.enqueue(v);

        marked[v] = true;
        for (DirectedEdge e : g.adj(v)) {
            int w = e.to();
            if (!marked[w]) {
                dfs(g, w);
            }
        }
        post.enqueue(v);
        reversePost.push(v);
    }

    public Iterable<Integer> pre() {
        return pre;
    }

    public Iterable<Integer> post() {
        return post;
    }

    public Iterable<Integer> reversePost() {
        return reversePost;
    }

    public static void main(String[] args) {
        File file = new File("D:\\projects\\java\\learnalgorithm\\algs4\\" +
                "src\\algs4-data\\tinyDG.txt");
        Digraph g = new Digraph(new In(file));
        DepthFirstOrder dfo = new DepthFirstOrder(g);
        System.out.println(dfo.pre());
    }
}
