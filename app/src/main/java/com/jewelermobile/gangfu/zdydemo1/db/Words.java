package com.jewelermobile.gangfu.zdydemo1.db;

import android.content.UriMatcher;
import android.net.Uri;
import android.provider.BaseColumns;

public final class Words {

    public static final String authorities="com.jewelermobile.gangfu.zdydemo1.DictProvider";

    public static final class Word implements BaseColumns{
        public static final String _ID = "_id" ;
        public static final String WORD = "word" ;
        public static final String DETAIL = "detail" ;




    }
}
