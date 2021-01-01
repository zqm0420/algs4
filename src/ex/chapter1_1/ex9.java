package ex.chapter1_1;

/**
 * 将一个正整数N用二进制表示并转换为一个String类型的值s
 * @author zqm
 */
public class ex9 {
    static String binaryToString(int n){
        String s = "";
        for(int i = n;i>0;i /= 2){
            s = (i % 2) + s;
        }
        return s;
    }

    public static void main(String[] args) {
        String s = binaryToString(4);
        System.out.println(s);
    }
}
