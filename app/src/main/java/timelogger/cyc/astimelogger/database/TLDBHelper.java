package timelogger.cyc.astimelogger.database;

import android.content.Context;

import timelogger.cyc.astimelogger.R;

/**
 * Created by cyc on 2017/10/18.
 */

public class TLDBHelper extends DataBaseHelper
{
    private  static  TLDBHelper _instance;
    public static TLDBHelper getInstance(Context context)
    {
        if(_instance==null)
        {
            synchronized (DataBaseHelper.class)
            {
                if(_instance==null)
                {
                    _instance=new TLDBHelper(context);
                    if(_instance.getDB()==null||!_instance.getDB().isOpen())
                    {
                        _instance.open();
                    }
                }
            }
        }
        return  _instance;
    }


    private TLDBHelper(Context context)
    {
        super(context);
    }
    @Override
    protected int getDbVersion(Context context)
    {
        String versionStr=context.getResources().getStringArray(R.array.DATABASE_INFO)[1];
//        Debug.Log("db version str:"+versionStr);
        return Integer.valueOf(versionStr);
    }

    @Override
    protected String getDbName(Context context)
    {
        return (context.getResources().getStringArray(R.array.DATABASE_INFO)[0]);
    }

    @Override
    protected String[] getDbCreateSql(Context context)
    {
        return context.getResources().getStringArray(R.array.CREATE_TABLE_SQL);
    }

    @Override
    protected String[] getDbUpdateSql(Context context)
    {
        return context.getResources().getStringArray(R.array.UPDATE_TABLE_SQL);
    }
}
