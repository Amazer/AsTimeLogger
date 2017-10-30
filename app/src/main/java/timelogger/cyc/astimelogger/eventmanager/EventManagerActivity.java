package timelogger.cyc.astimelogger.eventmanager;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.TranslateAnimation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import timelogger.cyc.astimelogger.Debug;
import timelogger.cyc.astimelogger.R;
import timelogger.cyc.astimelogger.view.DragItemChangedListener;
import timelogger.cyc.astimelogger.view.DragableItemListView;

import static timelogger.cyc.astimelogger.MainActivity.dbHelper;

/**
 * Created by cyc on 2017/10/26.
 */

public class EventManagerActivity extends Activity implements DragItemChangedListener
{
    private DragableItemListView listview;
    private List<Map>  datalist;
    EventManagerListViewAdapter adapter;

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

        String sql = this.getResources().getString(R.string.Select_All_From_T_Events);
        datalist = dbHelper.queryListMap(sql, null);
        adapter = new EventManagerListViewAdapter(this, datalist);

        listview.setAdapter(adapter);
        listview.setDragItemChangedListener(this);
    }

    @Override
    public void OnDragItemChanged(int srcPos, int tarPos)
    {
        Debug.Log("OnDragItemChanged,srcPos:" + srcPos + ",targetPos:" + tarPos);
        View srcView=listview.getChildAt(srcPos);
        if(srcView==null)
        {
            Debug.Log("error,srcView==null");
        }
        final View tarView=listview.getChildAt(tarPos);
        if(tarView==null)
        {
            Debug.Log("error,tarView==null");
        }
        if(srcView!=null&& tarView!=null)
        {
            Map srcData=datalist.get(srcPos);
            Map tarData=datalist.get(tarPos);
            datalist.set(srcPos,tarData);
            datalist.set(tarPos,srcData);

            final EventMgrItemViewHolder srcHolder=(EventMgrItemViewHolder) srcView.getTag();
            final EventMgrItemViewHolder tarHoder=(EventMgrItemViewHolder) tarView.getTag();

            HashMap t=srcHolder.data;
            srcHolder.data=tarHoder.data;
            tarHoder.data=t;


            TranslateAnimation translateAnimation=new TranslateAnimation(srcView.getX(),tarView.getX(),srcView.getY(),tarView.getY());
            translateAnimation.setDuration(100);

            float srcX=srcView.getX();
            float srcY=srcView.getY();

            srcView.setX(tarView.getX());
            srcView.setY(tarView.getY());

            tarView.setX(srcX);
            tarView.setY(srcY);

            tarView.startAnimation(translateAnimation);
        }
    }

    @Override
    public void OnDropList()
    {
        listview.setAdapter(adapter);
    }
}
