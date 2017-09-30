package timelogger.cyc.astimelogger;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends Activity {
    private ListView _dateList;
    private DateListAdapter _dateListAdapter;
    private ArrayList<String> _dataList;

    private ListView _eventList;
    private EventListAdapter _eventListAdapter;
    private ArrayList<String> _eventDataList;

//    private MenuItem _menu_today;
//    private MenuItem _menu_settings;
//    private MenuItem _menu_date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        ActionBar actionBar = getActionBar();
//        actionBar.show();
        InitDateListView();
//        InitEventListView();

    }

    private void InitDateListView() {
        // date list
        _dateList = (ListView) findViewById(R.id.list);
        _dateList.setDividerHeight(0);

        _dataList = new ArrayList<String>();
        for (int i = 0, imax = 24; i < imax; ++i) {
            _dataList.add(String.valueOf(i));
        }

        _dateListAdapter = new DateListAdapter(this, _dataList);
        _dateList.setAdapter(_dateListAdapter);

        _dateList.setSelection(5);     // 哪个条目作为第一个

//        _dateList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//            }
//        });
//        _dateList.setOnScrollListener(new ListView.OnScrollListener(){
//            @Override
//            public void onScrollStateChanged(AbsListView absListView, int scrollState) {
//                switch (scrollState)
//                {
//                    case AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL:    // 拖动
//                    case AbsListView.OnScrollListener.SCROLL_STATE_FLING:           // 惯性滑动
//                    {
//                        // 滑动的时候
//                        // 1.获取第一个可见item，并获取其date数据，以及pos信息
//                        // 2.根据位置推算出下一个改变日期的item pos,并且刷新 otherDateTextView的位置信息
////                        int firstVisiblePosition = _dateList.getFirstVisiblePosition();
//                    }
//                        break;
//                    case AbsListView.OnScrollListener.SCROLL_STATE_IDLE:            // 手指离开或者惯性滑动停止
//                        break;
//                }
//
//            }

//            @Override
//            public void onScroll(AbsListView absListView, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
//                if(firstVisibleItem>0)
//                {
//                   // 获取第一个可见Item的数据，设置 _menu_date的title为item当前的日期
//                }
//
//            }
//        });

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
