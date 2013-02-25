public class Subset {
    public static void main(String[] args) {

        if (args.length < 1) {
            throw new IllegalArgumentException("one argument is expected!");
        }

        int k = -1;
        k = Integer.parseInt(args[0]);
        RandomizedQueue<String> rq = new RandomizedQueue<String>();

        String[] userInput = StdIn.readStrings();

        for (int i = 0; i < userInput.length; i++) {
            rq.enqueue(userInput[i]);
        }

        if (k > userInput.length) {
            k = userInput.length;
        }
        for (int i = 0; i < k; i++) {
            System.out.println(rq.dequeue());
        }

    }
}
