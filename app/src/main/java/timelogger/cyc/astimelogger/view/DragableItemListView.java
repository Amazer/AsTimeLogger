package timelogger.cyc.astimelogger.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.PixelFormat;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import timelogger.cyc.astimelogger.Debug;

/**
 * Created by cyc on 2017/10/26.
 */

public class DragableItemListView extends ListView
{
    private ImageView _dragView;
    private WindowManager _winManager;
    private WindowManager.LayoutParams _winLayoutParam;

    private int dragSrcPos;
    private int dragPos;

    private int dragPoint;
    private int dragOffsetY;

    private int upScrollBounce;
    private int downScrollBounce;

    private final static int step = 1;

    private int curStep;

    private int dragSrcId;
    private DragItemChangedListener dragItemChangedListener;


    public DragableItemListView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    public void setDragSrcId(int srcId)
    {
        this.dragSrcId = srcId;
    }

    public void setDragItemChangedListener(DragItemChangedListener listener)
    {
        this.dragItemChangedListener = listener;
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev)
    {
        Debug.Log("onInterceptTouchEvent," + ev.getAction());

        if (ev.getAction() == MotionEvent.ACTION_DOWN)        // 按下事件
        {
            int x = (int) ev.getX();       // 获取与listView对应的x坐标
            int y = (int) ev.getY();

            dragSrcPos = dragPos = pointToPosition(x, y);
            if (dragSrcPos == AdapterView.INVALID_POSITION)
            {
                return super.onInterceptTouchEvent(ev);
            }

            // 获取当前位置的视图
            ViewGroup itemView = (ViewGroup) getChildAt(dragPos - getFirstVisiblePosition());

            dragPoint = y - itemView.getTop();
            dragOffsetY = (int) (ev.getRawY() - y);

            View dragger = itemView.findViewById(dragSrcId);

            if (dragger != null && x > dragger.getLeft() - 20)
            {
                upScrollBounce = getHeight() / 3;
                downScrollBounce = getHeight() * 2 / 3;

                itemView.setDrawingCacheEnabled(true);

                Bitmap bm = Bitmap.createBitmap(itemView.getDrawingCache());
                startDrag(bm, y);
            }
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev)
    {
        Debug.Log("onTouchEvent," + ev.getAction());
        if (_dragView == null || dragPos == INVALID_POSITION)
        {
            return super.onTouchEvent(ev);
        }

        int action = ev.getAction();
        switch (action)
        {
            case MotionEvent.ACTION_UP:
                int upY = (int) ev.getY();
                stopDrag();
                onDrop(upY);
                break;
            case MotionEvent.ACTION_MOVE:
                int moveY = (int) ev.getY();
                onDrag(moveY);
                break;
            case MotionEvent.ACTION_DOWN:
                break;
            default:
                break;
        }   // end switch
        return true;
    }

    public void startDrag(Bitmap bm, int y)
    {
        _winLayoutParam = new WindowManager.LayoutParams();
        _winLayoutParam.gravity = Gravity.TOP;
        _winLayoutParam.x = 0;
        _winLayoutParam.y = y - dragPoint + dragOffsetY;
        _winLayoutParam.width = WindowManager.LayoutParams.WRAP_CONTENT;
        _winLayoutParam.height = WindowManager.LayoutParams.WRAP_CONTENT;

        _winLayoutParam.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON | WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN;
        _winLayoutParam.format = PixelFormat.TRANSLUCENT;
        _winLayoutParam.windowAnimations = 0;

        ImageView imageView = new ImageView(getContext());
        imageView.setImageBitmap(bm);
        _winManager = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        _winManager.addView(imageView, _winLayoutParam);
        _dragView = imageView;
    }

    public void onDrag(int y)
    {
        int drag_top = y - dragPoint;
        if (_dragView != null && drag_top > 0)
        {
            _winLayoutParam.alpha = 0.5f;
            _winLayoutParam.y = y - dragPoint + dragOffsetY;
            _winManager.updateViewLayout(_dragView, _winLayoutParam);
        }
        int tempPos = pointToPosition(0, y);
        if (tempPos != INVALID_POSITION)
        {
            dragPos = tempPos;
        }
        doScroller(y);
    }

    public void doScroller(int y)
    {
        if (y < upScrollBounce)
        {
            curStep = step + (upScrollBounce - y) / 10;
        } else if (y > downScrollBounce)
        {
            curStep = -(step + (y - downScrollBounce)) / 10;
        } else
        {
            curStep = 0;
        }

        View view = getChildAt(dragPos - getFirstVisiblePosition());
        setSelectionFromTop(dragPos, view.getTop() + curStep);
    }

    public void stopDrag()
    {
        if (_dragView != null)
        {
            _winManager.removeViewImmediate(_dragView);
            _dragView = null;
        }
    }

    public void onDrop(int y)
    {
        int tmpPos=pointToPosition(0,y);
        if(tmpPos!=INVALID_POSITION)
        {
            dragPos=tmpPos;
        }

        if(y<getChildAt(0).getTop())
        {
            dragPos=0;
        }
        else if(y>getChildAt(getChildCount()-1).getBottom())
        {
            dragPos=getAdapter().getCount()-1;
        }
        if(dragPos<getAdapter().getCount())
        {
            if(dragItemChangedListener!=null)
            {
                dragItemChangedListener.OnDragItemChanged(dragSrcPos,dragPos);
            }
        }
    }
}
