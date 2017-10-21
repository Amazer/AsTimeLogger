package timelogger.cyc.astimelogger;

import android.view.View;

import java.util.HashMap;

import timelogger.cyc.astimelogger.timelogger.cyc.astimelogger.database.T_Events;

/**
 * Created by cyc on 2017/10/21.
 */

public class EventBtnOnClickListener implements View.OnClickListener
{
    private EventViewHolder _holder;

    public EventBtnOnClickListener(EventViewHolder holder)
    {
        this._holder = holder;
    }

    @Override
    public void onClick(View view)
    {
        HashMap data=_holder.data;
        String btnName=(String)data.get(T_Events.Name) ;
        Debug.Log("Clicked event button:"+btnName);


    }
}
