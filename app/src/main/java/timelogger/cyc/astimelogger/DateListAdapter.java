package timelogger.cyc.astimelogger;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import static timelogger.cyc.astimelogger.DateCalculator.TOTAL_COUNT;

/**
 * Created by cyc on 2017/9/23.
 */

public class DateListAdapter extends BaseAdapter {
    private LayoutInflater inflater = null;

    public DateListAdapter(Context context) {
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return TOTAL_COUNT;
        //        return data.size();
    }

    @Override
    public Object getItem(int i) {
       return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        View vi = view;
        if (vi == null) {
            vi = inflater.inflate(R.layout.listview_item, null);
            holder = new ViewHolder();
            holder.date = (TextView) vi.findViewById(R.id.date);
            holder.date.setWidth(ConstantValue.GetPixelsWidthPercent(0.2f));
            holder.hour = (TextView) vi.findViewById(R.id.hour);
            holder.v0 = (Button) vi.findViewById(R.id.text0);
            holder.v1 = (Button) vi.findViewById(R.id.text1);
            holder.v2 = (Button) vi.findViewById(R.id.text2);
            holder.v3 = (Button) vi.findViewById(R.id.text3);
            holder.v0.setOnClickListener(new QuaterHourOnClickListener(holder, QuaterHourOnClickListener.QuaterHourType.View0));
            holder.v1.setOnClickListener(new QuaterHourOnClickListener(holder, QuaterHourOnClickListener.QuaterHourType.View1));
            holder.v2.setOnClickListener(new QuaterHourOnClickListener(holder, QuaterHourOnClickListener.QuaterHourType.View2));
            holder.v3.setOnClickListener(new QuaterHourOnClickListener(holder, QuaterHourOnClickListener.QuaterHourType.View3));
            vi.setTag(holder);      // 把 holder 记到vi中
        } else {
            vi = view;
            holder = (ViewHolder) vi.getTag();      // 取出存储过的 holder
        }
        if (holder != null) {
            holder.position = i;
            holder.SetDate();
        }
        return vi;
    }

}
