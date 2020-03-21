package algorithm.tree;

import java.util.*;

/**
 * 二叉树遍历算法
 * @author zhaozhe
 * 创建：<code>createTree</code>
 * 先序遍历-递归: <code>preOrderRecur</code>
 * 中序遍历-递归: <code>midOrderRecur</code>
 * 后序遍历-递归: <code>postOrderRecur</code>
 * 先序遍历-迭代（深度优先遍历）: <code>preOrder</code>
 * 中序遍历-迭代: <code>midOrder</code>
 * 后序遍历-迭代: <code>postOrder</code>
 * 层次遍历（广度优先遍历）: <code>levelTraverse</code>
 * 层次遍历-自底向上遍历: <code>levelTraverseFromBottom2Top</code>
 * 层次遍历-遍历结果存储到二维数组中 <code>levelTraverseTo2DArray</code>
 */
public class BinaryTree {

    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<Integer>();
        list.add(1);
        list.add(2);
        list.add(21);
        list.add(0);
        list.add(0);
        list.add(4);
        list.add(7);
        list.add(0);
        list.add(0);
        list.add(6);
        list.add(0);
        list.add(0);
        list.add(3);
        list.add(0);
        list.add(5);
        BinaryTree bt = new BinaryTree();
        Node tree = bt.createTree(list);
        List<Integer> itList = bt.preOrderRecur(tree);
        System.out.println("===========preOrderRecur============");
        bt.print(itList);
        System.out.println("===========preOrder============");
        itList = bt.preOrder(tree);
        bt.print(itList);
        System.out.println("===========midOrder============");
        itList = bt.midOrder(tree);
        bt.print(itList);
        System.out.println("===========postOrder============");
        itList = bt.postOrder(tree);
        bt.print(itList);
        System.out.println("===========levelTraverse============");
        itList = bt.levelTraverse(tree);
        bt.print(itList);
        System.out.println("===========levelTraverse from bottom to top============");
        itList = bt.levelTraverseFromBottom2Top(tree);
        bt.print(itList);
        System.out.println("===========levelTraverse store to a 2d array============");
        List<List<Integer>> twoDimensionArray = bt.levelTraverseTo2DArray(tree);
        bt.print2D(twoDimensionArray);
    }

    private void print2D(List<List<Integer>> twoDimensionArray) {
        for (List<Integer> list : twoDimensionArray) {
            for (Integer data : list) {
                System.out.print(data + " ");
            }
            System.out.println();
        }
    }


    private void print(List<Integer> itList) {
        System.out.println("=======================");
        for (int i=0; i<itList.size(); i++) {
            System.out.println(itList.get(i));
        }
    }

    private Node createTree(LinkedList<Integer> list) {
        if (list.size() == 0) {
            return null;
        }
        Node root = new Node();
        root.data = list.removeFirst();
        if (root.data == 0) {
            return null;
        }
        root.left = createTree(list);
        root.right = createTree(list);
        return root;
    }
    
    private List<Integer> preOrderRecur(Node root) {
        List<Integer> subTree = new ArrayList<Integer>();
        if (root != null) {
            subTree.add(root.data);
            subTree.addAll(preOrderRecur(root.left));
            subTree.addAll(preOrderRecur(root.right));
        }
        return subTree;
    }
    
    private List<Integer> midOrderRecur(Node root) {
        List<Integer> subTree = new ArrayList<Integer>();
        if (root != null) {
            subTree.addAll(preOrderRecur(root.left));
            subTree.add(root.data);
            subTree.addAll(preOrderRecur(root.right));
        }
        return subTree;
    }
    
    private List<Integer> postOrderRecur(Node root) {
        List<Integer> subTree = new ArrayList<Integer>();
        if (root != null) {
            subTree.addAll(preOrderRecur(root.left));
            subTree.addAll(preOrderRecur(root.right));
            subTree.add(root.data);
        }
        return subTree;

    }

    private List<Integer> preOrder(Node root) {
        List<Integer> result = new ArrayList<Integer>();
        LinkedList<Node> stack = new LinkedList<Node>();
        stack.addLast(root);
        while (!stack.isEmpty()) {
            Node temp = stack.removeLast();
            result.add(temp.data);
            if (temp.right != null) {
                stack.addLast(temp.right);
            }
            if (temp.left != null) {
                stack.addLast(temp.left);
            }
        }

        return result;
    }

    private List<Integer> midOrder(Node root) {
        List<Integer> result = new ArrayList<Integer>();
        LinkedList<Node> stack = new LinkedList<Node>();
        do {
            while (root != null) {
                stack.addLast(root);
                root = root.left;
            }

            if (!stack.isEmpty()) {
                root = stack.removeLast();
                result.add(root.data);
                root = root.right;
            }
        } while (!stack.isEmpty() || root != null);
        return result;
    }

    private List<Integer> postOrder(Node root) {
        List<Integer> result = new ArrayList<Integer>();
        LinkedList<Node> stack = new LinkedList<Node>();

        stack.addLast(root);
        while (!stack.isEmpty()) {
            root = stack.removeLast();
            result.add(root.data);
            if (root.left != null) {
                stack.addLast(root.left);
            }
            if (root.right != null) {
                stack.addLast(root.right);
            }
        }

        Collections.reverse(result);

        return result;
    }

    /**
     * 层次遍历，从上到下，
     * 用队列实现，每出队列一个根节点的同时，加入左孩子和右孩子
     * @param root
     * @return
     */
    private List<Integer> levelTraverse(Node root) {
        List<Integer> result = new ArrayList<Integer>();
        Queue<Node> queue = new LinkedList<Node>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            Node temp = queue.poll();
            result.add(temp.data);
            if (temp.left != null) {
                queue.offer(temp.left);
            }
            if (temp.right != null) {
                queue.offer(temp.right);
            }
        }

        return result;
    }

    /**
     * 层次遍历，从下到上，从左到右
     * 用队列实现，每出队列一个根节点的同时，加入右孩子和左孩子
     * @param root
     * @return
     */
    private List<Integer> levelTraverseFromBottom2Top(Node root) {
        List<Integer> result = new ArrayList<Integer>();
        LinkedList<Node> queue = new LinkedList<Node>();
        queue.addLast(root);
        while (!queue.isEmpty()) {
            root = queue.removeFirst();
            if (root != null) {
                result.add(root.data);
                queue.addLast(root.right);
                queue.addLast(root.left);
            }
        }
        Collections.reverse(result);
        return result;
    }

    /**
     * 层次遍历，结果存入到二维数组中，树的每一层是数组的每一行
     * @param root
     * @return
     */
    private List<List<Integer>> levelTraverseTo2DArray(Node root) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        LinkedList<Node> queue = new LinkedList<Node>();
        queue.addLast(root);
        int currLevelSize = 1;
        while (!queue.isEmpty()) {
            int nextLevelSize = 0;
            List<Integer> levelResult = new ArrayList<Integer>();
            for (int i=0; i<currLevelSize; i++) {
                root = queue.removeFirst();
                levelResult.add(root.data);
                if (root.left != null) {
                    queue.addLast(root.left);
                    nextLevelSize++;
                }
                if (root.right != null) {
                    queue.addLast(root.right);
                    nextLevelSize++;
                }
            }
            result.add(levelResult);
            currLevelSize = nextLevelSize;
        }

        return result;
    }

}


class Node {
    Integer data;
    Node left;
    Node right;    
}
