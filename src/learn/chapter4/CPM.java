package learn.chapter4;

import edu.princeton.cs.algs4.StdOut;

import java.io.*;

/**
 * 优先级限制下的并行任务调度问题的关键路径方法
 */
public class CPM {
    public static void main(String[] args) {
        File file = new File("D:\\projects\\java\\learnalgorithm" +
                "\\algs4\\src\\algs4-data\\jobsPC.txt");
        try (FileReader fr = new FileReader(file);
             BufferedReader br = new BufferedReader(fr)) {
            int n = Integer.parseInt(br.readLine());
            EdgeWeightedDigraph g;
            g = new EdgeWeightedDigraph(2 * n + 2);
            int s = 2 * n;
            int t = 2 * n + 1;
            for (int i = 0; i < n; i++) {
                String[] a = br.readLine().split("\\s+");
                double duration = Double.parseDouble(a[0]);
                g.addEdge(new DirectedEdge(s, i, 0.0));
                g.addEdge(new DirectedEdge(i, i + n, duration));
                g.addEdge(new DirectedEdge(i + n, t, 0.0));

                int m = Integer.parseInt(a[1]);
                for (int j = 0; j < m; j++) {
                    int successor = Integer.parseInt(a[2 + j]);
                    g.addEdge(new DirectedEdge(i + n, successor, 0.0));
                }
            }

            AcyclicLP lp = new AcyclicLP(g, s);

            System.out.println("Start times:");
            for (int i = 0; i < n; i++) {
                StdOut.printf("%4d: %5.1f\n", i, lp.distTo(i));
            }
            System.out.printf("Finish time: %5.1f\n", lp.distTo(t));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
