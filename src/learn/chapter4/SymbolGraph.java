package learn.chapter4;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;


/**
 * 符号图
 * 允许用例用字符串代替数字索引来表示图中的顶点。
 */
public class SymbolGraph {
    private ST<String, Integer> st;     //符号名 -> 索引
    private String[] keys;              //索引 -> 符号名
    private Graph g;                    //图

    public SymbolGraph(String stream, String sp) {
        st = new ST<String, Integer>();
        In in = new In(stream);                     //第一遍
        while (in.hasNextLine()) {                   //构造索引
            String[] a = in.readLine().split(sp);   //读取字符串

            for (int i = 0; i < a.length; i++) {    //为每个不同的字符串关联一个索引
                if (!st.contains(a[i])) {
                    st.put(a[i], st.size());
                }
            }
        }
        keys = new String[st.size()];               //用来获得顶点名的反向索引是一个数组

        for (String name : st.keys()) {
            keys[st.get(name)] = name;
        }

        g = new Graph(st.size());
        in = new In(stream);                        //第二遍
        while (in.hasNextLine()) {                   //构造图
            String[] a = in.readLine().split(sp);   //将每一行的第一个顶点和该行的其他顶点相连
            int v = st.get(a[0]);
            for (int i = 1; i < a.length; i++) {
                g.addEdge(v, st.get(a[i]));
            }
        }
    }

    public boolean contains(String s) {
        return this.st.contains(s);
    }

    public int index(String key) {
        return this.st.get(key);
    }

    public String name(int v) {
        return this.keys[v];
    }

    public Graph graph() {
        return this.g;
    }

    public static void main(String[] args) {
        String filename = args[0];
        String delim = args[1];
        SymbolGraph sg = new SymbolGraph(filename, delim);

        Graph g = sg.graph();

        while (StdIn.hasNextLine()) {
            String source = StdIn.readLine();
            for (int w : g.adj(sg.index(source))) {
                StdOut.println("  " + sg.name(w));
            }
        }
    }
}
