package learn.chapter3;

/**
 * 红黑二叉查找树（红黑树）
 */
public class RedBlackBST<Key extends Comparable<Key>, Value> {
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private Node root;
    private class Node {
        Key key;
        Value val;
        Node left, right;
        int n;
        boolean color;

        public Node(Key key, Value val, int n, boolean color) {
            this.key = key;
            this.val = val;
            this.n = n;
            this.color = color;
        }
    }

    private boolean isRed(Node x) {
        if (x == null) return false;
        return x.color == RED;
    }

    private Node rotateLeft(Node h) {    //左旋转
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        h.color = RED;
        x.n = h.n;
        h.n = size(h.left) + size(h.right) + 1;
        return x;
    }

    private Node rotateRight(Node h) {   //右旋转
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = RED;
        x.n = h.n;
        h.n = size(h.left) + size(h.right) + 1;
        return x;
    }

    private void flipColors(Node h) {    //变色
        h.color = !h.color;
        h.left.color = !h.left.color;
        h.right.color = !h.right.color;
    }

    public int size() {
        return size(root);
    }

    private int size(Node x) {
        if (x == null) return 0;
        else return x.n;
    }

    public void put(Key key, Value val) {
        root = put(root, key, val);
        root.color = BLACK;
    }

    private Node put(Node x, Key key, Value val) {
        if (x == null) {
            return new Node(key, val, 1, RED);
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            x.left = put(x.left, key, val);
        } else if (cmp > 0) {
            x.right = put(x.right, key, val);
        } else {
            x.val = val;
        }
        if (isRed(x.right) && !isRed(x.left)) x = rotateLeft(x);
        if (isRed(x.left) && isRed(x.left.left)) x = rotateRight(x);
        if (isRed(x.left) && isRed(x.right)) flipColors(x);
        x.n = size(x.left) + size(x.right) + 1;
        return x;
    }

    /**
     * 删除最小键功能
     *
     * @return
     */
    private Node moveRedLeft(Node h) {
        flipColors(h);
        if (isRed(h.right.left)) {
            //如果当前节点的左子节点是2-结点，这个左子节点的兄弟结点是3-结点，
            //则将左子节点的兄弟结点的一个键移动到左子节点中
            h.right = rotateRight(h.right);
            h = rotateLeft(h);
        }
        return h;
    }
    private Node balance(Node h) {
        if (isRed(h.right)) {
            h = rotateLeft(h);
        }
        if (isRed(h.right) && !isRed(h.left)) h = rotateLeft(h);
        if (isRed(h.left) && isRed(h.left.left)) h = rotateRight(h);
        if (isRed(h.left) && isRed(h.right)) flipColors(h);
        h.n = size(h.left) + size(h.right) + 1;
        return h;
    }

    public void deleteMin() {
//        if (!isRed(root.left) && !isRed(root.right)){
//            root.color = RED;
//        }
        root = deleteMin(root);
        if (!isEmpty()) root.color = BLACK;
    }

    private Node deleteMin(Node h) {
        if (h.left == null) {
            return null;
        }
        if (!isRed(h.left) && !isRed(h.left.left)) { //当左子节点是2-结点时，则需要调用下面的方法
            h = moveRedLeft(h);
        }
        h.left = deleteMin(h.left);
        return balance(h);
    }

    /**
     * 删除最大键功能
     *
     * @return
     */
    private Node moveRedRight(Node h) {
        flipColors(h);
        if (isRed(h.left.left)) {
            h = rotateRight(h);
        }
        return h;
    }
    public void deleteMax() {
        root = deleteMax(root);
        if (!isEmpty()) root.color = BLACK;
    }
    private Node deleteMax(Node h) {
        if (isRed(h.left)) h = rotateRight(h);  //因为红链接都是左链接，所以需要做右转换，将红链接转成左链接
        if (h.right == null) return null;
        if (!isRed(h.right) && !isRed(h.right.left)) {
            h = moveRedRight(h);
        }
        h.right = deleteMax(h.right);
        return balance(h);
    }

