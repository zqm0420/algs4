package ex.chapter1_3;

/**
 * 为FixedCapacityStackOfStrings类添加一个isFull()方法
 * @author zqm
 */
public class FixedCapacityStackOfStrings {
    private String[] a;
    private int N;

    public FixedCapacityStackOfStrings(int cap) {
        a = new String[cap];
    }
    public boolean isEmpty(){return N==0;}
    //添加的isFull()方法
    public boolean isFull(){return N == a.length;}
    public int size(){return N;}
    public void push(String item){a[N++] = item;}
    public String pop(){return a[--N];}
}
