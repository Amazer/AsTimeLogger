package timelogger.cyc.astimelogger.eventmanager;

import android.app.Activity;
import android.os.Bundle;

import timelogger.cyc.astimelogger.R;

/**
 * Created by cyc on 2017/10/26.
 */

public class EventManagerActivity extends Activity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_manager_main);
    }
}
