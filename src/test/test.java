package test;


import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;

public class test {


    public static void main(String[] args) {
        Queue<Integer> q = new Queue<Integer>();
        for (int i=0;i<3;i++){
            q.enqueue(i);
        }
        Stack<Integer> s = new Stack<Integer>();
        while(!q.isEmpty())
            s.push(q.dequeue());
        while(!s.isEmpty())
            q.enqueue(s.pop());
        for (int i :
                q) {
            System.out.println(i);
        }
    }
}
