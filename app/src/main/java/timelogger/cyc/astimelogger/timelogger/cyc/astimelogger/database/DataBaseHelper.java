package timelogger.cyc.astimelogger.timelogger.cyc.astimelogger.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
        return this.mDb;
    }

    public void close()
    {
        this.mDb.close();
        this.mDbHelper.close();
    }


    private void ContentValuesPut(ContentValues contentValues, String key, Object value)
    {
        if (value == null)
        {
            contentValues.put(key, "");
        } else
        {
            String className = value.getClass().getName();
            if (className.equals("java.lang.String"))
            {
                contentValues.put(key, value.toString());
            } else if (className.equals("java.lang.Integer"))
            {
                contentValues.put(key, Integer.valueOf(value.toString()));
            } else if (className.equals("java.lang.Float"))
            {
                contentValues.put(key, Float.valueOf(value.toString()));
            } else if (className.equals("java.lang.Double"))
            {
                contentValues.put(key, Double.valueOf(value.toString()));
            } else if (className.equals("java.lang.Boolean"))
            {
                contentValues.put(key, Boolean.valueOf(value.toString()));
            } else if (className.equals("java.lang.Long"))
            {
                contentValues.put(key, Long.valueOf(value.toString()));
            } else if (className.equals("java.lang.Short"))
            {
                contentValues.put(key, Short.valueOf(value.toString()));
            }
        }
    }

    public boolean insert(String tableName, String[] columns, Object[] values)
    {
        ContentValues contentValues = new ContentValues();
        for (int i = 0, imax = columns.length; i < imax; ++i)
        {
            ContentValuesPut(contentValues, columns[i], values[i]);
        }
        long rowId = this.mDb.insert(tableName, null, contentValues);
        return rowId != -1;

    }

    public boolean insert(String tableName, Map<String, Object> columnValues)
    {
        ContentValues contentValues = new ContentValues();
        Iterator iterator = columnValues.keySet().iterator();
        while (iterator.hasNext())
        {
            String key = (String) iterator.next();
            this.ContentValuesPut(contentValues, key, columnValues.get(key));
        }
        long rowId = this.mDb.insert(tableName, null, contentValues);
        return rowId != -1;
    }

    private String initWhereSqlFromArray(String[] whereColumns)
    {
        StringBuffer whereStr = new StringBuffer();
        for (int i = 0, imax = whereColumns.length; i < imax; ++i)
        {
            whereStr.append(whereColumns[i]).append(" = ? ");
            if (i < whereColumns.length - 1)
            {
                whereStr.append(" and ");
            }
        }
        return whereStr.toString();
    }

    private Map<String, Object> initWhereSqlFromMap(Map<String, String> whereParams)
    {
        Set set = whereParams.keySet();
        String[] tmp = new String[whereParams.size()];
        int i = 0;
        Iterator iterator = set.iterator();
        StringBuffer whereStr = new StringBuffer();
        while (iterator.hasNext())
        {
            String key = (String) iterator.next();
            whereStr.append(key).append(" = ? ");
            tmp[i] = whereParams.get(key);
            if (i < set.size() - 1)
            {
                whereStr.append(" and ");
            }
            ++i;
        }
        HashMap result = new HashMap();
        result.put("whereSql", whereStr);
        result.put("whereSqlParam", tmp);
        return result;
    }

    public boolean update(String tableName, String[] columns, Object[] values, String[] whereColumns, String[] whereArgs)
    {
        ContentValues contentValues = new ContentValues();
        for (int i = 0, imax = columns.length; i < imax; ++i)
        {
            this.ContentValuesPut(contentValues, columns[i], values[i]);
        }
        String whereClause = this.initWhereSqlFromArray(whereColumns);
        int rowNumber = this.mDb.update(tableName, contentValues, whereClause, whereArgs);
        return rowNumber > 0;
    }

    public boolean update(String tableName, Map<String, Object> columnValues, Map<String, String> whereParam)
    {
        ContentValues contentValues = new ContentValues();
        Iterator iterator = columnValues.keySet().iterator();
        String columns;
        while (iterator.hasNext())
        {
            columns = (String) iterator.next();
            ContentValuesPut(contentValues, columns, contentValues.get(columns));
        }

        Map map = this.initWhereSqlFromMap(whereParam);
        int rowNumber = this.mDb.update(tableName, contentValues, (String) map.get("whereSql"), (String[]) map.get("whereSqlParam"));
        return rowNumber > 0;
    }

    public boolean delete(String tableName, String[] whereColumns, String[] whereParam)
    {
        String whereStr = this.initWhereSqlFromArray(whereColumns);
        int rowNumber = this.mDb.delete(tableName, whereStr, whereParam);
        return rowNumber > 0;
    }

    public boolean delete(String tableName, Map<String, String> whereParam)
    {
        Map map = this.initWhereSqlFromMap(whereParam);
        int rowNumber = this.mDb.delete(tableName, map.get("whereSql").toString(), (String[]) map.get("whereSqlParam"));
        return rowNumber > 0;
    }

    public List<Map> queryListMap(String sql, String[] params)
    {
        ArrayList list = new ArrayList();
        Cursor cursor = this.mDb.rawQuery(sql, params);
        int columnCount = cursor.getColumnCount();
        while (cursor.moveToNext())
        {
            HashMap item = new HashMap();
            for (int i = 0; i < columnCount; ++i)
            {
                int type = cursor.getType(i);
                switch (type)
                {
                    case 0:
                        item.put(cursor.getColumnName(i), null);
                        break;
                    case 1:
                        item.put(cursor.getColumnName(i), cursor.getInt(i));
                        break;
                    case 2:
                        item.put(cursor.getColumnName(i), cursor.getFloat(i));
                        break;
                    case 3:
                        item.put(cursor.getColumnName(i), cursor.getString(i));
                        break;
                }   // end switch
            }       // end for
            list.add(item);
        }   // end while
        cursor.close();
        return list;
    }

    public Map queryItemMap(String sql, String[] params)
    {
        Cursor cursor = this.mDb.rawQuery(sql, params);
        HashMap map = new HashMap();
        if (cursor.moveToNext())
        {
            for (int i = 0; i < cursor.getColumnCount(); ++i)
            {
                int type = cursor.getType(i);
                switch (type)
                {
                    case 0:
                        map.put(cursor.getColumnName(i), null);
                        break;
                    case 1:
                        map.put(cursor.getColumnName(i), cursor.getInt(i));
                        break;
                    case 2:
                        map.put(cursor.getColumnName(i), cursor.getFloat(i));
                        break;
                    case 3:
                        map.put(cursor.getColumnName(i), cursor.getString(i));
                        break;
                }   // end switch
            }       // end for
        }
        cursor.close();
        return map;
    }

    public  void execSQL(String sql)
    {
        this.mDb.execSQL(sql);
    }
    public void  execSQL(String sql,Object[] params)
    {
        this.mDb.execSQL(sql,params);
    }


    // DBHelper
    private class DBHelper extends SQLiteOpenHelper
    {
        public DBHelper(Context context, String dbName, SQLiteDatabase.CursorFactory factory, int version)
        {
            super(context, dbName, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db)
        {
            String[] arr = DataBaseHelper.this.mDbCreateSql;
            for (int i = 0; i < arr.length; ++i)
            {
                String sql = arr[i];
                db.execSQL(sql);
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
        {
            String[] arr = DataBaseHelper.this.mDbUpdateSql;
            for (int i = 0, imax = arr.length; i < imax; ++i)
            {
                String sql = arr[i];
                db.execSQL(sql);
            }
        }
    }
}
