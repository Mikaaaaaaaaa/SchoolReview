package school.review.world.objects;

import school.review.world.objects.customs.Circle;
import school.review.world.objects.customs.Rectangle;

import java.util.HashSet;
import java.util.Set;

public class ObjectGenerator
{

    private final Set<WorldObject> WORLD_OBJECT_SET;

    public ObjectGenerator()
    {
        WORLD_OBJECT_SET = new HashSet<>();

        WORLD_OBJECT_SET.add(new Circle());
        WORLD_OBJECT_SET.add(new Rectangle());
    }


    /**
     * Returns an object with the class given.
     *
     * @param clazz the clazz of the object to return.
     * @param <T>   the type of the return value.
     * @return the object from {@link #WORLD_OBJECT_SET}.
     */
    @SuppressWarnings("unchecked")
    public <T extends WorldObject> T get(Class<T> clazz)
    {
        return (T) WORLD_OBJECT_SET.stream().filter(currentCloudTool -> currentCloudTool.getClass().equals(clazz)).findFirst().orElse(null);
    }

}
