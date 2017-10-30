package timelogger.cyc.astimelogger.eventmanager;

import android.widget.Button;
import android.widget.TextView;

import java.util.HashMap;

import timelogger.cyc.astimelogger.Debug;
import timelogger.cyc.astimelogger.database.T_Events;

/**
 * Created by cyc on 2017/10/27.
 */

public class EventMgrItemViewHolder
{
    public Button button;
    public TextView textView;
    public int position;
    public HashMap data;

    public void  UpdateView()
    {
        String tName=(String)data.get(T_Events.Name);
        textView.setText(tName);
        Debug.Log("pos:"+position+",data:"+tName);
    }
}
