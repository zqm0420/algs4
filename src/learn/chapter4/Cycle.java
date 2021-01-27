package learn.chapter4;

/**
 * G是无环图吗？
 */
public class Cycle {
    private boolean[] marked;
    private boolean hasCycle;

    public Cycle(Graph g) {
        marked = new boolean[g.v()];
        for (int s = 0; s < g.v(); s++) {
            if (!marked[s]) {
                dfs(g, s, s);
            }
        }
    }

    private void dfs(Graph g, int v, int u) {
        marked[v] = true;
        for (int w : g.adj(v)) {
            if (!marked[w]){
                dfs(g, w, v);
            }else{
                if (w!=u) hasCycle=true;
            }
        }
    }
}
