package com.learn.test;

import org.junit.Test;

import java.util.Random;

/**
 * @author ：sunrise
 * @description ：
 * @copyright ：	Copyright 2019 yowits Corporation. All rights reserved.
 * @create ：2019/1/19 10:53
 */
public class Sort {

    /**
     * 桶排序
     * 需要明确知道最大的数-确定桶的个数
     * 时间复杂度为O(n+m+n)=O(2n+m)
     */
    @Test
    public void bucketSort(){
        int[] n = new int[]{6,1,10,7,9,6,4,10,8,5};
        int[] a = new int[n.length + 1];
//        for (int i = 0; i < a.length; i++) {
//            a[i] = 0;
//        }

        for (int i = 0; i < n.length; i++) {
            a[n[i]]++;
        }

        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i]; j++) {
                System.out.print(i + " ");
            }
        }
        System.out.println();
    }

    /**
     * 冒泡排序
     * 时间复杂度为O(n²)
     * n为需要排序的个数
     */
    @Test
    public void maopaoSort(){
        int[] a = new int[]{6,1,2,7,9,3,4,10,8,5};
        int temp;
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a.length - i - 1; j++) {//核心是
                if(a[j] > a[j+1]){
                    temp = a[j];
                    a[j] = a[j+1];
                    a[j+1] = temp;
                }
            }
        }
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
    }


    @Test
    public void quickSort(){
        //int[] a = new int[]{1,6,2,7,9,3,4,10,8,5};
        int n = 1000000;
        int[] a = new int[n];
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            a[i] = random.nextInt(n);
        }
        long startTime = System.currentTimeMillis();
        this.quickSort(a, 0, n - 1);
        for (int k = a.length - 1; k >= 0 ; k--) {
            System.out.print(a[k] + " ");
        }
        long endTime = System.currentTimeMillis();
        System.out.println();
        System.out.println("快速排序所属时间："+(endTime - startTime) / 1000);
    }

    /**
     * 快速排序法（二分查找法排序）
     * 1.最差的时间复杂度与冒泡排序的时间复杂度一致为O(n²)线性阶
     * 2.平均的时间复杂度位O(nlogn)线性对数阶
     * @param a
     * @param left
     * @param rigth
     */
    private void quickSort(int[] a, int left, int rigth){
        if(left > rigth){
            return;
        }
        int i = left,j = rigth, mid = a[left], temp;
        while (i != j){
            while (a[j] >= mid && i < j)
                j--;
            while (a[i] <= mid && i < j)
                i++;

            if(i < j){
                temp = a[i];
                a[i] = a[j];
                a[j] = temp;
            }
        }
        a[left] = a[i];
        a[i] = mid;
        this.quickSort(a, left, i - 1);//基数左边的数据全部小于该基数，递归再次选计算排序
        this.quickSort(a, i + 1, rigth);//基数右边的数据全部大于该基数，递归再次选计算排序
    }

}
