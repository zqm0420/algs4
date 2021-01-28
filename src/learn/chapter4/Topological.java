package learn.chapter4;


/**
 * 拓扑排序
 */
public class Topological {
    private Iterable<Integer> order;        //顶点的拓扑顺序

    public Topological(Digraph g){
        DirectedCycle cyclefinder = new DirectedCycle(g);
        if (!cyclefinder.hasCycle()){
            DepthFirstOrder dfs = new DepthFirstOrder(g);

            order = dfs.reversePost();
        }
    }

    public Iterable<Integer> order(){
        return order;
    }

    public boolean isDAG(){
        return order!=null;
    }


}
