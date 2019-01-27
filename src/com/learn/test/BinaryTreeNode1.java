package com.learn.test;

import org.junit.Test;

/**
 * @author ：sunrise
 * @description ：二叉树数-1.最小堆（根节点最小）2.最大堆（根节点最大）
 * @copyright ：	Copyright 2019 yowits Corporation. All rights reserved.
 * @create ：2019/1/27 17:10
 */
public class BinaryTreeNode1<T> {
    private int[] h = {99,5,36,7,22,17,46,12,2,19,25,28,1,92};
    private int n = h.length - 1;

     //交换函数，用来交换堆中两个元素的值
    private void swap(int x, int y){
        int t = h[x];
        h[x] = h[y];
        h[y] = t;
    }

    //向下调整函数
    private void siftdown(int i){//传入一个需要向下调整的结点编号i，这里传入1，即从堆的顶点开始向下调整
        int t,flag = 0;//flag用来判断是否需要继续向下调整，t用来记录较小节点的编号
        //当传入节点i有儿子（其实至少有左儿子）并且有需要向下调整的时候循环就执行
        while (i*2 <= n &&  flag == 0){
            //首先判断传入i节点和它的左儿子的关系，并用t记录值较小的节点编号
            if(h[i] < h[i*2])
              t = i*2;
            else
              t = i;

            //如果节点i还存在右儿子，再对右儿子进行讨论
            if(i*2+1 <= n){
                //如果右儿子的值更小，则更新较小的节点编号
                if(h[t] < h[i*2+1])
                    t = i*2+1;
            }

            //如果发现最小节点编号不是自己，说明子节点中有比父节点更小的
            if(i != t){
                this.swap(t, i);//交换t编号节点与i编号节点的值
                i = t;//更新i为刚才与它交换节点值的节点编号，便于下次来继续向下调整
            }else
                flag = 1;
        }
    }
    //建立堆的函数
    private void create(){
        //n右移一位，相当于 n/2
        for (int i = n >> 1; i >= 0; i--) {
            siftdown(i);
        }
    }

    //堆排序
    private void heapsort(){
        while (n >= 0){
            swap(0, n);
            n--;
            siftdown(0);
        }
    }

    @Test
    public void test(){
        BinaryTreeNode1 binaryTreeNode = new BinaryTreeNode1();
        binaryTreeNode.create();//建堆
        binaryTreeNode.heapsort();//堆排序
        for (int i = 0; i < h.length; i++) {
            System.out.print(binaryTreeNode.h[i] + " ");//输出
        }
    }
}
