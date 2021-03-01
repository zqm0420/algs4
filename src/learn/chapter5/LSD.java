package learn.chapter5;

/**
 * 低位优先的字符串排序
 * 此方法总运行时间与WN成正比，（W为字符串长度，N为字符串个数）
 * 此算法是一种适用于一般应用的线性时间排序算法，而第二章的所有排序算法都达不到线性时间复杂度
 */
public class LSD {
    private static void sort(String[] a, int w) {//通过前w个字符将a[]排序
        int n = a.length;
        int r = 128;
        String[] aux = new String[n];


        for (int d = w - 1; d >= 0; d--) {
            int count[] = new int[r+1];
            for (int i = 0; i < n; i++) {   //计算出现频率
                count[a[i].charAt(d)+1]++;
            }
            for (int i = 0; i < r; i++) {   //将频率转换为索引
                count[r+1]+=count[r];
            }
            for (int i = 0; i < n; i++) {   //将元素分类
                aux[count[a[i].charAt(d)]++] = a[i];
            }
            System.arraycopy(aux, 0, a, 0, n);  //回写

        }
    }
}
