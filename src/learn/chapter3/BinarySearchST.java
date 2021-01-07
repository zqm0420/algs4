package learn.chapter3;

import learn.chapter1.Queue;

/**
 * 基于两个数组的有序泛型符号表（Map）
 */
public class BinarySearchST<Key extends Comparable<Key>, Value> {
    private Key[] keys;
    private Value[] values;
    private int n;  //符号表键值对的数量

    public BinarySearchST() {
        this(1);
    }

    public BinarySearchST(int capacity) {
        this.keys = (Key[]) new Comparable[capacity];
        this.values = (Value[]) new Comparable[capacity];
        this.n = 0;
    }

    private int rank(Key key) {
        int lo = 0;
        int hi = n - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int cmp = key.compareTo(keys[mid]);
            if (cmp < 0) {
                hi = mid - 1;
            }
            else if (cmp > 0) {
                lo = mid + 1;
            } else {
                return mid;
            }
        }
        return lo;
    }

    public void put(Key key, Value value) {
        if (n == keys.length) {
            resizeArray(2 * n);
        }
        int i = rank(key);  //获得key对应的索引，如果没有则获得key值之前的键值对个数
        if (i<n && (key.compareTo(keys[i]) == 0)) { //如果符号表中有此键值对，则更新值
            values[i] = value;
            return;
        }
        for (int j = n; j > i; j--) {   //符号表没有此键值对，需要在数组中插入键值对
            keys[j] = keys[j - 1];
            values[j] = values[j - 1];
        }
        keys[i] = key;
        values[i] = value;
        n++;
    }

    public Value get(Key key) {
        int i = rank(key);
        if (key.compareTo(keys[i])==0){
            return values[i];
        }
        return null;
    }

    public int size() {
        return n;
    }

    public boolean contains(Key key){
        int i = rank(key);
        if (key.compareTo(keys[i])==0){
            return true;
        }else{
            return false;
        }
    }

    public boolean isEmpty(){
        return n==0;
    }

    public Key min(){
        if (n==0){
            return null;
        }
        return keys[0];
    }

    public Key max(){
        if (n==0){
            return null;
        }
        return keys[n-1];
    }

    public Key floor(Key key){    //小于等于key的最大键
        if (n==0){
            return null;
        }
        int i = rank(key);
        if (i==0){
            throw new IllegalArgumentException();
        }
        return keys[--i];
    }

    public Key ceiling(Key key){
        if (n==0){
            return null;
        }
        int i = rank(key);
        if (i>=n){
            throw new IllegalArgumentException();
        }
        return keys[i];
    }

    public Key select(int k){
        if (n==0){
            return null;
        }
        return keys[k];
    }

    public void deleteMin(){
        delete(keys[0]);
    }

    public void deleteMax(){
        delete(keys[n-1]);
    }

    public int size(Key lo, Key hi){
        return rank(hi)-rank(lo);
    }

    public Iterable<Key> keys(){
        return keys(keys[0], keys[n-1]);
    }

    public Iterable<Key> keys(Key lo, Key hi){
        Queue<Key> queue = new Queue<>();
        int low = rank(lo);
        int high = rank(hi);
        for (int i = low; i <= high; i++) {
            queue.enqueue(keys[i]);
        }
        return queue;
    }

    public void delete(Key key) {
        if (!contains(key))
            throw new IllegalArgumentException();
        int i = rank(key);
        for (int j = i;j<n;j++){
            keys[j] = keys[j+1];
            values[j] = values[j+1];
        }
        n--;
    }

    /*******************************
     * 动态调整数组大小
     *******************************/
    private void resizeArray(int max) {
        Key[] tempKey = (Key[]) new Comparable[max];
        Value[] tempValue = (Value[]) new Comparable[max];
        for (int i = 0; i < keys.length; i++) {
            tempKey[i] = keys[i];
            tempValue[i] = values[i];
        }
        keys = tempKey;
        values = tempValue;
    }

    public static void main(String[] args) {
        String str = "S E A R C H E X A M P L E";
        String[] a = str.split(" ");
        BinarySearchST<String, Integer> st = new BinarySearchST<>();
        for (int i = 0; i < a.length; i++) {
            st.put(a[i], i);
        }
        for (String key : st.keys()) {
            System.out.print(key+" ");
        }
    }
}
