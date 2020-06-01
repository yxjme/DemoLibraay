package com.jewelermobile.gangfu.zdydemo1.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class MyDataBaseHelper extends SQLiteOpenHelper {

    /*数据库名字*/
    public static String name =  "my.db";
    /*数据库 版本*/
    private static int version = 1  ;
    /*表名*/
    public static String table_name = "my_table";
    /*创建表的sql语句*/
    public static String sql = "create table "+table_name+"( "+ Words.Word._ID +" integer  primary key autoincrement,"+
            Words.Word.WORD +" varchar,"+
            Words.Word.DETAIL +" varchar)";

    /**
     *
     * @param context
     */
    public MyDataBaseHelper(@Nullable Context context) {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        /*建表*/
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        /*升级*/
        if (newVersion>oldVersion){
            /*卸载表*/
            String sql_drop_old_table = "drop table if exists "+table_name+"";
            db.execSQL(sql_drop_old_table);
            /*重建*/
            onCreate(db);
        }
    }
}
