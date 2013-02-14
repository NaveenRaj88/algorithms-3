public class Percolation {

    /**
     * all sites of a grid
     */
    private boolean[][] sites;

    /**
     * helps to keep count of opened and connected sites, core of this algorithm
     */
    private WeightedQuickUnionUF uf;

    /**
     * top virtual node, helps to determine if site is full
     */
    private int topRoot = -1;
    /**
     * bottom virtual node, helps to determine if system percolates
     */
    private int bottomRoot = -1;

    /**
     * grid size
     */
    private int N;

    /**
     * creates N-by-N grid, with all sites blocked
     * 
     * @param N
     *            - size of the grid
     */
    public Percolation(int N) {
        this.N = N;

        // inits with the value beyond sites of the grid, to be able to put it
        // into uf
        topRoot = N * N + 1;
        bottomRoot = N * N + 2;

        // +1 for topRoot and +1 for bottomRoot
        // +2N for extra row and column, due to indices are started from 1
        uf = new WeightedQuickUnionUF(N * N + 2 + 1);

        sites = new boolean[N][N];

        // init sites
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                sites[i - 1][j - 1] = false;
            }
        }

        // // connect top sites to the topRoot
        // for (int i = 1; i <= N; i++) {
        // // StdOut.print(xyTo1D(1, i)+" ");
        // uf.union(topRoot, xyTo1D(1, i));
        // }
        // // connect all bottom sites to the bottomRoot
        // for (int i = 1; i <= N; i++) {
        // uf.union(bottomRoot, xyTo1D(N, i));
        // }

    }

    /**
     * open site (row i, column j) if it is not already
     * 
     * @param i
     *            - site row
     * @param j
     *            - site column
     */
    public void open(int i, int j) {
        validateIndices(i, j);
        int currentSite = xyTo1D(i, j);

        if (!sites[i - 1][j - 1]) {
            StdOut.println("(" + i + "," + j + ") is opened");
            sites[i - 1][j - 1] = true;

            // check that neighbors exist and in bounds
            if (j - 1 > 0) {
                testNeighbor(currentSite, i, j - 1);
            }

            if (j + 1 <= N) {
                testNeighbor(currentSite, i, j + 1);
            }

            if (i - 1 > 0) {
                testNeighbor(currentSite, i - 1, j);
            }

            if (i + 1 <= N) {
                testNeighbor(currentSite, i + 1, j);
            }

            // to eliminate back washes connect bottom sites to a virtual bottom
            // root only when they are full
            if (i == N) {
                StdOut.println("(" + i + "," + j
                        + ") is connected to the bottom root");
                StdOut.println("isFull = "+isFull(i, j));
               // if (isFull(i, j)) {
//                if(isOpen(i, j)){
//                    // TODO: nothing is connected to the bottom due to isFull
//                    // restriction/condition - investigate
//                    uf.union(xyTo1D(i, j), bottomRoot);
//                }
                // connect only opened top items to the virtual top root
            } else if (i == 1) {
                StdOut.println("(" + i + "," + j
                        + ") is connected to the top root");
                uf.union(xyTo1D(i, j), topRoot);
            }
        }
    }

    /**
     * @param i
     *            site row
     * @param j
     *            site column
     * @return true if site is open
     */
    public boolean isOpen(int i, int j) {
        validateIndices(i, j);
        return sites[i - 1][j - 1];
    }

    /**
     * @param i
     *            site row
     * @param j
     *            site column
     * @returns true if site is full, tree of opened sites is connected to the
     *          top
     */
    public boolean isFull(int i, int j) {
        return uf.connected(xyTo1D(i, j), topRoot) && isOpen(i, j);
    }

    /**
     * @returns true if system percolates
     */
    public boolean percolates() {
        StdOut.println("4.1->3.1 " + uf.connected(xyTo1D(4, 1), xyTo1D(3, 1)));
        StdOut.println("2.1->3.1 " + uf.connected(xyTo1D(2, 1), xyTo1D(3, 1)));
        StdOut.println("2.1->1.1 " + uf.connected(xyTo1D(2, 1), xyTo1D(1, 1)));
        StdOut.println("top->1.1 " + uf.connected(topRoot, xyTo1D(1, 1)));
        StdOut.println("bottom->4.1 " + uf.connected(bottomRoot, xyTo1D(4, 1)));
        StdOut.println("bottom->4.4 " + uf.connected(bottomRoot, xyTo1D(4, 4)));
        StdOut.println("percolates? " + uf.connected(topRoot, bottomRoot));
        return uf.connected(topRoot, bottomRoot);
        // return isFull(bottomRoot);
    }

    /**
     * test neighbors of the site and if they are opened connects to them
     * 
     * @param site
     *            - the site which neighbors are tested
     * @param neighborRow
     *            - neighbor row
     * @param neighborColumn
     *            - neighbor column
     */
    private void testNeighbor(int site, int neighborRow, int neighborColumn) {
        int neighbor = xyTo1D(neighborRow, neighborColumn);
        if (!uf.connected(site, neighbor)
                && isOpen(neighborRow, neighborColumn)) {
            uf.union(site, neighbor);
        }
    }

    /**
     * validates if indices are in allowed range otherwise
     * ArrayIndexOutOfBoundsException is thrown
     * 
     * @param i
     *            - site row
     * @param j
     *            - site column
     */
    private void validateIndices(int i, int j) {
        if (i <= 0 || j <= 0 || i > N || j > N)
            throw new ArrayIndexOutOfBoundsException("index is out of bounds");
    }

    /**
     * maps grid indices to 1 dimension, to put result into uf
     * 
     * @param i
     * @param j
     * @return
     */
    private int xyTo1D(int i, int j) {
        validateIndices(i, j);
        return N * (i - 1) + j;
    }

    /**
     * helps to debug and see which sites are opened
     * 
     */
    private void test() {
        StdOut.println("Percolation.test() in");
        StdOut.println(uf.connected(xyTo1D(1, 1), xyTo1D(5, 1)));
        StdOut.println("isFull = " + isFull(5, 1));
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (j - 1 > 0) {
                    if (uf.connected(xyTo1D(i, j), xyTo1D(i, j - 1))
                            && isOpen(i, j)) {
                        StdOut.print("l");
                    }
                }

                if (j + 1 <= N) {
                    if (uf.connected(xyTo1D(i, j), xyTo1D(i, j + 1))
                            && isOpen(i, j)) {
                        StdOut.print("r");
                    }
                }

                if (i - 1 > 0) {
                    if (uf.connected(xyTo1D(i, j), xyTo1D(i - 1, j))
                            && isOpen(i, j)) {
                        StdOut.print("t");
                    }
                }

                if (i + 1 <= N) {
                    if (uf.connected(xyTo1D(i, j), xyTo1D(i + 1, j))
                            && isOpen(i, j)) {
                        StdOut.print("b");
                    }
                }

                if (isOpen(i, j)) {
                    StdOut.print("*");
                }
                StdOut.print("(" + i + "," + j + ")");
                StdOut.print(xyTo1D(i, j) + "    ");
            }
            StdOut.println();
        }
    }

}
