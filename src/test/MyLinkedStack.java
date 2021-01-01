package test;

import java.util.Iterator;

/**
 * 我的栈(链表实现)
 * @author zqm
 */
public class MyLinkedStack<T> implements Iterable<T> {
    private Node first;
    private int eleNum;

    private class Node{ //定义了节点的嵌套类
        private T t;
        private Node next;
    }

    public boolean isEmpty(){ return first == null; }
    public int size(){return eleNum;}

    public void push(T t){  //向栈顶增加元素
        Node oldFirst = first;
        first = new Node();
        first.t = t;
        first.next = oldFirst;
        eleNum++;
    }

    public void peek(){ //从栈顶弹出元素
        first = first.next;
        eleNum--;
    }

    public T pop(){  //从栈顶删除元素
        T t = first.t;
        first = first.next;
        eleNum--;
        return t;
    }

    @Override
    public Iterator<T> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<T>{
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

    public static void main(String[] args) {
        MyLinkedStack<Integer> numStack = new MyLinkedStack<Integer>();
        for (int i=0;i<3;i++){
            numStack.push(i);
        }
        for (int i=0;i<2;i++){
            System.out.println(numStack.pop());
        }

    }

}
