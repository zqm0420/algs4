package learn;


import edu.princeton.cs.algs4.StdIn;
import learn.chapter1.Transaction;
import learn.chapter2.MinPQ;

import java.util.Stack;

public class Test {
    public static void main(String[] args)
    {   //  打印输入流中最大的M行
        int M = Integer.parseInt(args[0]);
        MinPQ<Transaction> pq = new MinPQ<Transaction>(M+1);
        while (StdIn.hasNextLine())
        {
            pq.insert(new Transaction(StdIn.readLine()));
            if (pq.size()>M)
            {
                pq.delMin();    //如果优先队列中存在M+1个元素则删除其中最小的元素
            }
        }
        Stack<Transaction> stack = new Stack<>();
        while (!pq.isEmpty())
        {
            stack.push(pq.delMin());
        }
        for (Transaction transaction : stack) {
            System.out.println(transaction);
        }
    }
}
