package com.learn.test;

import org.junit.Test;

/**
 * 深度优先搜索
 * @author ：sunrise
 * @description ：
 * @copyright ：	Copyright 2019 yowits Corporation. All rights reserved.
 * @create ：2019/1/20 16:56
 */
public class DepthSearch {
    int[] a = new int[10];//盒子1-9个盒子
    int[] book = new int[10];//用于标识1-9的扑克牌是否已放入盒子，0-为放入盒子，1-已放入盒子
    int n = 3;
    @Test
    public void searchMain(){
        this.dfs(1);
    }

    /**
     * 深度优先搜索
     * 输入一个数n，输出1~n的全排序
     * depth first search, dfs
     * @param step 站在第几个盒子面前
     */
    private void dfs(int step){
        if(step == n + 1){
            for (int i = 1; i <= n; i++) {
                System.out.print(a[i] + " ");
            }
            System.out.println();
            return;
        }
        for (int i = 1; i <= n; i++) {
            if(book[i] == 0){
                a[step] = i;
                book[i] = 1;
                this.dfs(step + 1);
                book[i] = 0;
            }
        }
        return;
    }
}
