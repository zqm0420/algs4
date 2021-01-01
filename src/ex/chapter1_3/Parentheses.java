package ex.chapter1_3;

import test.MyLinkedStack;

import java.io.*;

/**
 * 从标准输入中读取一个文本流并使用栈判定其中的括号是否配对完整.
 *
 * @author zqm
 */
public class Parentheses {
    private static final char LEFT_PAREN = '(';
    private static final char RIGHT_PAREN = ')';
    private static final char LEFT_BRACKET = '[';
    private static final char RIGHT_BRACKET = ']';
    private static final char LEFT_BRACE = '{';
    private static final char RIGHT_BRACE = '}';

    public static boolean isBanlanced(String s) {
        MyLinkedStack<Character> stack = new MyLinkedStack<Character>();
        for (int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == LEFT_PAREN)   {stack.push(LEFT_PAREN); }
            if(s.charAt(i) == LEFT_BRACKET)   {stack.push(LEFT_BRACKET); }
            if(s.charAt(i) == LEFT_BRACE)   {stack.push(LEFT_BRACE); }

            if(s.charAt(i) == RIGHT_PAREN)  {
                if(stack.isEmpty()) return false;
                if(stack.pop()!=LEFT_PAREN) return false;
            }
            if(s.charAt(i) == RIGHT_BRACKET)  {
                if(stack.isEmpty()) return false;
                if(stack.pop()!=LEFT_BRACKET) return false;
            }
            if(s.charAt(i) == RIGHT_BRACE)  {
                if(stack.isEmpty()) return false;
                if(stack.pop()!=LEFT_BRACE) return false;
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        BufferedReader br = null;
        try {
            br = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream("src/ex/chapter1_3/testParentheses.txt")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String s = null;
        try {
            s = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(isBanlanced(s));
    }
}
