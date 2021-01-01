package ex.chapter1_3;

/**
 * 字符串队列(可变数组实现)
 */
public class ResizingArrayQueueOfStrings {
    private String[] a;
    private int index;

    public ResizingArrayQueueOfStrings(int cap) {
        a = new String[cap];
    }
    public boolean isEmpty(){return index ==0;}
    //添加的isFull()方法
    public boolean isFull(){return index == a.length;}
    public int size(){return index;}
    public void enqueue(String item){a[index++] = item;}
    public String dequeue(){return a[--index];}
}
