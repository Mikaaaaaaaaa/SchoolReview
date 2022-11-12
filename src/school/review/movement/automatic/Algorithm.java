package school.review.movement.automatic;

import school.review.Planet;
import school.review.algorithm.Point;

import java.util.ArrayList;
import java.util.List;

public class Algorithm
{

    public static void main(String[] args)
    {
        int[][] map = {
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0},
                {0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0},
                {0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0},
                {0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0},
                {0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0},
                {0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
        };

        Point start = new Point(0, 0, null);
        Point end = new Point(5, 5, null);
        Algorithm algorithm = new Algorithm();
        List<Point> path = algorithm.findPath(map, start, end);
        if (path != null)
        {
            for (Point point : path)
            {
                System.out.println(point);
            }
        } else
            System.out.println("No path found");
    }

    public boolean isWalkable(int[][] map, Point point)
    {
        if (point.getY() < 0 || point.getY() > map.length - 1) return false;
        if (point.getX() < 0 || point.getX() > map[0].length - 1) return false;
        return map[point.getY()][point.getX()] == 0;
    }

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

    public List<Point> findPath(int[][] map, Point start, Point end)
    {
        boolean finished = false;
        List<Point> used = new ArrayList<>();
        used.add(start);
        while (!finished)
        {
            List<Point> newOpen = new ArrayList<>();
            for (int i = 0; i < used.size(); ++i)
            {
                Point point = used.get(i);
                for (Point neighbor : findNeighbors(map, point))
                {
                    if (!used.contains(neighbor) && !newOpen.contains(neighbor))
                    {
                        newOpen.add(neighbor);
                    }
                }
            }

            for (Point point : newOpen)
            {
                used.add(point);
                if (end.equals(point))
                {
                    finished = true;
                    break;
                }
            }

            if (!finished && newOpen.isEmpty())
                return null;
        }

        List<Point> path = new ArrayList<>();
        Point point = used.get(used.size() - 1);
        while (point.getPrevious() != null)
        {
            path.add(0, point);
            point = point.getPrevious();
        }
        return path;
    }

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