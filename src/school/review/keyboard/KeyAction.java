package school.review.keyboard;

import school.review.entity.Player;

import java.util.List;

public interface KeyAction
{
    void execute(Player player);

    List<GreenKey> getKeys();
}
