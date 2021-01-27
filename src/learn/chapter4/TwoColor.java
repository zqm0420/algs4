package learn.chapter4;

/**
 * 图是二分图吗？（双色问题）
 */
public class TwoColor {
    private boolean[] marked;
    private boolean[] color;
    private boolean isTwoColorable = true;

    public TwoColor(Graph g) {
        marked = new boolean[g.v()];
        color = new boolean[g.v()];
        for (int s = 0;s<g.v();s++){
            if (!marked[s]){
                dfs(g, s);
            }
        }
    }

    private void dfs(Graph g, int v){
        marked[v] = true;
        for (int w:g.adj(v)){
            if (!marked[w]){
                color[w] = !color[v];
            }else if(color[w]==color[v]){
                isTwoColorable = false;
            }
        }
    }

    public boolean isBipartite(){
        return isTwoColorable;
    }
}
