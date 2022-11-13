package school.review.algorithm;

import java.util.Objects;

public class Point {
    private int x;
    private int y;
    private Point previous;

    public Point(int x, int y, Point previous) {
        this.x = x;
        this.y = y;
        this.previous = previous;
    }

    /**
     * This method change the object to a string.
     * @return The string of the object.
     */
    @Override
    public String toString() { return String.format("(%d, %d)", x, y); }

    /**
     * This method compares two points.
     * @param o The point to compare.
     * @return Whether the points are equal.
     */
    @Override
    public boolean equals(Object o) {
        Point point = (Point) o;
        return x == point.x && y == point.y;
    }

    @Override
    public int hashCode() { return Objects.hash(x, y); }

    /**
     * This method creates a new point with the given offset.
     * @param ox The offset on the x-axis.
     * @param oy The offset on the y-axis.
     * @return The new point.
     */
    public Point offset(int ox, int oy) { return new Point(x + ox, y + oy, this);  }

    /**
     * This method gets the x-coordinate.
     * @return The x-coordinate.
     */
    public int getX()
    {
        return x;
    }

    /**
     * This method gets the y-coordinate.
     * @return The y-coordinate.
     */
    public int getY()
    {
        return y;
    }

    /**
     * This method gets the previous point.
     * @return The previous point.
     */
    public Point getPrevious()
    {
        return previous;
    }
}
