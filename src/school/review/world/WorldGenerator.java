package school.review.world;

import school.review.Planet;
import school.review.world.objects.ObjectGenerator;
import school.review.world.objects.customs.Circle;
import school.review.world.objects.customs.Rectangle;

public class WorldGenerator
{
    private final Planet PLANET;

    private final ObjectGenerator OBJECT_GENERATOR;

    /**
     * This method should later offer different possibilities and mini-games to generate.
     *
     * @param planet is the planet on which the environment is to be created
     */
    public WorldGenerator(Planet planet)
    {
        this.PLANET = planet;
        this.OBJECT_GENERATOR = new ObjectGenerator();
    }

    /**
     * This method generates a very simple wall of rocks around the map
     */
    public void calculateRectangle(Location location, int height, int width)
    {
        OBJECT_GENERATOR.get(Rectangle.class).setPlanet(getPlanet())
                .setHeight(height)
                .setWidth(width)
                .setLocation(new Location(location.getPlanet(), location.getX(), location.getY(), false)
                        .setClassName("hill"))
                .generateBorder()
                .addGap();
    }

    public void calculateCircle(Location location, int radius)
    {
        OBJECT_GENERATOR.get(Circle.class)
                .setPlanet(getPlanet())
                .setStart(new Location(location.getPlanet(), location.getX(), location.getY(), false)
                        .setClassName("circle"))
                .setRadius(radius)
                .generateBorder()
                .addGap();
    }

    public Planet getPlanet()
    {
        return PLANET;
    }
}
