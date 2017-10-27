package timelogger.cyc.astimelogger.eventmanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import timelogger.cyc.astimelogger.R;

/**
 * Created by cyc on 2017/10/27.
 */

public class EventManagerListViewAdapter extends BaseAdapter
{
    private Context context;
    //    private List<Map> list;
    private ArrayList<String> list;
    private LayoutInflater inflate;

    public EventManagerListViewAdapter(Context context, ArrayList<String> list)
    {
        this.context = context;
        inflate = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.list = list;
    }

    @Override
    public int getCount()
    {
        return list.size();
    }

    @Override
    public Object getItem(int i)
    {
        return i;
    }

    @Override
    public long getItemId(int i)
    {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup)
    {
        if (view == null)
        {
            view = this.inflate.inflate(R.layout.eventmanager_item, null);
            EventMgrItemViewHolder viewHolder = new EventMgrItemViewHolder();
            viewHolder.button = (Button) view.findViewById(R.id.drag_item);
            viewHolder.textView = (TextView) view.findViewById(R.id.eventmanagerlist_item_view);
            view.setTag(viewHolder);
        }
        EventMgrItemViewHolder viewHolder = (EventMgrItemViewHolder) view.getTag();
        viewHolder.position = i;
        viewHolder.textView.setText(String.valueOf(i));
        return view;
    }
}
