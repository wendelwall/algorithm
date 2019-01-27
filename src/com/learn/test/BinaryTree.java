package com.learn.test;

import org.junit.Test;

import java.util.Stack;

/**
 * @author ：sunrise
 * @description ：@desc:二叉树(递归与非递归方式实现)
 * @copyright ：	Copyright 2019 yowits Corporation. All rights reserved.
 * @create ：2019/1/27 20:52
 */
public class BinaryTree {
    private static Node rootNode;//根节点

    /**
     * 构建树
     * @param node
     * @desc:
     */
    public void addNode(Node node) {
        if (rootNode == null) {
            rootNode = new Node();
            rootNode.setValue(node.getValue());
        } else {
            int rootValue = rootNode.getValue();
            if (node.getValue() < rootValue) {
                addChildNode(rootNode, node, 1);
            } else {
                addChildNode(rootNode, node, 2);
            }
        }
    }

    public void addChildNode(Node rootNode, Node node, int flag) { //flag: 1:左边节点;2:右边节点
        Node nextNode = null;
        if (flag == 1) {
            nextNode = rootNode.getLeftNode();
        } else {
            nextNode = rootNode.getRightNode();
        }
        if (nextNode == null) {
            if (flag == 1) {
                rootNode.setLeftNode(node);
            } else {
                rootNode.setRightNode(node);
            }
        } else {
            if (node.getValue() < nextNode.getValue()) {
                addChildNode(nextNode, node, 1);
            } else {
                addChildNode(nextNode, node, 2);
            }
        }
    }

    /**
     * 前序遍历(递归)
     * @param rootNode
     * @desc:遍历顺序:根节点----左子树----右子树
     */
    public void preOrder(Node rootNode) {
        if (rootNode != null) {
            System.out.print(rootNode.getValue() + ",");
            preOrder(rootNode.getLeftNode());
            preOrder(rootNode.getRightNode());
        }
    }

    /**
     * 前序遍历(非递归实现1)
     * @param node
     * @desc:遍历顺序:根节点----左子树----右子树
     */
    public void preOrderWithNonRecursive1(Node node) {
        Stack<Node> stack = new Stack();
        while (node != null || stack.size() > 0) {
            while (node != null) {
                System.out.print(node.getValue() + ",");
                stack.push(node);
                node = node.getLeftNode();
            }
            if (stack.size() > 0) {
                node = stack.pop();
                node = node.getRightNode();
            }
        }
    }

    /**
     * 前序遍历(非递归实现2)
     * @param node
     * @desc:遍历顺序:根节点----左子树----右子树
     */
    public void preOrderWithNonRecursive2(Node node) {
        Stack<Node> stack = new Stack();
        while (node != null || stack.size() > 0) {
            //遍历左子树
            while (node != null) {
                System.out.print(node.getValue() + ",");
                stack.push(node);
                node = node.getLeftNode();
            }
            //遍历右子树
            Node rightNode = null;
            while (stack.size() > 0) {
                node = stack.pop();
                rightNode = node.getRightNode();
                if (rightNode != null) {
                    System.out.print(rightNode.getValue() + ",");
                    if (rightNode.getRightNode() != null) {
                        stack.push(rightNode);
                    }
                    node = rightNode.getLeftNode();
                    break;
                }
            }
        }
    }

    /**
     * 中序遍历(递归)
     * @param node
     * @desc:遍历顺序:左子树----根节点----右子树
     */
    public void midOrder(Node node) {
        if (node != null) {
            midOrder(node.getLeftNode());
            System.out.print(node.getValue() + ",");
            midOrder(node.getRightNode());
        }
    }

    /**
     * 中序遍历(非递归实现)
     * @param node
     * @desc:左子树----根节点----右子树
     */
    public void midOrderWithNonRecursive(Node node) {
        Stack<Node> stack = new Stack();
        while (node != null || stack.size() > 0) {
            while (node != null) {
                stack.push(node);
                node = node.getLeftNode();
            }
            if (stack.size() > 0) {
                node = stack.pop();
                System.out.print(node.getValue() + ",");
                node = node.getRightNode();
            }
        }
    }

    /**
     * 后序遍历(递归)
     * @param node
     * @desc:左子树----右子树----根节点
     */
    public void lastOrder(Node node) {
        if (node != null) {
            lastOrder(node.getLeftNode());
            lastOrder(node.getRightNode());
            System.out.print(node.getValue() + ",");
        }
    }

