package timelogger.cyc.astimelogger;


import android.icu.util.Calendar;
import android.icu.util.TimeZone;

/**
 * Created by cyc on 2017/10/2.
 */

public class DateCalculator {
    public static LoggerDate GetCurDate() {
        Calendar c = Calendar.getInstance();
        c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        LoggerDate date = new LoggerDate();
        date.year = c.get(Calendar.YEAR);
        date.mon = c.get(Calendar.MONTH) + 1;
        date.day = c.get(Calendar.DAY_OF_MONTH);
        date.week = c.get(Calendar.DAY_OF_WEEK) - 1; // 第一天是星期日
        date.hour = c.get(Calendar.HOUR_OF_DAY);
        return date;
    }
}

