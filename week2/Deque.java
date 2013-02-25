import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    // I will use linked list for the base of this data structure

    private int N; // size of the deque
    private Node first; // beginning of the deque

    private Node last; // ending of the deque

    // helper linked list class
    private class Node {
        private Item item;
        private Node next;
        private Node prev;
    }

    // construct an empty deque
    public Deque() {
        first = null;
        N = 0;
        last = null;
        // assert check();
    }

    // is the deque empty?
    public boolean isEmpty() {
        return first == null;
    }

    // return the number of items on the deque
    public int size() {
        return N;
    }

    // insert the item at the front
    public void addFirst(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }

        Node newFirst = new Node();
        newFirst.next = first;
        newFirst.prev = null;
        newFirst.item = item;
        if (first != null) {
            first.prev = newFirst;
        } else {
            // first == last case
            last = newFirst;
        }
        first = newFirst;
        newFirst = null;

        N++;
    }

    // insert the item at the end
    public void addLast(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }
        Node newLast = new Node();
        newLast.item = item;
        if (last != null) {
            last.next = newLast;
        }
        newLast.prev = last;
        last = newLast;
        newLast = null; // prevent loitering
        if (N == 0) {
            first = last;
        }
        N++;
    }

    // delete and return the item at the front
    public Item removeFirst() {
        if (isEmpty())
            throw new NoSuchElementException("Deque underflow");
        Item item = first.item; // save item to return
        if (first.next != null) {
            // more than 1 element is deque
            first = first.next; // delete first node
            first.prev.next = null;
            first.prev = null;
        } else {
            first = null;
            last = null;
        }
        N--;
        // assert check();
        return item; // return the saved item
    }

    // delete and return the item at the end
    public Item removeLast() {
        if (isEmpty())
            throw new NoSuchElementException("Deque overflow");
        Item item = last.item; // save item to return
        if (last.prev != null) {
            // last != first
            Node newLast = last.prev;
            last.prev = null;
            newLast.next = null;
            last = newLast;
            newLast = null;
        } else {
            // last == first
            last = null;
            first = null;

        }

        N--;
        // assert check();
        return item; // return the saved item
    }

    // return an iterator over items in order from front to end
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements java.util.Iterator<Item> {
        private Node current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            if (!hasNext())
                throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();

        }

    }

}