    /**
     * 后序遍历(非递归实现)
     * @param node
     * @desc:左子树----右子树----根节点
     */
    public void lastOrderWithNonRecursive1(Node node) {
        Stack<Node> stack = new Stack();
        Stack<Node> tempStack = new Stack();//存放已经遍历过且含有右子树的节点
        Node tempNode = null;
        while (node != null || stack.size() > 0) {
            //遍历左子树
            while (node != null) {
                stack.push(node);
                node = node.getLeftNode();
            }
            if (stack.size() > 0) {
                node = stack.peek(); //获取一个节点
                if (tempStack.size() > 0) {
                    tempNode = tempStack.peek();
                    if (node == tempNode) {//说明此节点右子树已经遍历,下面处理时直接输出结果,不再遍历其右节点
                        tempStack.pop();
                    }
                }
                if (node.getRightNode() == null || node == tempNode) { //若无右子树或此节点已经遍历过,则输出根节点结果,否则继续遍历右子树
                    System.out.print(node.getValue() + ",");
                    stack.pop();
                    node = null;
                } else { //若有右子树,然后继续遍历右子树
                    tempStack.push(node);//将此节点放入栈中,目的是当再次遍历到此节点时(说明此节点右子树已经遍历),直接输出结果,不再遍历其右节点
                    node = node.getRightNode();
                }
            }
        }
    }

    /**
     * 后序遍历(非递归实现)
     * @param node
     * @desc:左子树----右子树----根节点
     */
    public void lastOrderWithNonRecursive2(Node node) {
        Node tempNode = null;//存储最新输出过结果的节点,当一个节点的右节点等于tempNode,说明此节点已经遍历过(但未输出结果),直接输出结果
        Stack<Node> stack = new Stack();
        while (node != null || stack.size() > 0) {
            //遍历左子树
            while (node != null) {
                stack.push(node);
                node = node.getLeftNode();
            }
            if (stack.size() > 0) {
                node = stack.peek().getRightNode(); //获取栈顶节点的右节点,若无右子树或此节点已经遍历过(但未输出结果),则输出结果;否则,若有右子树,则继续遍历右子树
                if (node == null || tempNode == node) { //若无右子树或此节点已经遍历过(但未输出结果),则输出根节点结果;否则继续遍历右子树
                    node = stack.pop();
                    System.out.print(node.getValue() + ",");
                    tempNode = node;//将当前节点保存起来
                    node = null;
                }
            }
        }
    }

    /**
     * 在二叉树中查找节点是否存在
     * @param value
     * @return
     * @desc:
     */
    public boolean searchInBinaryTree(int value) {
        boolean flag = false;
        while (rootNode != null) {
            if (value == rootNode.getValue()) {
                flag = true;
                break;
            } else if (value < rootNode.getValue()) {//遍历左子树
                rootNode = rootNode.getLeftNode();
            } else {//遍历右子树
                rootNode = rootNode.getRightNode();
            }
        }
        return flag;
    }

    /**
     * 初始化一棵树
     */
    public void buildBinaryTree() {
        int[] a = {10,7,16,8,5,3,1,6,19,12,9};
        for (int i = 0; i < a.length; i++) {
            this.addNode(new Node(a[i]));
        }
    }

    /**
     * 树结构定义
     *
     * @desc:
     */
    class Node {
        private int value;
        private Node leftNode;
        private Node rightNode;

        public Node() {
        }

        public Node(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public Node getLeftNode() {
            return leftNode;
        }

        public void setLeftNode(Node leftNode) {
            this.leftNode = leftNode;
        }

        public Node getRightNode() {
            return rightNode;
        }

        public void setRightNode(Node rightNode) {
            this.rightNode = rightNode;
        }
    }

    /**
     * @desc:
     */
    @Test
    public void test() {
        BinaryTree test = new BinaryTree();
        //*************构建树**********************
        test.buildBinaryTree();
        //*************前序遍历(递归算法)*************
        System.out.println("前序遍历结果:");
        test.preOrder(rootNode);
        System.out.println();
        //*************前序遍历(非递归算法)*************
        test.preOrderWithNonRecursive1(rootNode);
        System.out.println();
        test.preOrderWithNonRecursive2(rootNode);
        System.out.println();


        //*************中序遍历(递归算法)*************
        System.out.println();
        System.out.println("中序遍历结果:");
        test.midOrder(rootNode);
        System.out.println();
        //*************中序遍历(非递归算法)*************
        test.midOrderWithNonRecursive(rootNode);
        System.out.println();


        //*************后序遍历(递归算法)*************
        System.out.println();
        System.out.println("后序遍历结果:");
        test.lastOrder(rootNode);
        System.out.println();
        //*************后序遍历(非递归算法)*************
        test.lastOrderWithNonRecursive1(rootNode);
        System.out.println();
        test.lastOrderWithNonRecursive2(rootNode);
        System.out.println();


        //*************在二叉树中查找节点是否存在**********
        System.out.println();
        System.out.println("在二叉树中查找节点是否存在:");
        System.out.println("查找节点【7】是否存在:" + test.searchInBinaryTree(7));
        System.out.println("查找节点【9】是否存在:" + test.searchInBinaryTree(9));
        System.out.println("查找节点【27】是否存在:" + test.searchInBinaryTree(27));
    }
}
