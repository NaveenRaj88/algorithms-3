public class PercolationStats {

    /**
     * results of experiments are stored here
     */
    private double[] results;

    /**
     * count of experiments
     */
    private int T = -1;

    //
    /**
     * perform T independent computational experiments on an N-by-N grid
     * 
     * @param N
     *            size of a grid
     * 
     * @param T
     *            count of experiments
     * 
     */
    public PercolationStats(int N, int T) {
        if (N <= 0 || T <= 0)
            throw new IllegalArgumentException("N ant T should be above zero");
        this.T = T;
        results = new double[T];
        for (int j = 0; j < T; j++) {
            Percolation p = new Percolation(N);
            double closed = N * N;
            double all = N * N;
            while (!p.percolates()) {
                int randomRow = 1 + StdRandom.uniform(N);
                int randomColumn = 1 + StdRandom.uniform(N);
                if (!p.isOpen(randomRow, randomColumn)) {
                    p.open(randomRow, randomColumn);
                    closed--;
                }
            }
            results[j] = (all - closed) / all;
        }
    }

    /**
     * @returns sample mean of percolation threshold
     */
    public double mean() {
        return StdStats.mean(results);
    }

    /**
     * @returns sample standard deviation of percolation threshold
     */
    public double stddev() {
        if (T == 1) {
            return Double.NaN;
        } else {
            return StdStats.stddev(results);
        }
    }

    /**
     * @returns lower bound of the 95% confidence interval
     */
    public double confidenceLo() {
        return mean() - (1.96 * stddev() / Math.sqrt(T));
    }

    /**
     * @returns upper bound of the 95% confidence interval
     */
    public double confidenceHi() {
        return mean() + (1.96 * stddev() / Math.sqrt(T));
    }

    /**
     * @param args
     *            must have 2 parameters: N - size of a grid and T - amount of
     *            experiments
     */
    public static void main(String[] args) {
        if (args.length == 2) {
            int N = Integer.parseInt(args[0]);
            int T = Integer.parseInt(args[1]);

            PercolationStats ps = new PercolationStats(N, T);
            StdOut.println("mean = " + ps.mean());
            StdOut.println("stddev = " + ps.stddev());
            StdOut.println("95% confidence interval = " + ps.confidenceLo()
                    + ", " + ps.confidenceHi());
        } else {
            StdOut.println("2 command line arguments are expected");
            StdOut.println("N - size of a grid and T - amount of experiments");
        }
        
//        Percolation ps = new Percolation(1);
//        //ps.open(1, 1);
//        //System.out.println(ps.isFull(1, 1));
//        System.out.println(ps.percolates());
        
    }
}
