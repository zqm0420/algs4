package learn.chapter3;

import learn.chapter1.Queue;

/**
 * 基于二分查找树的符号表
 */
public class BST<Key extends Comparable<Key>, Value> {
    private Node root;

    private class Node {
        private Node left, right;
        private Key key;
        private Value value;
        private int n;

        public Node(Key key, Value value, int n) {
            this.key = key;
            this.value = value;
            this.n = n;
        }
    }

    public int size() {
        return size(root);
    }

    private int size(Node x) {
        if (x == null) return 0;
        else return x.n;
    }

    public Value get(Key key) {
        return get(root, key);
    }

    private Value get(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            return get(x.left, key);
        } else if (cmp > 0) {
            return get(x.right, key);
        } else {
            return x.value;
        }
    }

    public void put(Key key, Value value) {
        root = put(root, key, value);
    }

    private Node put(Node x, Key key, Value value) {
        if (x == null) {
            return new Node(key, value, 1);
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            x.left = put(x.left, key, value);
        } else if (cmp > 0) {
            x.right = put(x.right, key, value);
        } else {
            x.value = value;
        }
        x.n = size(x.left) + size(x.right) + 1;
        return x;
    }

    public Key min() {
        return min(root).key;
    }

    private Node min(Node x) {
        if (x.left == null) return x;
        return min(x.left);
    }

    public Key max() {
        return max(root).key;
    }

    private Node max(Node x) {
        if (x.right == null) return x;
        return max(x.right);
    }

    public Key floor(Key key) {
        return floor(root, key).key;
    }


    private Node floor(Node x, Key key) {    //查找x节点子树小于等于key的最大节点
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp == 0) return x;
        if (cmp < 0) return floor(x.left, key);
        Node t = floor(x.right, key);
        if (t != null) return t;
        else return x;
    }

    public Key ceiling(Key key) {
        return ceiling(root, key).key;
    }

    private Node ceiling(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp == 0) return x;
        else if (cmp > 0) return ceiling(x.right, key);
        Node t = ceiling(x.left, key);
        if (t != null) return t;
        else return x;
    }

    public Key select(int k) {
        if (k < 0 || k >= root.n) {
            throw new IllegalArgumentException("超限");
        }
        return select(root, k).key;
    }

    private Node select(Node x, int k) {
        int t = size(x.left);
        if (t > k) return select(x.left, k);
        else if (t < k) return select(x.right, k - t - 1);
        else return x;
    }

    public int rank(Key key) {
        return rank(key, root);
    }

    private int rank(Key key, Node x) {
        if (x == null) return 0;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) return rank(key, x.left);
        else if (cmp > 0) return rank(key, x.right) + size(x.left) + 1;
        else return size(x.left);
    }

    public void deleteMin() {
        deleteMin(root);
    }

    private Node deleteMin(Node x) {
        if (x.left == null) {   //x节点的左子节点为空，说明x已经是最小的节点，那么就返回x.right
            return x.right;
        }
        x.left = deleteMin(x.left); //递归
        x.n = size(x.left) + size(x.right) + 1; //更新n的值
        return x;
    }

    public void deleteMax() {
        deleteMax(root);
    }

    private Node deleteMax(Node x) {
        if (x.right == null) {
            return x.left;
        }
        x.right = deleteMax(x.right);
        x.n = size(x.left) + size(x.right) + 1;
        return x;
    }

    public void delete(Key key) {
        root = delete(root, key);
    }

    private Node delete(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) delete(x.left, key);
        else if (cmp > 0) delete(x.right, key);
        else {
            if (x.right==null)return null;
            if (x.left==null)return null;
            Node t = x;
            x = min(t.right);
            x.right = deleteMin(t.right);
            x.left = t.left;
        }
        x.n = size(x.left)+size(x.right)+1;
        return x;
    }

    public Iterable<Key> keys(){
        return keys(this.min(), this.max());
    }

    public Iterable<Key> keys(Key lo, Key hi){
        Queue<Key> queue = new Queue<>();
        keys(root, queue, lo, hi);
        return queue;
    }

    private void keys(Node x, Queue queue, Key lo, Key hi){
        if (x==null) return;
        int cmpLo = lo.compareTo(x.key);
        int cmpHi = hi.compareTo(x.key);
        if (cmpLo<0)    keys(x.left, queue, lo, hi);
        if (cmpLo<=0 && cmpHi>=0)   queue.enqueue(x.key);
        if (cmpHi>0)    keys(x.right, queue, lo, hi);
    }


    public static void main(String[] args) {
        String str = "S E A R C H E X A M P L E";
        String[] a = str.split(" ");
        BST<String, Integer> st = new BST<>();
        for (int i = 0; i < a.length; i++) {
            st.put(a[i], i);
        }
        System.out.println(st.rank("D"));
//        st.deleteMax();
//        st.delete("A");
        Iterable<String> keys = st.keys();
        int j = 0;

    }
}
