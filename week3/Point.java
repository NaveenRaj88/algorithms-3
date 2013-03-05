import java.util.Comparator;

public class Point implements Comparable<Point> {

    // compare points by slope
    public final Comparator<Point> SLOPE_ORDER;

    private final int x; // x coordinate
    private final int y; // y coordinate

    // create the point (x, y)
    public Point(int x, int y) {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
        SLOPE_ORDER = new SlopeOrder();
    }

    // plot this point to standard drawing
    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }

    // draw line between this point and that point to standard drawing
    public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    // slope between this point and that point
    public double slopeTo(Point that) {
        if (that == null) {
            throw new NullPointerException();
        }
        // System.out.println("slope between " + this + " " + that);
        // System.out.println("that.y - this.y = " + (that.y - this.y));
        // System.out.println("that.x - this.x = " + (that.x - this.x));
        if (that.x == this.x) {
            if (that.y == this.y) {
                return Double.NEGATIVE_INFINITY;
            }
            return Double.POSITIVE_INFINITY;
        }
        if (that.y == this.y) {
            return 0.0;
        }
        double slope = ((double) (that.y - this.y)) / (that.x - this.x);
        // System.out.println("slope = " + slope);
        return slope;
    }

    // is this point lexicographically smaller than that one?
    // comparing y-coordinates and breaking ties by x-coordinates
    public int compareTo(Point that) {
        if (that == null) {
            throw new NullPointerException();
        }

        if (y == that.y && x == that.x)
            return 0;
        if (y < that.y || y == that.y && x < that.x)
            return -1;
        return 1;

    }

    // return string representation of this point
    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }

    // unit test
    public static void main(String[] args) {
        // double i = 4322.0 / 30766;
        // System.out.println(i);
    }

    private class SlopeOrder implements Comparator<Point> {
        public int compare(Point a, Point c) {
            double res = slopeTo(a) - slopeTo(c);
            if (res < 0.0)
                return -1;
            else if (res > 0.0)
                return 1;
            else
                return 0;
        }
    }
}
