package timelogger.cyc.astimelogger;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends Activity {
    private ListView _list;
    private View _listItem;
    private DateListAdapter _listAdapter;
    private ArrayList<String> _dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
    }
}
