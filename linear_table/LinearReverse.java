package algorithm.linear;

import java.util.LinkedList;

/**
 * 链表反转
 * @author zzhao
 * 1. 三个指针遍历实现
 * 2. 栈实现
 */
public class ListReverse {

    public static void main(String[] args) {
        Node node4 = new Node(4, null);
        Node node3 = new Node(3, node4);
        Node node2 = new Node(2, node3);
        Node head = new Node(1, node2);
        ListReverse lr = new ListReverse();
        lr.printList(head);
        head = lr.reverseList(head);
        lr.printList(head);
        head = lr.reverseListUsingStack(head);
        lr.printList(head);
    }

    private void printList(Node head) {
        while (head != null) {
            System.out.print(head.data + " ");
            head = head.next;
        }
        System.out.println();
    }

    /**
     * 反转链表
     * 用三个指针实现
     *   1 --- 2 --- 3 --- 4 --- null
     *   ^     ^     ^
     *   |     |     |
     *  pre  curr  next
     *  1. 循环遍历，每次将curr指向前一个节点pre
     *  2. pre立即切换到下一个节点curr
     *  3. curr立即切换到下一个节点next
     *  4. 终止条件是curr走到最后null，此时pre在最后一个节点上，如下所示，所以最后返回pre即可
     *   1 --- 2 --- 3 --- 4 --- null
     *                     ^     ^
     *                    |     |
     *                   pre  curr/next
     *  5. 注意：第一个节点要指向null
     * @param head
     * @return
     */
    public Node reverseList(Node head) {
        if (head == null) {
            return null;
        }
        Node pre = head;
        Node curr = head.next;
        Node next = null;
        pre.next = null;
        while (curr != null) {
            next = curr.next;
            curr.next = pre;
            pre = curr;
            curr = next;
        }
        return pre;
    }

    /**
     * 用栈实现链表反转
     * 注意最后一个出栈得节点next指向null
     * @param head
     * @return
     */
    public Node reverseListUsingStack(Node head) {
        LinkedList<Node> stack = new LinkedList<Node>();

        while (head != null) {
            stack.addLast(head);
            head = head.next;
        }

        if (!stack.isEmpty()) {
            head = stack.removeLast();
        }
        Node preTemp = head;
        while (!stack.isEmpty()) {
            Node temp = stack.removeLast();
            preTemp.next = temp;
            preTemp = temp;
        }
        preTemp.next = null;

        return head;
    }

}
