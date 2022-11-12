package school.review.items;

import greenfoot.Actor;
import greenfoot.Greenfoot;

public class Rock extends Actor
{

    private final String COLOR;
    private final int WATER_CONTENT;

    public Rock()
    {
        WATER_CONTENT = Greenfoot.getRandomNumber(20);

        if (Greenfoot.getRandomNumber(2) == 0)
        {
            COLOR = "RED";
            setImage("images/gesteinRot.png");
        } else
        {
            COLOR = "BLUE";
            setImage("images/gesteinBlau.png");
        }
    }

    public void act()
    {

    }

    public int getWATER_CONTENT()
    {
        return WATER_CONTENT;
    }

    public String getCOLOR()
    {
        return COLOR;
    }

    @Override
    public String toString()
    {
        return "WaterContent: " + WATER_CONTENT;
    }
}

