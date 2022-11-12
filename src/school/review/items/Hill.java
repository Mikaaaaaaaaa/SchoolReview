package school.review.items;

import greenfoot.Actor;
import greenfoot.Greenfoot;

public class Hill extends Actor
{

    private final int SLOPE;

    public Hill()
    {
        SLOPE = Greenfoot.getRandomNumber(30) + 31;
        setImage("images/huegel.png");
    }

    public void act()
    {
    }

    public int getSlope()
    {
        return SLOPE;
    }
}
