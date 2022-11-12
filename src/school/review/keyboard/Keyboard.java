package school.review.keyboard;

import greenfoot.Greenfoot;
import school.review.entity.Player;
import school.review.keyboard.actions.information.PlayerInformation;
import school.review.keyboard.actions.movement.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Keyboard
{
    private final Set<KeyAction> ACTIONS;

    private final Player PLAYER;

    public Keyboard(Player player)
    {
        this.ACTIONS = new HashSet<>();
        this.PLAYER = player;

        this.ACTIONS.add(new MoveUp());
        this.ACTIONS.add(new MoveDown());
        this.ACTIONS.add(new MoveLeft());
        this.ACTIONS.add(new MoveRight());
        this.ACTIONS.add(new AutomaticRunning());
        this.ACTIONS.add(new PlayerInformation());
    }

    public void startListening()
    {
        Arrays.stream(GreenKey.values()).filter(key ->  Greenfoot.isKeyDown(key.getKey())).forEach(this::start);
    }

    private void start(GreenKey greenKey)
    {
        this.ACTIONS.stream().filter(keyAction -> keyAction.getKeys().contains(greenKey)).findAny().stream().forEach(action -> action.execute(PLAYER));
    }
}
