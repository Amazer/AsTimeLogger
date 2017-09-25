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
    private  static LayoutInflater inflater=null;
    public  DateListAdapter(Context context,ArrayList<String> d)
    {
        data=d;
        inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
    public View getView(int i, View view, ViewGroup viewGroup)
    {
        View vi=view;
        if(vi==null)
        {
            vi=inflater.inflate(R.layout.listview_item,null);
        }
        TextView t0=(TextView)vi.findViewById(R.id.text0);
        TextView t1=(TextView)vi.findViewById(R.id.text1);
        TextView t2=(TextView)vi.findViewById(R.id.text2);
        TextView t3=(TextView)vi.findViewById(R.id.text3);

        String s=data.get(i);
        t0.setText(s+"0");
        t1.setText(s+"1");
        t2.setText(s+"2");
        t3.setText(s+"3");

        return vi;
    }
}
