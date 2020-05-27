package com.hss.test;

/**
 * @ProjectName: ins
 * @Package: com.hss.test
 * @ClassName: BubbleSort
 * @Description: java类作用描述--冒泡排序
 * @Author: hss
 * @Create_Date: 2019/6/1 16:23
 * @Update_By:
 * @Update_Date: 2019/6/1 16:23
 * U.H. All Rights Reserved.
 */
public class BubbleSort {
    public static void main(String[] args) {
        int[] array = {5, 3, 8, 9, 1, 2};
        System.out.println("排序前的数组为：");
        for (int i : array) {
            System.out.print(i + " ");
        }

        // 外层循环控制趟数
        for (int i = 0; i < array.length - 1; i++) {
            // 内层循环控制每趟排序次数
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
        System.out.println();
        System.out.println("排序后的数组为：");
        for (int i : array) {
            System.out.print(i + " ");
        }
        Object obj = new Object();
    }
}
