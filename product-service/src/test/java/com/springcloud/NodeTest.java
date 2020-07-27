package com.springcloud;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @Description:
 * @Author: Sui, ChengBin
 * @Date: 2020/7/15
 **/
public class NodeTest {
    public static void main(String[] args) {
        List<Node> n = new ArrayList<>();
        n.add(new Node(5));
        n.add(new Node(6));
        List<Node> nList = new ArrayList<>();
        nList.add(new Node(3, n));
        nList.add(new Node(2));
        nList.add(new Node(4));
        Node root = new Node(1, nList);

        System.out.println(preorder(root));

    }

    public static List<Integer> preorder(Node root) {
        LinkedList<Node> stack = new LinkedList<>();
        LinkedList<Integer> output = new LinkedList<>();
        if (root == null) {
            return output;
        }

        stack.add(root);
        while (!stack.isEmpty()) {
            Node node = stack.pollLast();
            output.add(node.val);
            if (node.children!=null){
                Collections.reverse(node.children);
                for (Node item : node.children) {
                    stack.add(item);
                }
            }

        }
        return output;

    }
}


class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
};

