package com.hss.test;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;

/**
 * @ProjectName: ins
 * @Package: com.hss.test
 * @ClassName: ForeachMap
 * @Description: java类作用描述
 * @Author: hss
 * @Create_Date: 2019/5/28 18:08
 * @Update_By:
 * @Update_Date: 2019/5/28 18:08
 * U.H. All Rights Reserved.
 */
public class ForeachMap {
    public static void main(String[] args) {
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "serialize-1");
        map.put(2, "serialize-2");
        map.put(3, "serialize-3");
        map.put(4, "serialize-4");
        map.put(5, "serialize-5");
        map.put(6, "serialize-6");
        map.put(7, "serialize-7");
        map.put(8, "serialize-8");
        long start = System.currentTimeMillis();


        // 利用keySet遍历Map
//        foreachMethod1(map);
        // 利用entrySet进行遍历
        foreachMethod2(map);
        // 利用keySet与Values()方法分别遍历
//        foreachMethod3(map);
        // 利用Iterator遍历
//        foreachMethod4(map);

        System.out.println("执行耗时[" + (System.currentTimeMillis() - start) + "]");
//        String s = "ABCdbc";
//        s = s.replace("A", "a");
//        System.out.println(s);



    }

    /**
     * 利用keySet遍历Map
     *
     * @param map
     */
    public static void foreachMethod1(Map<Integer, String> map) {
        Set<Integer> integerSet = map.keySet();
        for (Integer i : integerSet) {
            System.out.println("key: " + i + " value:" + map.get(i));
        }
    }


    /**
     * 利用entrySet遍历Map
     *
     * @param map
     */
    public static void foreachMethod2(Map<Integer, String> map) {
        Set<Map.Entry<Integer, String>> newMap = map.entrySet();
        for (Map.Entry<Integer, String> entry : newMap) {
            System.out.println("key: " + entry.getKey() + " value:" + entry.getValue());
        }
    }

    /**
     * for-each循环中遍历keys或values
     *
     * @param map
     */
    public static void foreachMethod3(Map<Integer, String> map) {
        for (Integer i : map.keySet()) {
            System.out.println("key :" + i);
        }

        for (String s : map.values()) {
            System.out.println("value :" + s);
        }
    }

    /**
     * 使用Iterator遍历
     *
     * @param map
     */
    public static void foreachMethod4(Map<Integer, String> map) {
        Iterator<Map.Entry<Integer, String>> entries = map.entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry<Integer, String> entry = entries.next();
            System.out.println("key: " + entry.getKey() + "  value: " + entry.getValue());
        }
    }
}
