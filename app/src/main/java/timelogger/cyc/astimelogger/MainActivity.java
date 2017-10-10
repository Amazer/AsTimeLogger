package timelogger.cyc.astimelogger;

import android.app.Activity;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import static timelogger.cyc.astimelogger.DateCalculator.LOOP_COUNT;
import static timelogger.cyc.astimelogger.DateCalculator.MIDDLE_INDEX;

public class MainActivity extends Activity {
    private ListView _dateList;
    private DateListAdapter _dateListAdapter;

    private ListView _eventList;
    private EventListAdapter _eventListAdapter;
    private ArrayList<String> _eventDataList;

    private TextView _curDateView;

    //
    //    private MenuItem _menu_today;
    //    private MenuItem _menu_settings;
    //    private MenuItem _menu_date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ConstantValue.InitConstantValue(this);

        //        ActionBar actionBar = getActionBar();
        //        actionBar.show();
        InitDateTextView();

        InitDateListView();


        InitTestFunction();
        //        InitEventListView();

    }

    private void InitDateTextView() {
        _curDateView = (TextView) findViewById(R.id.curDate);
        _curDateView.setWidth(ConstantValue.GetPixelsWidthPercent(0.2f));
    }

    private void InitDateListView() {
        // date list
        _dateList = (ListView) findViewById(R.id.list);
        _dateList.setDividerHeight(0);

        _dateListAdapter = new DateListAdapter(this);
        _dateList.setAdapter(_dateListAdapter);

        Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);

        Debug.Log("cur hour:" + hour + ",first item:" + (MIDDLE_INDEX + hour));
        _dateList.setSelection(MIDDLE_INDEX + hour);     // 哪个条目作为第一个

        //        _dateList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        //            @Override
        //            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //
        //            }
        //        });

        _dateList.setOnScrollListener(new ListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int scrollState) {
                switch (scrollState) {
                    case AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL:    // 拖动
                    case AbsListView.OnScrollListener.SCROLL_STATE_FLING:           // 惯性滑动
                    {
                        // 滑动的时候
                        // 1.获取第一个可见item，并获取其date数据，以及pos信息
                        // 2.根据位置推算出下一个改变日期的item pos,并且刷新 otherDateTextView的位置信息
                        //                        int firstVisiblePosition = _dateList.getFirstVisiblePosition();
                        //                        _dateList.getItemAtPosition(firstVisiblePosition);
                        //                        _curDateView.setText(firstVisiblePosition % 24);
                    }
                    break;
                    case AbsListView.OnScrollListener.SCROLL_STATE_IDLE:            // 手指离开或者惯性滑动停止
                        break;
                }

            }

            @Override
            public void onScroll(AbsListView absListView, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                // 获取第一个可见Item的数据，设置 _menu_date的title为item当前的日期
                LoggerDate firstDate = DateCalculator.GetDate(firstVisibleItem);
                int next0HourDelta = LOOP_COUNT - firstDate.hour;
                _curDateView.setAlpha((next0HourDelta - 2) * 0.5f);
                _curDateView.setText(firstDate.day + "\n星期" + firstDate.week);

                if (next0HourDelta < 2 /*|| next0HourDelta > 23*/) {
                    _curDateView.setVisibility(View.INVISIBLE);
                } else {
                    _curDateView.setVisibility(View.VISIBLE);
                }

                if (next0HourDelta < 4) {
                    View view = _dateList.getChildAt(next0HourDelta);
                    if (view != null) {
                        ViewHolder holder = (ViewHolder) view.getTag();
                        int topPos = view.getTop();       // 获取距离顶部的y像素值
                        if (topPos <= 0) {
                            holder.date.setVisibility(View.INVISIBLE);
                            _curDateView.setVisibility(View.VISIBLE);
                            //                            Debug.Log("cur nextOHourDelta:"+next0HourDelta);
                        } else {
                            holder.date.setVisibility(View.VISIBLE);
//                            if(topPos>=_curDateView.getHeight())
//                            {
//                                _curDateView.setVisibility(View.VISIBLE);
//                            }
                        }
                    }
                }
            }
        });
    }

    private void InitTestFunction() {
        LoggerDate curDate = DateCalculator.GetCurDate();

        Debug.Log("curDate:");
        DateCalculator.PintLoggerDate(curDate);
        LoggerDate newDate = DateCalculator.GetDate(MIDDLE_INDEX + 25);
        Debug.Log("newDate, add 25 hour:");
        DateCalculator.PintLoggerDate(newDate);
    }

    private void InitEventListView() {
        _eventList = (ListView) findViewById(R.id.eventlist);
        _eventDataList = new ArrayList<String>();
        for (int i = 0, imax = 5; i < imax; ++i) {
            _eventDataList.add("event " + i);
        }

        _eventListAdapter = new EventListAdapter(this, _eventDataList);
        _eventList.setAdapter(_eventListAdapter);
    }

    //    @Override
    //    public boolean onMenuItemSelected(int featureId, MenuItem item) {
    //        switch (item.getItemId())
    //        {
    //            case R.id.action_menu:
    //                System.out.println("On Menu Item Selected action_menu");
    //                return true;
    //            case R.id.action_date:
    //                System.out.println("On Menu Item Selected action_date");
    //
    //                return true;
    //            case R.id.action_today:
    //                System.out.println("On Menu Item Selected action_today");
    //                return true;
    //        }
    //        return super.onMenuItemSelected(featureId, item);
    //    }
    //
    //    @Override
    //    public boolean onCreateOptionsMenu(Menu menu) {
    //        _menu_date = menu.findItem(R.id.action_date);
    //        _menu_settings=menu.findItem(R.id.action_menu);
    //        _menu_today=menu.findItem(R.id.action_today);
    //        return super.onCreateOptionsMenu(menu);
    //    }
    //
    //    @Override
    //    public boolean onPrepareOptionsMenu(Menu menu) {
    //        return super.onPrepareOptionsMenu(menu);
    //    }
}
