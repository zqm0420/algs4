package learn.chapter3;

import learn.chapter1.Queue;

/**
 * 使用基本链表实现的无序符号表，查找和插入都需要遍历，效率非常慢
 */
public class SequentialSearchST<Key, Value> {
    private Node first;
    private int n;  //符号表的大小

    private class Node {
        Key key;
        Value value;
        Node next;

        public Node(Key key, Value value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    public Value get(Key key) {
        for (Node x = first; x != null; x = x.next) {
            if (key.equals(x.key)) {
                return x.value;
            }
        }
        return null;
    }

    public void put(Key key, Value value) {
        for (Node x = first; x != null; x = x.next) {
            if (key.equals(x.key)) {
                x.value = value;
                return;
            }
        }
        first = new Node(key, value, first);
        n++;
    }

    public Iterable<Key> keys() {
        Queue<Key> queue = new Queue<Key>();
        for (Node x = first; x != null; x = x.next) {
            queue.enqueue(x.key);
        }
        return queue;
    }

    public int size() {
        return n;
    }


    public void delete(Key key) {
        if (!contains(key)) throw new IllegalArgumentException("没有相应的主键");
        first = delete(first, key);
    }

    public Node delete(Node x, Key key) {
        if (x == null) return null;
        if (key.equals(x.key))
        {
            n--;
            return x.next;
        }
        x.next = delete(x.next, key);
        return x;
    }

    /************************
     * 一些默认实现的方法
     ***********************/
    public boolean contains(Key key) {
        return get(key) != null;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public static void main(String[] args) {
        SequentialSearchST<String, Integer> st = new SequentialSearchST();
        String str = "S E A R C H E X A M P L E";
        String[] a = str.split(" ");
        for (int i = 0; i < a.length; i++) {
            st.put(a[i], i);
        }
        Iterable<String> keys = st.keys();
        for (String key : keys) {
            System.out.print(key+" ");
        }
        int j = 0;
        st.delete("L");
    }
}
