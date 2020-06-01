package com.jewelermobile.gangfu.zdydemo1.activity;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

public class DictProvider extends ContentProvider {

    String TAG  = "=======TAG=====" ;


    static {
        UriMatcher uriMatcher=new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI("com.jewelermobile.gangfu.zdydemo1.DictProvider","path",1);

//        int code = uriMatcher.match(Uri.parse("content://com.jewelermobile.gangfu.zdydemo1.DictProvider/path"));
    }

    @Override
    public boolean onCreate() {
        Log.v(TAG,"onCreate");

//        ContentUris.withAppendedId(Uri.parse("content://com.jewelermobile.gangfu.zdydemo1.DictProvider/path"),1);
//        ContentUris.parseId(Uri.parse("content://com.jewelermobile.gangfu.zdydemo1.DictProvider/path/1"));

        return false;
    }



    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        Log.v(TAG,"query");
        return null;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        Log.v(TAG,"getType");
        return null;
    }


    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        Log.v(TAG,"insert");
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        Log.v(TAG,"delete");
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        Log.v(TAG,"update");
        return 0;
    }
}
