package timelogger.cyc.astimelogger;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends Activity {
    private ListView _list;
    private DateListAdapter _listAdapter;
    private ArrayList<String> _dataList;

    private ListView _eventList;
    private EventListAdapter _eventListAdapter;
    private ArrayList<String> _eventDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InitDateListView();
        InitEventListView();
        ActionBar actionBar=getActionBar();
        actionBar.show();

    }
    private  void InitDateListView()
    {
        // date list
        _list = (ListView) findViewById(R.id.list);

        _dataList = new ArrayList<String>();
        for (int i = 0, imax = 10; i < imax; ++i) {
            _dataList.add(String.valueOf(i));
        }

        _listAdapter = new DateListAdapter(this, _dataList);
        _list.setAdapter(_listAdapter);

        _list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
        _list.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {

            }

            @Override
            public void onScroll(AbsListView absListView, int i, int i1, int i2) {

            }
        });

    }
    private  void  InitEventListView()
    {
        _eventList=(ListView)findViewById(R.id.eventlist);
        _eventDataList=new ArrayList<String>();
        for(int i=0,imax=5;i<imax;++i)
        {
            _eventDataList.add("event "+i);
        }

        _eventListAdapter=new EventListAdapter(this,_eventDataList);
        _eventList.setAdapter(_eventListAdapter);
    }
}
