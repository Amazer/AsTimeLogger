package timelogger.cyc.astimelogger;

/**
 * Created by cyc on 2017/10/2.
 */

public class Debug {
    public static void Log(String content) {
        System.out.println("Log:"+content);
    }
    public static void LogTag(String tag,String content) {
        System.out.println(String.format("[{0}] {1}",tag,content));
    }
}
