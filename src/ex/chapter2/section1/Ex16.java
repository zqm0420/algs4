package ex.chapter2.section1;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 2.1.16 验证。编写一个 check()方法,调用sort () 对任意数组排序。如果排序成功而且数组中的所
 * 有对象均没有被修改则返回 true,否则返回false。不要假设 sort() 只能通过 exch () 来移动
 * 数据,可以信任并使用 Arrays.sort()。
 */

public class Ex16 {
    public static Boolean check(Comparable[] a) {
        //键：数组中的元素。值：元素出现的次数
        Map<Comparable, Integer> valuesMap = new HashMap<>();

        //初始化此Map
        for (Comparable value : a) {
            int count = 1;
            if (valuesMap.containsKey(value)) {
                count = valuesMap.get(value);
                count++;
            }
            valuesMap.put(value, count);
        }

        Arrays.sort(a);

        //判断数组中的所有对象有没有被修改
        for (Comparable value : a) {
            if (valuesMap.containsKey(value)) {
                int count = valuesMap.get(value);
                count--;
                if (count == 0) {
                    valuesMap.remove(value);
                } else {
                    valuesMap.put(value, count);
                }
            } else {
                return false;
            }
        }
        if (valuesMap.size() > 0) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Comparable[] arr = {1,3,3,2,5,-12,6,4,2,8,-2,0,9,0,-8};
        System.out.println(check(arr));

    }
}
