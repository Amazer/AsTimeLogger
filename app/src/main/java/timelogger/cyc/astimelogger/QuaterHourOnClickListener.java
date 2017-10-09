package timelogger.cyc.astimelogger;

import android.view.View;

/**
 * Created by cyc on 2017/10/2.
 */


public class QuaterHourOnClickListener implements View.OnClickListener {
    public enum QuaterHourType {View0, View1, View2, View3}

    private QuaterHourType mType;
    private ViewHolder mViewHolder;

    public QuaterHourOnClickListener(ViewHolder holder, QuaterHourType quaterType) {

        this.mType=quaterType;
        this.mViewHolder=holder;
    }

    @Override
    public void onClick(View view) {
        //        DateListAdapter.ViewHolder holder = (DateListAdapter.ViewHolder) view.getTag();
        //        Debug.Log("onClick---view--" + holder.position + "," + holder.curHour);
        Debug.Log("onClick---view--position:"+mViewHolder.position);
        Debug.Log("onClick---view--type:"+mType);
    }
}
