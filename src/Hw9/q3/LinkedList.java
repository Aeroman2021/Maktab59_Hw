package Hw9.q3;

public class LinkedList {



    private class Node{
        private int value;
        private Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    private Node first;
    private Node last;
    private int index ;

    public void addLast(LinkedList ls,int item) {
        Node node = new Node(item);

        if (isEmpty(ls))
            first = last = node;
        else {
            last.next = node;
            last = node;
        }
    }

    public void addFirst(LinkedList ls,int item) {
        Node node = new Node(item);
        if (isEmpty(ls))
            first = last = node;
        else {
            node.next = first;
            first = node;
        }
    }

    public void removeFirst(LinkedList ls) throws NoSuchFieldException {

        if (isEmpty(ls))
            throw new NoSuchFieldException();

        if (first == last)
            first = last = null;

        Node second = first.next;
        first.next = null;
        first = second;
    }

    public void removeLast(LinkedList ls) throws NoSuchFieldException {
        if (isEmpty(ls))
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

    public boolean contain(LinkedList list,int item) {
        return indexOf( list,item) != -1;
    }

    public int indexOf(LinkedList list,int item) {

        Node currentNode = list.first;
        while (currentNode != null) {
            if (currentNode.value == item)
                return index;
            currentNode = currentNode.next;
            index++;
        }
        return -1;
    }

    public void printLinkedList(LinkedList list){
        Node currentNode = list.first;
        while (currentNode != null){
            System.out.print(currentNode.value + " ");
            currentNode = currentNode.next;
        }
    }


    public boolean isEmpty(LinkedList list) {
        return list.first == null;
    }


}
