import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import javax.swing.text.html.HTMLDocument.Iterator;

import org.junit.Ignore;
import org.junit.Test;

public class TestDeque {
    public Deque<String> globalDeque = new Deque<String>();

    @Test
    public void testEmpty() {
        Deque<String> deque = new Deque<String>();
        assertTrue(deque.isEmpty());
    }

    @Test
    public void testNonEmpty() {
        Deque<String> deque = new Deque<String>();
        deque.addFirst("test");
        assertFalse(deque.isEmpty());
    }

    @Test
    public void testSize() {
        Deque<String> deque = new Deque<String>();
        deque.addFirst("test");
        deque.addFirst("test2");
        deque.addFirst("test3");
        assertEquals(3, deque.size());
    }

    @Test
    public void testAddFirst() {
        Deque<String> deque = new Deque<String>();
        printDeque("before addFirst", deque);
        deque.addFirst("test");
        deque.addFirst("test2");
        deque.addFirst("test3");
        printDeque("after addFirst", deque);
        assertEquals(3, deque.size());
    }

    @Test
    public void testAddFirstRemoveLast() {
        Deque<String> deque = new Deque<String>();
        printDeque("before addFirst", deque);
        deque.addFirst("test");
        printDeque("after addFirst", deque);
        deque.removeLast();
        printDeque("after removeLast", deque);
        assertTrue(deque.isEmpty());
        assertEquals(0, deque.size());
    }

    @Test
    public void testAddLastRemoveLast() {
        Deque<String> deque = new Deque<String>();
        printDeque("before addFirst", deque);
        deque.addLast("test");
        printDeque("after addFirst", deque);
        deque.removeLast();
        printDeque("after removeLast", deque);
        assertTrue(deque.isEmpty());
        assertEquals(0, deque.size());
    }

    @Test
    public void testAddFirstAddLastRemoveFirstRemoveLast() {
        Deque<String> deque = new Deque<String>();
        for (int i = 0; i < 1000; i++) {            
            deque.addFirst("test");
            deque.addLast("test2");
            deque.removeFirst();
            deque.removeLast();
            assertTrue(deque.isEmpty());
        }
        assertEquals(0, deque.size());
    }

    @Test
    public void testIntermixedCalls() {
        Deque<String> deque = new Deque<String>();
        deque.addFirst("test");
        deque.addLast("test2");
        deque.removeFirst();
        deque.removeLast();
        printDeque("any nulls?", deque);
        assertTrue(deque.isEmpty());
        assertEquals(0, deque.size());
    }

    @Test
    public void testRemoveFirst() {
        Deque<String> deque = new Deque<String>();
        deque.addFirst("third");
        String secondItem = "second";
        deque.addFirst(secondItem);
        deque.addFirst("first");

        String first = null;
        printDeque("before removeFirst", deque);
        String removedItem = deque.removeFirst();
        printDeque("after removeFirst", deque);
        for (String item : deque) {
            first = item;
            break;
        }

        assertEquals("first", removedItem);
        assertEquals(secondItem, first);
        assertEquals(2, deque.size());
    }

    @Test
    public void testAddLast() {
        Deque<String> deque = new Deque<String>();
        printDeque("before addLast", deque);
        deque.addLast("last1");
        deque.addLast("last2");
        deque.addLast("last3");
        printDeque("after addLast", deque);
        assertEquals(3, deque.size());
    }

    @Test
    public void testRemoveLast() {
        Deque<String> deque = new Deque<String>();
        // deque.addFirst("first");
        deque.addLast("x");
        String preLast = "y";
        deque.addLast(preLast);
        deque.addLast("z");

        printDeque("before removeLast", deque);
        deque.removeLast();
        printDeque("after removeLast", deque);

        String last = null;
        for (String item : deque) {
            last = item;
        }

        assertEquals(preLast, last);
    }

    @Test
    public void testAddLastToEmptyDeque() {
        Deque<String> deque = new Deque<String>();
        deque.addLast("last");
        assertEquals(1, deque.size());
    }

    @Test(expected = NoSuchElementException.class)
    public void testRemoveFirstFromEmptyDeque() {
        Deque<String> deque = new Deque<String>();
        deque.removeFirst();
    }

    @Test(expected = NoSuchElementException.class)
    public void testRemoveLastFromEmptyDeque() {
        Deque<String> deque = new Deque<String>();
        deque.removeLast();
    }

    @Test
    public void testAddFirstAndThenRemoveLastToEmptyDeque() {
        Deque<String> deque = new Deque<String>();
        deque.addFirst("first");
        deque.removeLast();
        assertEquals(0, deque.size());
    }

    @Test
    public void testAddFirstAddLastAndThenRemoveLastToEmptyDeque() {
        Deque<String> deque = new Deque<String>();
        deque.addFirst("first");
        deque.addLast("last");
        deque.removeLast();
        assertEquals(1, deque.size());
    }

    @Test
    public void testAddLastAndRemoveFirst() {
        Deque<String> deque = new Deque<String>();
        deque.addLast("last");
        deque.removeFirst();
        assertEquals(0, deque.size());
    }

    @Test
    public void testIterator() {
        Deque<String> deque = new Deque<String>();
        deque.addLast("first");
        deque.removeFirst();
        // deque.addFirst("first");
        // deque.addFirst("first");
        // deque.addFirst("first");
        deque.addLast("last");
        deque.addLast("last");
        deque.addLast("last");
        // deque.removeLast();

        java.util.Iterator<String> it = deque.iterator();
        assertTrue(it != null);
        while (it.hasNext()) {
            System.out.println(it.next());

        }

    }

    @Test
    public void testComplex() {
        Deque<String> deque = new Deque<String>();
        assertEquals(0, deque.size());
        assertEquals("", dequeToString(deque));
        deque.addLast("0");
        deque.addFirst("1");
        deque.addLast("2");
        deque.addFirst("3");
        deque.addLast("4");
        deque.addLast("5");
        deque.addFirst("6");
        assertEquals(7, deque.size());
        assertEquals("6 3 1 0 2 4 5 ", dequeToString(deque));
        deque.removeFirst();
        deque.removeLast();
        deque.removeLast();
        deque.removeLast();
        deque.removeFirst();
        deque.removeFirst();
        deque.removeFirst();
        deque.addLast("7");
        deque.addFirst("8");
        assertEquals(2, deque.size());
        assertEquals("8 7 ", dequeToString(deque));
    }

    @Ignore
    public void testConcurrentIteration() {
        // TODO: test concurrent invocation in main function, JUint is not the
        // best place for such test
        TestThread t1 = new TestThread();
        TestThread t2 = new TestThread();
        t1.start();
        t2.start();
    }

    private String dequeToString(Deque<String> deque) {
        String result = "";
        for (String item : deque) {
            result += item + " ";
        }
        return result;
    }

    private void printDeque(String message, Deque<String> deque) {
        System.out.print(message + ": ");
        for (String item : deque) {
            System.out.print(item + " ");
        }
        System.out.println();
    }

    class TestThread extends Thread {
        public void run() {
            for (int i = 0; i < 50000; i++) {
                globalDeque.addLast(Integer.toString(i));
            }
            for (String item : globalDeque) {
                System.out.println(getId() + ": " + item);
            }
        }

    }

}
