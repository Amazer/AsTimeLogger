package timelogger.cyc.astimelogger;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by cyc on 2017/10/2.
 */

public final class ViewHolder {
    public TextView date;
    public TextView hour;
    public Button v0;
    public Button v1;
    public Button v2;
    public Button v3;
    public int position;

    public void SetDate() {
        LoggerDate loggerDate = DateCalculator.GetDate(position);

        hour.setText(String.valueOf(loggerDate.hour));
        v0.setText("0");
        v1.setText("1");
        v2.setText("2");
        v3.setText("3");
        if (loggerDate.hour == 0) {
            date.setVisibility(View.VISIBLE);
            date.setText(loggerDate.day + "\n星期" + loggerDate.week);
        } else {
            date.setVisibility(View.INVISIBLE);
        }
    }
}

