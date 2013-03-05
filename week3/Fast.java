import java.awt.Color;
import java.util.Arrays;

public class Fast {
    public static void main(String[] args) {

        prepareStdDraw();
        String filename = args[0];
        In in = new In(filename);
        // StdOut.println(filename);
        int N = in.readInt();
        Point[] p = new Point[N];
        for (int i = 0; i < N; i++) {
            int x = in.readInt();
            int y = in.readInt();
            p[i] = new Point(x, y);
            p[i].draw();
        }

        StdDraw.setPenColor(Color.BLUE);
        StdDraw.setPenRadius();

        Arrays.sort(p);

        for (int i = 0; i < N - 3; i++) {
            Arrays.sort(p, p[i].SLOPE_ORDER);
            if (p[i].slopeTo(p[i + 1]) == p[i].slopeTo(p[i + 2])
                    && p[i].slopeTo(p[i + 2]) == p[i].slopeTo(p[i + 3])) {
                StdOut.println(p[i] + " -> " + p[i + 1] + " -> " + p[i + 2]
                        + " -> " + p[i + 3]);
                p[i].drawTo(p[i + 3]);
                p[i + 1].drawTo(p[i + 2]);
                p[i + 2].drawTo(p[i + 3]);
            }

        }
        StdDraw.show(0);
    }

    private static void prepareStdDraw() {
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        StdDraw.show(0);
        StdDraw.setPenColor(Color.RED);
        StdDraw.setPenRadius(0.005);
    }
}
