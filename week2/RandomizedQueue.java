import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    // I will use an array as a base structure because
    // there will be access to elements in the middle of queue

    private Item[] a; // array of items
    private int tail; // number of elements in a queue
    private int head;

    // construct an empty randomized queue
    public RandomizedQueue() {
        // System.out.println("RandomizedQueue() in");
        a = (Item[]) new Object[2]; // initial length is 2
    }

    // is the queue empty?
    public boolean isEmpty() {
        return tail - head == 0;
    }

    // return the number of items on the queue
    public int size() {
        return tail - head;
    }

    // add the item
    public void enqueue(Item item) {
        // System.out.println("enqueue() in, item = " + item);
        if (item == null) {
            throw new NullPointerException();
        }
        if (tail >= a.length)
            resize(2 * a.length); // double size of array if necessary
        a[tail++] = item; // add item
        // trace();
    }

    // delete and return a random item
    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        // simple queue
        // Item item = a[head];

        // randomized queue

        int randomIndex = StdRandom.uniform(head, tail);

        // swap found random element with head, to remove head with correct
        // value and to keep former head element
        Item temp = a[head];
        a[head] = a[randomIndex];
        a[randomIndex] = temp;

        // now head contains randomly chosen item
        Item item = a[head];
        a[head] = null; // to avoid loitering
        head++;

        // shrink size of array if necessary
        if (size() > 0 && size() == a.length / 4) {
            resize(a.length / 2);
        }
        // System.out.println("dequeue() in, item = " + item);
        // trace();
        return item;
    }

    // return (but do not delete) a random item
    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return a[StdRandom.uniform(head, tail)];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    // resize the underlying array holding the elements
    private void resize(int capacity) {
        // System.out.println("resize() in, capacity = " + capacity);
        assert capacity >= tail;
        Item[] temp = (Item[]) new Object[capacity];
        for (int i = 0; i < tail - head; i++) {
            temp[i] = a[head + i];
        }
        // System.out.println("tail - head = " + (tail - head));
        tail = tail - head;
        head = 0;
        a = temp;
    }

    private void trace(String message) {
        System.out.println(message + ": " + "head=" + head + ", tail=" + tail
                + ", size=" + size() + ", length=" + a.length + "\n");
    }

    private class RandomizedQueueIterator implements java.util.Iterator<Item> {

        private int i;
        private Item[] localArray;

        public RandomizedQueueIterator() {
            i = head;
            localArray = (Item[]) new Object[a.length];
            
            //copying is needed to have independent iterators
            //linear time - performance is OK
            System.arraycopy(a, 0, localArray, 0, a.length);
            if (tail > 1) {
                StdRandom.shuffle(localArray, head, tail - 1);
                // StdRandom.shuffle(a, head, tail - 1);
            }
        }

        @Override
        public boolean hasNext() {
            // System.out.println("RandomizedQueueIterator.hasNext() in");
            // TODO Auto-generated method stub
            return i < tail;
        }

        @Override
        public Item next() {
            // System.out.println("RandomizedQueueIterator.next() in");
            if (!hasNext())
                throw new NoSuchElementException();
            return localArray[i++];
            // return a[i++];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();

        }

    }
}