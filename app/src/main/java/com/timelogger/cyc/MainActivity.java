package com.timelogger.cyc;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import timelogger.cyc.R;

public class MainActivity extends Activity {
    private ListView _list;
    private View _listItem;
    private DateListAdapter _listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        _list=(ListView)findViewById(R.id.list);

        _listAdapter=new DateListAdapter(this);

        _list.setAdapter(_listAdapter);
    }
}
