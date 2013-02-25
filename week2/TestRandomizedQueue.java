import static org.junit.Assert.*;

import org.junit.Test;

public class TestRandomizedQueue {

    @Test
    public void testEmpty() {
        RandomizedQueue<String> rq = new RandomizedQueue<String>();
        assertTrue(rq.isEmpty());
    }

    @Test
    public void testNonEmpty() {
        RandomizedQueue<String> rq = new RandomizedQueue<String>();
        rq.enqueue("test");
        assertFalse(rq.isEmpty());
    }

    @Test
    public void testSize() {
        RandomizedQueue<String> rq = new RandomizedQueue<String>();
        assertEquals(0, rq.size());
    }

    @Test
    public void testEnqueue() {
        RandomizedQueue<String> rq = new RandomizedQueue<String>();
        print("before", rq);
        assertEquals(0, rq.size());
        rq.enqueue("test");
        rq.enqueue("test2");
        rq.enqueue("test3");
        print("after", rq);
        assertEquals(3, rq.size());
    }

    @Test
    public void testDequeue() {
        RandomizedQueue<String> rq = new RandomizedQueue<String>();
        print("rq", rq);
        rq.enqueue("test ");
        rq.enqueue("test2 ");
        rq.enqueue("test3 ");
        // rq.enqueue("test4 ");
        // rq.enqueue("test5 ");
        print("rq", rq);
        rq.dequeue();
        rq.dequeue();
        print("rq", rq);
        // rq.dequeue();
        // rq.dequeue();
        // rq.dequeue();
        rq.enqueue("test6 ");
        print("rq", rq);
        rq.enqueue("test7 ");
        print("rq", rq);
        System.out.println("result = " + rq.dequeue());
        print("rq", rq);
        for (int i = 0; i < 20; i++) {
            rq.enqueue("qqqq ");
        }
        print("rq", rq);
        // assertEquals(0, rq.size());
    }

    @Test
    public void testEnqueueToEmpty() {
        RandomizedQueue<String> rq = new RandomizedQueue<String>();
        assertEquals("", toString(rq));
        assertEquals(0, rq.size());

        rq.enqueue("test");
        assertEquals("test ", toString(rq));
        assertEquals(1, rq.size());

        String result = rq.dequeue();
        assertEquals("", toString(rq));
        assertEquals(0, rq.size());
        assertEquals("test", result);

        rq.enqueue("test2");
        assertEquals("test2 ", toString(rq));
        assertEquals(1, rq.size());

        // array is resized from 2 to 4, head is moved to 0
        rq.enqueue("test3");
        assertEquals("test2 test3 ", toString(rq));
        assertEquals(2, rq.size());

        String result2 = rq.dequeue();
        assertEquals("test3 ", toString(rq));
        assertEquals(1, rq.size());
        assertEquals("test2", result2);

        rq.enqueue("test4");
        assertEquals("test3 test4 ", toString(rq));
        assertEquals(2, rq.size());

        for (int i = 0; i < 3; i++) {
            rq.enqueue(Integer.toString(i));
        }
        assertEquals("test3 test4 0 1 2 ", toString(rq));
        assertEquals(5, rq.size());
    }

    @Test
    public void testDequeueResizing() {
        RandomizedQueue<String> rq = new RandomizedQueue<String>();
        for (int i = 0; i < 20; i++) {
            rq.enqueue(Integer.toString(i));
        }
        print("populated rq", rq);

        for (int i = 0; i < 20; i++) {
            System.out.println(i);
            rq.dequeue();
            print("rq", rq);
        }
    }

    @Test
    public void testRandom() {
        int head = 5;
        int tail = 10;
        for (int i = 0; i < 20; i++) {
            System.out.println(StdRandom.uniform(head, tail));

        }
    }

    @Test
    public void testRandomOrder() {
        RandomizedQueue<String> rq = new RandomizedQueue<String>();
        for (int i = 0; i < 20; i++) {
            rq.enqueue(Integer.toString(i));
        }
        for (int i = 0; i < 20; i++) {
            System.out.println(rq.sample() + " ");
        }
        assertEquals(20, rq.size());
        for (int i = 0; i < 20; i++) {
            rq.dequeue();
        }
        assertEquals(0, rq.size());
    }

    @Test
    public void testRandomIterator() {
        RandomizedQueue<String> rq = new RandomizedQueue<String>();
        for (int i = 0; i < 10; i++) {
            rq.enqueue(Integer.toString(i));
        }
        for (String item : rq) {

        }
        System.out.println();
        for (String item : rq) {

        }
    }

    @Test
    public void testRandomDequeue() {
        RandomizedQueue<String> rq = new RandomizedQueue<String>();

        for (int i = 0; i < 10; i++) {
            rq.enqueue(Integer.toString(i));
        }

        for (String item : rq) {

        }

        for (int i = 0; i < 10; i++) {
            System.out.println(rq.dequeue());
        }
    }

    @Test
    public void test2independentIterators() {
        RandomizedQueue<String> rq = new RandomizedQueue<String>();
        for (int i = 0; i < 10; i++) {
            rq.enqueue(Integer.toString(i));
        }
        java.util.Iterator<String> it1 = rq.iterator();
        java.util.Iterator<String> it2 = rq.iterator();
        while (it1.hasNext()) {
            System.out.print(it1.next() + " ");

        }
        System.out.println();
        while (it2.hasNext()) {
            System.out.print(it2.next() + " ");

        }
    }

    private String toString(RandomizedQueue<String> rq) {
        System.out.println("toString() in");
        String result = "";
        for (String item : rq) {
            System.out.println("item=" + item);
            result += item + " ";
        }
        System.out.println("toString() out, result=" + result);
        return result;
    }

    private void print(String message, RandomizedQueue<String> rq) {
        System.out.print(message + ": ");
        for (String item : rq) {
            System.out.print(item + " ");
        }
        System.out.println("\n");
    }

}
