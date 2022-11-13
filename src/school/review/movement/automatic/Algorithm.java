package school.review.movement.automatic;

import school.review.Planet;
import school.review.algorithm.Point;

import java.util.ArrayList;
import java.util.List;

public class Algorithm
{

    /**
     * This method checks whether the player is moving outside the border.
     * @param map The map of the planet.
     * @return The path to the destination.
     */
    public boolean isWalkable(int[][] map, Point point)
    {
        if (point.getY() < 0 || point.getY() > map.length - 1) return false;
        if (point.getX() < 0 || point.getX() > map[0].length - 1) return false;
        return map[point.getY()][point.getX()] == 0;
    }

    // The video shows what this project can do.

    /**
     * This method finds the path to the destination.
     * @param map The map of the planet.
     * @param point The starting point.
     * @return The path to the destination.
     */
    public List<Point> findNeighbors(int[][] map, Point point)
    {
        List<Point> neighbors = new ArrayList<>();
        Point up = point.offset(0, 1);
        Point down = point.offset(0, -1);
        Point left = point.offset(-1, 0);
        Point right = point.offset(1, 0);
        if (isWalkable(map, up)) neighbors.add(up);
        if (isWalkable(map, down)) neighbors.add(down);
        if (isWalkable(map, left)) neighbors.add(left);
        if (isWalkable(map, right)) neighbors.add(right);
        return neighbors;
    }

    /**
     * This method finds the path to the destination.
     * @param map The map of the planet.
     * @param start The starting point.
     * @param end The destination point.
     * @return The path to the destination.
     */
    public List<Point> findPath(int[][] map, Point start, Point end)
    {
        boolean finished = false;

        List<Point> existingWays = new ArrayList<>();

        existingWays.add(start);

        while (!finished)
        {
            List<Point> points = new ArrayList<>();

            for (Point point : existingWays)
            {
                points.addAll(findRoutes(existingWays, map, point, end));
            }

            for (Point point : points)
            {
                existingWays.add(point);
                if (end.equals(point))
                {
                    finished = true;
                    break;
                }
            }

            if (!finished && points.isEmpty())
                return null;
        }
        return improveOrder(existingWays);
    }

    public List<Point> findRoutes(List<Point> existingWays, int[][] map, Point start, Point end)
    {
        List<Point> points = new ArrayList<>();
        for (Point point : findNeighbors(map, start))
        {
            if(!points.contains(point) && !existingWays.contains(point))
            {
                points.add(point);
            }
        }
        return points;
    }

    /**
     * This method brings the pure sequence of the path into order.
     * @param used The list of points.
     * @return The list in order.
     */
    private List<Point> improveOrder(List<Point> used)
    {
        List<Point> path = new ArrayList<>();
        Point point = used.get(used.size() - 1);
        while (point.getPrevious() != null)
        {
            path.add(0, point);
            point = point.getPrevious();
        }
        return path;
    }

    /**
     * This method converts the planet map to a 2D array.
      * @param planet The planet.
     * @return The map of the planet as a 2D array.
     */
    public int[][] convertPlanetToMap(Planet planet)
    {
        int[][] map = new int[planet.getHeight()][planet.getWidth()];
        for (int y = 0; y < planet.getHeight(); y++)
        {
            for (int x = 0; x < planet.getWidth(); x++)
            {
                map[y][x] = planet.isWalkable(x, y) ? 0 : 1;
            }
        }
        return map;
    }
}