package learn.chapter3;

public class LinearProbingHashST<Key, Value> {
    private int n;
    private int m;
    private Key[] keys;
    private Value[] values;

    public LinearProbingHashST() {
        keys = (Key[]) new Object[m];
        values = (Value[]) new Object[m];
    }

    public int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % m;
    }

    public Value get(Key key) {
        int h = hash(key);
        for (int i = h; keys[i] != null; i = (i + 1) % m) {
            if (key.equals(keys[i])) {
                return values[i];
            }
        }
        return null;
    }

    public void put(Key key, Value val) {
        int h = hash(key);
        int i;
        for (i = h; keys[i] != null; i = (i + 1) % m) {
            if (key.equals(keys[i])) {
                values[i] = val;
                return;
            }
        }
        keys[i] = key;
        values[i] = val;
        n++;
    }

    public void delete(Key key) {
        if (!contains(key)) return;
        int i = hash(key);
        while (keys[i] != null) {
            i = (i + 1) % m;
        }
        keys[i] = null;
        values[i] = null;
        i = (i + 1) % m;
        while (keys[i] != null) {
            Key keyToReDo = keys[i];
            Value valueToReDo = values[i];
            keys[i] = null;
            values[i] = null;
            i = (i + 1) % m;
            n--;
            put(keyToReDo, valueToReDo);
        }
        n--;
    }

    public boolean contains(Key key) {
        return get(key) != null;
    }
}
