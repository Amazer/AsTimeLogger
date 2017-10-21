package timelogger.cyc.astimelogger;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import timelogger.cyc.astimelogger.timelogger.cyc.astimelogger.database.T_Events;

/**
 * Created by 95 on 2017/9/25.
 */

public class EventListAdapter extends BaseAdapter
{

    private List<Map> _dataList;
    private LayoutInflater _inflater;

    public EventListAdapter(Context context, List<Map> list)
    {
        _dataList = list;
        _inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount()
    {
        return _dataList.size() + 1;
    }

    @Override
    public Object getItem(int position)
    {
        return position;
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        EventViewHolder holder;
        View vi = convertView;
        if (vi == null)
        {
            vi = _inflater.inflate(R.layout.eventlist_item, null);
            holder = new EventViewHolder();
            holder.tagBtn = (Button) vi.findViewById(R.id.tag_button);
            holder.tagBtn.setOnClickListener(new EventBtnOnClickListener(holder));
            holder.managerBtn = (Button) vi.findViewById(R.id.manager_button);
            holder.managerBtn.setOnClickListener(new EventsManagerOnClickListener());
            vi.setTag(holder);
        } else
        {
            holder = (EventViewHolder) vi.getTag();
        }

        if (holder != null)
        {
            holder.position = position;
            if (position >= (getCount() - 1))
            {
                holder.data = null;
                holder.tagBtn.setVisibility(View.INVISIBLE);
                holder.managerBtn.setVisibility(View.VISIBLE);
            } else
            {
                HashMap s = (HashMap) _dataList.get(position);
                holder.data = s;
                holder.tagBtn.setTag(s);
                holder.tagBtn.setText(String.valueOf(s.get(T_Events.Name)));
                holder.tagBtn.setVisibility(View.VISIBLE);
                holder.managerBtn.setVisibility(View.INVISIBLE);
            }
        }
        return vi;
    }
}