    /**
     * 删除功能
     * @return
     */
    public void delete(Key key){
        if (!isRed(root.left) && !isRed(root.right))
            root.color = RED;

        root = delete(root, key);
        if (!isEmpty()) root.color = BLACK;
    }
    private Node delete(Node h, Key key) {
        // assert get(h, key) != null;

        if (key.compareTo(h.key) < 0)  {
            if (!isRed(h.left) && !isRed(h.left.left))
                h = moveRedLeft(h);
            h.left = delete(h.left, key);
        }
        else {
            if (isRed(h.left))
                h = rotateRight(h);
            if (key.compareTo(h.key) == 0 && (h.right == null))
                return null;
            if (!isRed(h.right) && !isRed(h.right.left))
                h = moveRedRight(h);
            if (key.compareTo(h.key) == 0) {
                Node x = min(h.right);
                h.key = x.key;
                h.val = x.val;
                // h.val = get(h.right, min(h.right).key);
                // h.key = min(h.right).key;
                h.right = deleteMin(h.right);
            }
            else h.right = delete(h.right, key);
        }
        return balance(h);
    }

    public boolean isEmpty() {
        return root == null;
    }


    public Key min() {
        return min(root).key;
    }

    // the smallest key in subtree rooted at x; null if no such key
    private Node min(Node x) {
        // assert x != null;
        if (x.left == null) return x;
        else                return min(x.left);
    }

    /*************************************
     * 检查是否是一棵二分查找树
     ************************************/
    private boolean isBST() {
        return isBST(root, null, null);
    }

    private boolean isBST(Node x, Key min, Key max) {
        if (x == null) return true;
        if (min != null && x.key.compareTo(min) <= 0) return false;
        if (max != null && x.key.compareTo(max) >= 0) return false;
        return isBST(x.left, min, x.key) && isBST(x.right, x.key, max);
    }

    /*************************************
     * 检查是否是一棵23树
     ************************************/
    private boolean is23() {
        return is23(root);
    }

    private boolean is23(Node x) {
        if (x == null) {
            return true;
        }
        if (x.left == null && x.right == null) {
            return true;
        }
        if (isRed(x.right)) {
            return false;
        }
        if (isRed(x) && isRed(x.left)) {
            return false;
        }
        return is23(x.left) && is23(x.right);
    }

    /*************************************
     * 检查从根节点到所有空链接的路径上的黑链接的数量是否相同
     ************************************/
    private boolean isBalanced() {
        int blackNum = 0;
        Node x = root;
        while (x != null) {
            if (!isRed(x)) {
                blackNum++;
            }
            x = x.left;

        }
        return isBalanced(root, blackNum);
    }

    private boolean isBalanced(Node x, int blackNum) {
        if (x == null) return blackNum == 0;
        if (!isRed(x)) {
            blackNum--;
        }
        return isBalanced(x.left, blackNum) && isBalanced(x.right, blackNum);

    }

    public boolean isRedBlackBST() {
        if (!isBST()) return false;
        if (!is23()) return false;
        if (!isBalanced()) return false;
        return true;
    }

    public static void main(String[] args) {
        String str = "A B C D E F G H I J K L M N";
        String[] a = str.split(" ");
        RedBlackBST<String, Integer> st = new RedBlackBST<>();
        for (int i = 0; i < a.length; i++) {
            st.put(a[i], i);
        }
//        System.out.println(st.rank("D"));
//        st.deleteMax();
//        st.delete("A");
//        Iterable<String> keys = st.keys();
//        System.out.println("是否是23树：" + st.is23());
//        System.out.println("树是否平衡：" + st.isBalanced());
//        st.deleteMin();
//        st.deleteMax();
//        st.deleteMax();
        st.delete("D");
        System.out.println("是否是红黑树：" + st.isRedBlackBST());

        int j = 0;

    }


}
