package timelogger.cyc.astimelogger.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ListView;

import timelogger.cyc.astimelogger.Debug;

/**
 * Created by cyc on 2017/10/26.
 */

public class DragableItemListVisew extends ListView
{
    private View _dragView;
    private WindowManager _winManager;
    private WindowManager.LayoutParams _winLayoutParam;

    public DragableItemListVisew(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev)
    {
        Debug.Log("onInterceptTouchEvent," + ev.getAction());
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev)
    {
        Debug.Log("onTouchEvent," + ev.getAction());
        return super.onTouchEvent(ev);
    }
}
