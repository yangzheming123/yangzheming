package com.byted.camp.todolist.db;

import android.provider.BaseColumns;

import com.byted.camp.todolist.operation.db.FeedReaderContract;

/**
 * Created on 2019/1/22.
 *
 * @author xuyingyi@bytedance.com (Yingyi Xu)
 */
public final class TodoContract {

    // TODO 定义表结构和 SQL 语句常量
    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TodoContract.FeedEntry.TABLE_NAME + " (" +
                    TodoContract.FeedEntry._ID + " INTEGER PRIMARY KEY," +
                    TodoContract.FeedEntry.COLUMN_NAME_TITLE + " TEXT," +
                    TodoContract.FeedEntry.COLUMN_NAME_SUBTITLE + " TEXT,"+
                    TodoContract.FeedEntry.COLUMN_NAME_PRIORITY + " TEXT)";

    public static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + FeedReaderContract.FeedEntry.TABLE_NAME;

    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.

    /* Inner class that defines the table contents */
    public static class FeedEntry implements BaseColumns {

        public static final String TABLE_NAME = "entry";

        public static final String COLUMN_NAME_TITLE = "title";

        public static final String COLUMN_NAME_SUBTITLE = "subtitle";

        public static final String COLUMN_NAME_PRIORITY = "priority";

        public static final String COLUMN_NAME_CONTENT = "content";
    }
    private TodoContract() {
    }

}
