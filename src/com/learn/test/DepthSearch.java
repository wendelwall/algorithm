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
        this.dfs(1);//首先从1号盒子开始
    }

    /**
     * 深度优先搜索
     * 输入一个数n，输出1~n的全排序
     * depth first search, dfs
     * @param step 站在第几个盒子面前
     */
    private void dfs(int step){
        if(step == n + 1){//如果站在第n+1个盒子前面，则表示前n个盒子已经放好了扑克牌
            //输出一种排列（1-n号盒子中的扑克牌编号）
            for (int i = 1; i <= n; i++) {
                System.out.print(a[i] + " ");
            }
            System.out.println();
            return;//返回之前的一步（最近一次掉用dfs函数的地方）
        }
        //此时站在第step个盒子前面，应该放那张牌呢？
        //按照1、2、3...n的顺序一一尝试
        for (int i = 1; i <= n; i++) {
            //判断扑克牌i是否还在手上
            if(book[i] == 0){//book[i]等于0表示i号扑克牌还在手上
                //开始尝试使用扑克牌i
                a[step] = i;//将i号扑克牌放入到第step号扑克牌盒子中
                book[i] = 1;//蒋book[i]设为1，表示i号扑克牌已经不在手上了
                //第step个盒子已经放好了扑克牌，接下来走到下一个盒子面前
                this.dfs(step + 1);//这里通过函数递归调用来实现（自己调用自己）
                book[i] = 0;//这一步非常重要，一定要将刚才尝试的扑克牌收回，才能进行下一次尝试
            }
        }
        return;
    }
}
