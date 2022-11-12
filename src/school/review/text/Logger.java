package school.review.text;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger
{

    private static final DateTimeFormatter format = DateTimeFormatter.ofPattern("HH:mm:ss");

    public static void info(String info)
    {
        System.out.println("[" + format.format(LocalDateTime.now()) + "] [INFO] " + info);
    }

    public static void warn(String warn)
    {
        System.out.println("[" + format.format(LocalDateTime.now()) + "] [WARNING] " + warn);
    }

    public static void error(String error)
    {
        System.err.println("[" + format.format(LocalDateTime.now()) + "] [ERROR] " + error);
    }

}
