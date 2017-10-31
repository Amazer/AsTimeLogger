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
    private List<Map> datalist;
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
        //        Debug.Log("OnDragItemChanged,srcPos:" + srcPos + ",targetPos:" + tarPos);
        View srcView = listview.getChildAt(srcPos);
        final View tarView = listview.getChildAt(tarPos);
        if (srcView != null && tarView != null)
        {
            Debug.Log("OnDragItemChanged,srcPos:" + srcPos + ",targetPos:" + tarPos);


            final EventMgrItemViewHolder srcHolder = (EventMgrItemViewHolder) srcView.getTag();
            final EventMgrItemViewHolder tarHoder = (EventMgrItemViewHolder) tarView.getTag();

            int srcIndex = srcHolder.position;
            int tarIndex = tarHoder.position;

            srcHolder.position = tarIndex;
            tarHoder.position = srcIndex;

            Map srcData = datalist.get(srcIndex);
            Map tarData = datalist.get(tarIndex);

            datalist.set(srcIndex, tarData);
            datalist.set(tarIndex, srcData);

            HashMap t = srcHolder.data;
            srcHolder.data = tarHoder.data;
            tarHoder.data = t;

            // // TODO: 2017/10/30
            // 1.动画的起始位置不对 --- checked :先设置位置，再相对于自己做动画
            // 2.item位置交换后，交换不回来了

            final float srcX = srcView.getX();
            final float srcY = srcView.getY();

            final float tarX = tarView.getX();
            final float tarY = tarView.getY();



            srcView.setX(tarX);
            srcView.setY(tarY);

            tarView.setX(srcX);
            tarView.setY(srcY);

            // 如果不指定动画位置的type，默认是相对于自己位置的
            TranslateAnimation translateAnimation = new TranslateAnimation(0, 0, tarY - srcY, 0);
            translateAnimation.setDuration(300);
            tarView.startAnimation(translateAnimation);
//            tarView.postOnAnimation(new Runnable()
//            {
//                @Override
//                public void run()
//                {
//                    tarView.setX(srcX);
//                    tarView.setY(srcY);
//                }
//            });
        }
    }

    @Override
    public void OnDropList()
    {
        listview.setAdapter(adapter);
    }
}
