package timelogger.cyc.astimelogger;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
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
            holder=new ViewHolder();
            holder.v0 = (TextView) vi.findViewById(R.id.text0);
            holder.v1 = (TextView) vi.findViewById(R.id.text1);
            holder.v2 = (TextView) vi.findViewById(R.id.text2);
            holder.v3 = (TextView) vi.findViewById(R.id.text3);
        }
        else
        {
            holder=(ViewHolder)vi.getTag();
        }
        String s = data.get(i);
        holder.v0.setText(s + "0");
        holder.v1.setText(s + "1");
        holder.v2.setText(s + "2");
        holder.v3.setText(s + "3");

        return vi;
    }

    public final class ViewHolder {
        public TextView v0;
        public TextView v1;
        public TextView v2;
        public TextView v3;
    }
}
