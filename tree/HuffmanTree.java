package algorithm.tree;

import java.util.Collections;
import java.util.LinkedList;

public class HuffmanTree {

    public static void main(String[] args) {
        HuffmanTree ht = new HuffmanTree();
        WeightedNode root = ht.createHuffmanTree();
        ht.levelTraverse(root);
    }

    private WeightedNode createHuffmanTree() {
        // 构建有序链表
        WeightedNode n1 = new WeightedNode('a', 12);
        WeightedNode n2 = new WeightedNode('b', 6);
        WeightedNode n3 = new WeightedNode('c', 3);
        WeightedNode n4 = new WeightedNode('d', 22);
        WeightedNode n5 = new WeightedNode('e', 6);
        WeightedNode n6 = new WeightedNode('f', 2);
        WeightedNode n7 = new WeightedNode('g', 1);
        LinkedList<WeightedNode> list = new LinkedList<WeightedNode>();
        list.add(n1);
        list.add(n2);
        list.add(n3);
        list.add(n4);
        list.add(n5);
        list.add(n6);
        list.add(n7);
        // 对list从小到大排序
        Collections.sort(list);

        // 创建哈夫曼树
        // 基本思路是：
        // 1. 维护一个有序列表，每次循环重新排序
        // 2. 每次循环，取两个最小的节点，在上次循环产生的子树基础上，生成新的根节点
        // 3. 循环终止条件是list里面节点数量大于1，事实上，list节点数量最终不会为1
        WeightedNode root = null;
        while (list.size() > 1) {
            WeightedNode wn1 = list.removeFirst();
            WeightedNode wn2 = list.removeFirst();
            root = new WeightedNode(' ', wn1.getWeight() + wn2.getWeight());
            root.left = wn1;
            root.right = wn2;
            this.addWeightNodeToSortedList(list, root);
        }

        return root;
    }

    // 使用插入排序
    private void addWeightNodeToSortedList(LinkedList<WeightedNode> sortedList, WeightedNode weightedNode) {
        int length = sortedList.size();
        sortedList.addLast(weightedNode); // 加入list最后，当下面循环比较时，发现应该插入在List末尾，就不用再判断是不是末尾了
        for (int i=0; i<length; i++) {
            if (weightedNode.getWeight() < sortedList.get(i).getWeight()) {
                sortedList.add(i, weightedNode);
                sortedList.removeLast();
                break;
            }
        }

    }

    // 层次遍历，queue实现
    private void levelTraverse(WeightedNode root) {
        LinkedList<WeightedNode> queue = new LinkedList<WeightedNode>();
        queue.addLast(root);
        while (!queue.isEmpty()) {
            root = queue.removeFirst();
            System.out.println(root.getWeight());
            if (root.getLeft() != null) {
                queue.addLast(root.getLeft());
            }
            if (root.getRight() != null) {
                queue.addLast(root.getRight());
            }
        }
    }
}

class WeightedNode implements Comparable<WeightedNode> {
    char c;
    int weight;
    WeightedNode left;
    WeightedNode right;

    public WeightedNode(char c, int weight) {
        this.c = c;
        this.weight = weight;
    }

    public int compareTo(WeightedNode o) {
        return this.getWeight() - o.getWeight();
    }

    public char getC() {
        return c;
    }

    public void setC(char c) {
        this.c = c;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public WeightedNode getLeft() {
        return left;
    }

    public void setLeft(WeightedNode left) {
        this.left = left;
    }

    public WeightedNode getRight() {
        return right;
    }

    public void setRight(WeightedNode right) {
        this.right = right;
    }
}

