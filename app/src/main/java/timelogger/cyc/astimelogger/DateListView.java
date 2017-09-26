package timelogger.cyc.astimelogger;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ListView;

/**
 * Created by cyc on 2017/9/23.
 */

public class DateListView extends ListView {
    // region  Constructor
    public DateListView(Context context) {
        super(context);
    }

    public DateListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DateListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public DateListView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public void addHeaderView(View v, Object data, boolean isSelectable) {
        super.addHeaderView(v, data, isSelectable);
    }

    @Override
    public void setDivider(@Nullable Drawable divider) {
        super.setDivider(divider);
    }
    // endregion
}
