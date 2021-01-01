package learn;

import edu.princeton.cs.algs4.In;

import java.util.Stack;

public class Test {
    public static void main(String[] args) {
        String[] a = In.readStrings();
        int N = a.length;
        int h = 1;
        int[] hArray;   //存放递增序列的数组
        int count = 0;  //递增序列数组大小
        while(h < N/3){
            h = h * 3 + 1;
            count++;
        }
        hArray = new int[count];
        h = 1;
        for (int i = 0; i < count; i++) {
            hArray[i] = h;
            h = h * 3 + 1;
            if(h>= N/3){
                break;
            }
        }
        for (int i = 0; i < hArray.length; i++) {
            System.out.println(hArray[i]);
        }
    }
}
