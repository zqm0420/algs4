package learn.chapter4;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * 使用了SymbolGraph和BreadthFirstPaths来查找图中的最短路径
 */
public class DegreesOfSeparation {
    public static void main(String[] args) {
        SymbolGraph sg = new SymbolGraph(args[0], args[1]);

        Graph g = sg.graph();

        String source = args[2];
        if (!sg.contains(source)){
            StdOut.println(source+"not in database.");
            return;
        }
        int s = sg.index(source);
        BreadthFirstPaths bfs = new BreadthFirstPaths(g, s);

        while (!StdIn.isEmpty()){
            String sink = StdIn.readLine();
            if (sg.contains(sink)){
                int t = sg.index(sink);
                if (bfs.hasPathTo(t)){
                    for (int v:bfs.pathTo(t)){
                        StdOut.println("  "+sg.name(v));
                    }
                }else{
                    StdOut.println("Not connected");
                }
            }else{
                StdOut.println("Not in databases;");
            }
        }
    }
}
