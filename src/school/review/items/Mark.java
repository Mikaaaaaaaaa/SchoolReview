package school.review.items;

import greenfoot.Actor;

public class Mark extends Actor
{
    public Mark()
    {
        this.setImage("images/marke.png");
    }


    public void act()
    {
    }

    /**
     * Creates a marker at a specific location
     *
     * @param x is the X coordinate
     * @param y is the Y coordinate
     */
    public void createMark(int x, int y)
    {
        this.getWorld().addObject(this, x, y);
    }

    /**
     * This method checks whether a marker has been set
     *
     * @return true if the above conditions are met.
     */
    public boolean isMarkAvailable()
    {
        return this.getOneIntersectingObject(Mark.class) != null;
    }


    /**
     * If a tag can be deleted, it will be removed.
     * You can find the exact conditions at {@link #isMarkAvailable()}
     */
    public void removeMark()
    {
        if (this.isMarkAvailable())
        {
            this.removeTouching(Mark.class);
        }
    }
}