package learn.chapter3;

/**
 * 基于拉链法的散列表
 * @param <Key>
 * @param <Value>
 */
public class SeparateChainingHashST<Key, Value> {
    private int n;
    private int m;
    private SequentialSearchST<Key, Value>[] st;

    public SeparateChainingHashST(){
        this(997);
    }

    public SeparateChainingHashST(int m) {
        this.m = m;
        st = (SequentialSearchST<Key, Value>[])new SequentialSearchST[m];
        for (int i = 0; i < st.length; i++) {
            st[i] = new SequentialSearchST<>();
        }
    }

    public int hash(Key key){
        return (key.hashCode() & 0x7fffffff) % m;
    }

    public Value get(Key key){
        return st[hash(key)].get(key);
    }

    public void put(Key key, Value val){
        st[hash(key)].put(key, val);
    }

    public void delete(Key key){
        st[hash(key)].delete(key);
    }
}
