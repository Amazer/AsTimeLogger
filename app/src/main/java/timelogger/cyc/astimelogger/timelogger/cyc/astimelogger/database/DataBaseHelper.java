package timelogger.cyc.astimelogger.timelogger.cyc.astimelogger.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by cyc on 2017/10/18.
 */

public abstract class DataBaseHelper
{
    protected DBHelper mDbHelper;
    protected SQLiteDatabase mDb;

    private int mDbVersion;
    private String mDbName;

    private String[] mDbCreateSql;

    private String[] mDbUpdateSql;

    protected abstract int getDbVersion(Context context);

    protected abstract String getDbName(Context context);

    protected abstract String[] getDbCreateSql(Context context);

    protected abstract String[] getDbUpdateSql(Context context);

    private  SQLiteDatabase db;

    public DataBaseHelper(Context context)
    {
        this.mDbVersion = this.getDbVersion(context);
        this.mDbName = this.getDbName(context);
        this.mDbCreateSql = this.getDbCreateSql(context);
        this.mDbUpdateSql = this.getDbUpdateSql(context);
        this.mDbHelper = new DBHelper(context, this.mDbName, null, this.mDbVersion);
    }

    protected void open()
    {
        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                mDb = mDbHelper.getWritableDatabase();
            }
        }).start();
    }

    protected SQLiteDatabase getDB()
    {
        return  this.mDb;
    }

    public void  close()
    {
        this.mDb.close();
        this.mDbHelper.close();
    }
    private class DBHelper extends SQLiteOpenHelper
    {
        public DBHelper(Context context, String dbName, SQLiteDatabase.CursorFactory factory, int version)
        {
            super(context, dbName, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase)
        {
            String[] arr=DataBaseHelper.this.mDbCreateSql;
            for(int i=0;i<arr.length;++i)
            {
                String sql=arr[i];
                db.execSQL(sql);
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion)
        {
            String [] arr=DataBaseHelper.this.mDbUpdateSql;
            for(int i=0,imax=arr.length;i<imax;++i)
            {
                String sql=arr[i];
                db.execSQL(sql);
            }
        }
    }
}
