public class Subset {
    public static void main(String[] args) {
        int k = -1;
        if (args.length > 0) {
            k = Integer.parseInt(args[0]);
        }
        RandomizedQueue<String> rq = new RandomizedQueue<String>();
        for (int i = 0; i < k; i++) {
            String temp = StdIn.readString();
            StdOut.println(temp);            
            rq.enqueue(temp);            
        }
        //NPE - because iterator is not implemented
        for (String item : rq) {
            StdOut.println(item);
        }
    }
}
