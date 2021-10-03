package Hw9.q3;

public class LinkedList {

    private class Node {

        public Node(int value) {
            this.value = value;
        }

        private int value;
        private Node next;
    }

    private Node first;
    private Node last;

    public void addLast(int item) {
        Node node = new Node(item);

        if (isEmpty())
            first = last = node;
        else {
            last.next = node;
            last = node;
        }
    }

    public void addFirst(int item) {
        Node node = new Node(item);
        if (isEmpty())
            first = last = node;
        else {
            node.next = first;
            first = node;
        }
    }

    public void removeFirst() throws NoSuchFieldException {

        if (isEmpty())
            throw new NoSuchFieldException();

        if (first == last)
            first = last = null;

        Node second = first.next;
        first.next = null;
        first = second;
    }

    public void removeLast() throws NoSuchFieldException {
        if (isEmpty())
            throw new NoSuchFieldException();

        if (first == last) {
            first = last = null;
            return;
        }

        Node previous = getPrevious(last);
        last = previous;
        last.next = null;
    }


    private Node getPrevious(Node node) {
        Node currentNode = first.next;
        while (currentNode != null) {
            if (currentNode.next == node)
                return currentNode;
            currentNode = currentNode.next;
        }
        return null;
    }

    public boolean contain(int item) {
        return indexOf(item) != -1;
    }

    public int indexOf(int item) {
        int index = 0;
        Node currenNode = first.next;
        while (currenNode != null) {
            if (currenNode.value == item)
                return index;
            currenNode = currenNode.next;
            index++;
        }
        return -1;
    }


    public boolean isEmpty() {
        return first == null;
    }


}
