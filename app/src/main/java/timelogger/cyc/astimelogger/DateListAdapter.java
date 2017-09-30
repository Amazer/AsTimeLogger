package timelogger.cyc.astimelogger;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by cyc on 2017/9/23.
 */

public class DateListAdapter extends BaseAdapter {

    private ArrayList<String> data;
    private LayoutInflater inflater = null;

    public DateListAdapter(Context context, ArrayList<String> d) {
        data = d;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return data.size();
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
            holder.hour = (TextView) vi.findViewById(R.id.hour);
            holder.v0 = (Button) vi.findViewById(R.id.text0);
            holder.v1 = (Button) vi.findViewById(R.id.text1);
            holder.v2 = (Button) vi.findViewById(R.id.text2);
            holder.v3 = (Button) vi.findViewById(R.id.text3);
            vi.setTag(holder);      // 把 holder 记到vi中
        } else {
            vi = view;
            holder = (ViewHolder) vi.getTag();      // 取出存储过的 holder
        }
        if (holder != null) {
            String s = data.get(i);
            holder.hour.setText(s);
            holder.v0.setText("0");
            holder.v1.setText("1");
            holder.v2.setText("2");
            holder.v3.setText("3");
        }
        return vi;
    }

    public final class ViewHolder {
        public TextView hour;
        public Button v0;
        public Button v1;
        public Button v2;
        public Button v3;
    }
}
