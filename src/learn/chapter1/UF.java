package learn.chapter1;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class UF {
    private int[] id;   //分量id(以触点作为索引)
    private int count;  //分量数量

    public UF(int num) {    //初始化分量id数组
        count = num;
        id = new int[num];
        for (int i = 0; i < num; i++) {
            id[i] = i;
        }
    }

    public int[] getId() {
        return id;
    }

    public int count() {
        return count;
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    public int find(int p) {
        return id[p];
    }

    public void union(int p, int q) {
        int pID = find(p);
        int qID = find(q);

        if (pID == qID) return;

        for (int i = 0; i < id.length; i++) {
            if (id[i] == qID) id[i] = pID;
        }

        count--;

    }

    public static void main(String[] args) {
        int num = StdIn.readInt();
        UF uf = new UF(num);
        while (!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            if (uf.connected(p, q)) continue;
            uf.union(p, q);
            StdOut.println(p + " " + q);
        }
        StdOut.println(uf.count() + "components");
        int[] id = uf.getId();

        for (int i : id) {
            StdOut.print(id[i] + " ");
        }

    }
}
