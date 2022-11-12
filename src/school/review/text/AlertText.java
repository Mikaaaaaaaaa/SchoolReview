package school.review.text;

import greenfoot.Actor;
import greenfoot.Font;
import greenfoot.GreenfootImage;

public class AlertText extends Actor
{


    public AlertText()
    {
        GreenfootImage greenfootImage = new GreenfootImage("placeholder".length() * 40, 30);
        greenfootImage.setFont(new Font(true, true, 40));
        greenfootImage.drawString("placeholder", 5, 1);
        greenfootImage.clear();
        this.setImage(greenfootImage);
    }


    public void setText(String text)
    {
        GreenfootImage greenfootImage = this.getImage();
        greenfootImage.clear();
        greenfootImage.drawString(text, 5, 30);
    }


}
