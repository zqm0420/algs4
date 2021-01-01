package test;

import java.util.Iterator;

/**
 * 我的队列(链表实现)
 * @author zqm
 */
public class MyQueue<T> implements Iterable{
    private Node first;
    private Node last;
    private int size;

    private class Node{
        T t;
        Node next;
    }

    public boolean isEmpty(){return first == null;}
    public int size(){return size;}

    public void enqueue(T t){
        Node oldLast = last;
        last = new Node();
        last.t = t;
        last.next = null;
        if (isEmpty()) first = last;
        else oldLast.next = last;
        size++;
    }

    public T dequeue(){
        T t = first.t;
        first = first.next;
        if (isEmpty()) last = null;
        size--;
        return t;
    }

    @Override
    public Iterator iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator{
        private Node current = first;
        @Override
        public boolean hasNext() {
            return current!=null;
        }

        @Override
        public T next() {
            T t = current.t;
            current = current.next;
            return t;
        }
    }
}
