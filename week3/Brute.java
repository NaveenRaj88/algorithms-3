import java.awt.Color;
import java.util.Arrays;

public class Brute {
    public static void main(String[] args) {

        prepareStdDraw();

        String filename = args[0];
        In in = new In(filename);
        // StdOut.println(filename);
        int N = in.readInt();
        Point[] arr = new Point[N];
        // int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            int x = in.readInt();
            int y = in.readInt();
            arr[i] = new Point(x, y);
            arr[i].draw();
        }

        // System.out.println(Arrays.asList(arr));
        StdDraw.setPenColor(Color.BLUE);
        StdDraw.setPenRadius();

        for (int i = 0; i < N - 3; i++) {
            for (int j = i + 1; j < N - 2; j++) {
                for (int k = j + 1; k < N - 1; k++) {
                    for (int m = k + 1; m < N; m++) {

                        double slope1 = arr[i].slopeTo(arr[j]);
                        double slope2 = arr[i].slopeTo(arr[k]);
                        double slope3 = arr[i].slopeTo(arr[m]);

                        if (slope1 == slope2 && slope1 == slope3) {
                            Arrays.sort(arr);
                            StdOut.println(arr[i] + " -> " + arr[j] + " -> "
                                    + arr[k] + " -> " + arr[m]);
                            arr[i].drawTo(arr[j]);
                            arr[i].drawTo(arr[k]);
                            arr[i].drawTo(arr[m]);
                        }
                    }

                }
                // System.out.println();
            }
        }

        StdDraw.show(0);

    }

    private static void compare(int[] arr, int i, int j, int k, int l) {
        // System.out.println(i + "" + j + "" + k + "" + l + "");
        System.out.println("compare " + arr[i] + " " + arr[j] + " " + arr[k]
                + " " + arr[l]);

    }

    private static void prepareStdDraw() {
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        StdDraw.show(0);
        StdDraw.setPenColor(Color.RED);
        StdDraw.setPenRadius(0.005);
    }

}
