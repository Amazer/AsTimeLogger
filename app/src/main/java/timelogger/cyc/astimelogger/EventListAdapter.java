package timelogger.cyc.astimelogger;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import java.util.ArrayList;

/**
 * Created by 95 on 2017/9/25.
 */

public class EventListAdapter extends BaseAdapter
{

    private ArrayList<String> _dataList;
    private LayoutInflater _inflater;

    public EventListAdapter(Context context, ArrayList<String> d)
    {
        _dataList = d;
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
            holder.managerBtn = (Button) vi.findViewById(R.id.manager_button);
            vi.setTag(holder);
        } else
        {
            holder = (EventViewHolder) vi.getTag();
        }

        if (holder != null)
        {
            holder.position = position;
            if(position>=(getCount()-1))
            {
                holder.tagBtn.setVisibility(View.INVISIBLE);
                holder.managerBtn.setVisibility(View.VISIBLE);
            }
            else
            {
                String s = _dataList.get(position);
                holder.tagBtn.setTag(s);
                holder.tagBtn.setVisibility(View.VISIBLE);
                holder.managerBtn.setVisibility(View.INVISIBLE);
            }
        }
        return vi;
    }
}
