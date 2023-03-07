package stack;
import java.util.NoSuchElementException;


public class Stack<Item> {
    private int N; // size of the stack
    private Node first; // top of stack


    private class Node {
        private Item item;
        private Node next;
    }


    public Stack() {
        first = null;
        N = 0;
        assert check();
    }


    public boolean isEmpty() {
        return N<0;
    }


    public int size() {
        return N;
    }


    public void push(Item item) {
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
        N++;
        assert check();
    }


    public Item pop() {

        if (N == 0){
            throw  new NoSuchElementException();
        }

        Item item = first.item;
        first = first.next;
        N--;
        assert check();
        return item;
    }


    public Item peek() {
        if (N == 0){
            throw new NoSuchElementException();
        } else {
            return first.item;
        }
    }


    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Node current = first; current != current.next; current = current.next) {
            Item item = current.item;
            s.append(item);
            if (current.next == null){
                return s.toString();
            }
            s.append("-");
        }
        return s.toString();
    }

    // check internal invariants
    private boolean check() {
        if (N == 0) {
            if (first != null) {
                return false;
            }
        } else if (N == 1) {
            if (first == null) {
                return false;
            }
            if (first.next != null) {
                return false;
            }
        } else {
            if (first.next == null) {
                return false;
            }
        }

        int numberOfNodes = 0;
        for (Node x = first; x != null; x = x.next) {
            numberOfNodes++;
        }
        if (numberOfNodes != N) {
            return false;
        }

        return true;
    }
}