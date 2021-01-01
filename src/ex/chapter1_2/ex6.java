package ex.chapter1_2;

/**
 * 判断是否是回文
 */
public class ex6 {
    public static boolean Huiwen(String s, String t){
        return s.length()==t.length() && s.concat(s).contains(t);
    }

    public static void main(String[] args) {
        String a = "ACTGACG";
        String b = "TGACGAC";
        System.out.println(Huiwen(a,b));
    }
}
