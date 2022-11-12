package school.review.keyboard;

import java.util.Arrays;

public enum GreenKey
{

    A("a"), B("b"), C("c"), D("d"), E("e"), F("f"), G("g"), H("h"), I("i"), J("j"), K("k"), L("l"), M("m"), N("n"),
    O("o"), P("p"), Q("q"), R("r"), S("s"), T("t"), U("u"), V("v"), W("w"), X("x"), Y("y"), Z("z"), NULL("0"), ONE("1"),
    TWO("2"), THREE("3"), FOUR("4"), FIVE("5"), SIX("6"), SEVEN("7"), EIGHT("8"), NINE("9"), ARROW_DOWN("down"),
    ARROW_UP("up"), ARROW_LEFT("left"), ARROW_RIGHT("right");
    private String key;

    GreenKey(String key)
    {
        this.key = key;
    }

    public final String getKey()
    {
        return key;
    }

    public static GreenKey getKey(String label) {
        return Arrays.stream(values()).filter(greenKey -> greenKey.getKey().equals(label)).findFirst().orElse(null);
    }
}
