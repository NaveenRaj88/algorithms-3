import java.util.Iterator;
import java.util.NoSuchElementException;

/*
 * TODO:Your deque implementation should support each deque operation in constant worst-case time and
 * use space proportional to the number of items currently in the deque. 
 * Additionally, your iterator implementation should support the 
 * operations next() and hasNext() (plus construction) in constant worst-case time
 * and use a constant amount of extra space per iterator.
 */
public class Deque<Item> implements Iterable<Item> {

    //TODO: I will use linked list for the base of this data structure
    Item[] base;

    // construct an empty deque
    @SuppressWarnings("unchecked")
    public Deque() {
        Item[] base = (Item[]) new Object[1];
    }

    // is the deque empty?
    public boolean isEmpty() {
        return false;
    }

    // return the number of items on the deque
    public int size() {
        return 0;
    }

    // insert the item at the front
    public void addFirst(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }
    }

    // insert the item at the end
    public void addLast(Item item) {
    }

    // delete and return the item at the front
    public Item removeFirst() {
        if (base == null) {
            throw new NoSuchElementException();
        }
        return null;
    }

    // delete and return the item at the end
    public Item removeLast() {
        return null;
    }

    // return an iterator over items in order from front to end
    public Iterator<Item> iterator() {
        return null;
    }

    class DequeIterator implements java.util.Iterator<Item> {

        @Override
        public boolean hasNext() {
            // TODO Auto-generated method stub
            return false;
        }

        @Override
        public Item next() {
            if (base.length <= 0) {
                throw new NoSuchElementException();
            }
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();

        }

    }
}