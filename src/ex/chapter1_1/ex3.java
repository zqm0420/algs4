package ex.chapter1_1;

import java.util.Scanner;

/**
 * 编写一个程序, 从命令行得到三个整数参数. 如果他们都相等则打印equal, 否则打印not equal.
 * @author zqm
 */
public class ex3 {
    static String threeIntegerEqual(int a, int b, int c){
        if (a == b) {
            if (a == c) {
                return "equal";
            }
        }
        return "not equal";
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        //请输入三个整数
        int a = scan.nextInt();
        int b = scan.nextInt();
        int c = scan.nextInt();
        System.out.println(threeIntegerEqual(a,b,c));

    }
}
