package timelogger.cyc.astimelogger;

import android.content.Intent;
import android.view.View;

import timelogger.cyc.astimelogger.eventmanager.EventManagerActivity;

/**
 * Created by cyc on 2017/10/21.
 */

public class EventsManagerOnClickListener implements View.OnClickListener
{
    @Override
    public void onClick(View view)
    {
        Debug.Log("do click eventsManager,跳转到事件界面");
        Intent intent=new Intent(MainActivity.context, EventManagerActivity.class);
        MainActivity.context.startActivity(intent);
    }
}
