package timelogger.cyc.astimelogger.timelogger.cyc.astimelogger.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Created by cyc on 2017/10/18.
 */

public class CommonDBHelper extends SQLiteOpenHelper
{
//    private static final String DB_NAME = "db_timelogger.db";
//    private static final int DB_VERSION = 1;
//    private static String TABLE_EVENTS_NAME = "t_events";
//    private static String TABLE_ITEMS_NAME = "t_items";
//
//    private static final String CREATE_TABLE_EVENTS = "";
//    private static final String CREATE_TABLE_ITEMS = "";

    private SQLiteDatabase db;

    public CommonDBHelper(Context context, String dbName, SQLiteDatabase.CursorFactory factory, int version)
    {
        super(context, dbName, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase)
    {
        this.db = sqLiteDatabase;
//        db.execSQL(CREATE_TABLE_EVENTS);
//        db.execSQL(CREATE_TABLE_ITEMS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion)
    {

    }

    public void insert(ContentValues values, String tableName)
    {
        db = getWritableDatabase();
        db.insert(tableName, null, values);
        db.close();
    }

    public Cursor query(String tableName)
    {
        db = getWritableDatabase();
        Cursor c = db.query(tableName, null, null, null, null, null, null);
        return c;
    }

    public Cursor rawQuery(String sql, String[] args)
    {
        db = getWritableDatabase();
        Cursor c = db.rawQuery(sql, args);
        return c;
    }

    public void  execSQL(String sql)
    {
        db=getWritableDatabase();
        db.execSQL(sql);
    }

    public void  del(String tableName,int id)
    {
        if(db==null)
        {
            db=getWritableDatabase();
        }
        db.delete(tableName,"id=?",new String[]{String.valueOf(id)});
    }
    public void close()
    {
        if(db!=null)
        {
            db.close();
        }
    }

    // bitmap to byte[]
    public  byte[] bmpToByteArray(Bitmap bmp)
    {
        // default size is 32 bytes
        ByteArrayOutputStream bos=new ByteArrayOutputStream();
        try
        {
            bmp.compress(Bitmap.CompressFormat.JPEG,100,bos);
            bos.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return bos.toByteArray();

    }

}
