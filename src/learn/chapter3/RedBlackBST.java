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
        h.color = RED;
        h.left.color = BLACK;
        h.right.color = BLACK;
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
     * Is this symbol table empty?
     * @return {@code true} if this symbol table is empty and {@code false} otherwise
     */
    public boolean isEmpty() {
        return root == null;
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

    private boolean isRedBlackBST() {
        if (!isBST()) return false;
        if (!is23()) return false;
        if (!isBalanced()) return false;
        return true;
    }

    public static void main(String[] args) {
        String str = "S E A R C H E X A M P L E";
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
//        System.out.println("是否是红黑树："+st.isRedBlackBST());
        int j = 0;

    }


}
