package timelogger.cyc.astimelogger.eventmanager;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

import timelogger.cyc.astimelogger.Debug;
import timelogger.cyc.astimelogger.R;
import timelogger.cyc.astimelogger.view.DragItemChangedListener;
import timelogger.cyc.astimelogger.view.DragableItemListView;

/**
 * Created by cyc on 2017/10/26.
 */

public class EventManagerActivity extends Activity implements DragItemChangedListener
{
    private DragableItemListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_manager_main);
        InitListView();
    }

    private void InitListView()
    {
        listview = (DragableItemListView) findViewById(R.id.eventManagerList);
        listview.setDragSrcId(R.id.drag_item);
        ArrayList<String> list = new ArrayList<String>();
        for (int i = 0, imax = 10; i < imax; ++i)
        {
            list.add(String.valueOf(i));
        }
        EventManagerListViewAdapter adapter = new EventManagerListViewAdapter(this, list);

        listview.setAdapter(adapter);
        listview.setDragItemChangedListener(this);
    }

    @Override
    public void OnDragItemChanged(int dragSrcPos, int dragPos)
    {
        Debug.Log("OnDragItemChanged,srcPos:" + dragSrcPos + ",targetPos:" + dragPos);
        View srcView=listview.getChildAt(dragSrcPos);
        if(srcView!=null)
        {
        }
        else
        {
            Debug.Log("error,srcView==null");
        }
        View tarView=listview.getChildAt(dragPos);
        if(tarView!=null)
        {

        }
        else
        {
            Debug.Log("error,tarView==null");
        }
    }
}
