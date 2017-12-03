package com.cbook.zz.cbookmanager.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/**
 * Created by zz on 28/5/17.
 */


public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "cbook.db";
    private static final int DATABASE_VERSION = 1;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // for column status, 0 means out of stock, 1 means in stock
        db.execSQL("CREATE TABLE IF NOT EXISTS book" +
                "(isbn_id VARCHAR PRIMARY KEY, " +
                "book_title VARCHAR, " +
                "author VARCHAR, " +
                "price VARCHAR, " +
                "status INT)"
        );

        db.execSQL("CREATE TABLE IF NOT EXISTS member(" +
                "id VARCHAR PRIMARY KEY, " +
                "name VARCHAR, " +
                "phone VARCHAR, " +
                "address VARCHAR, " +
                "email VARCHAR)"
        );

        db.execSQL("CREATE TABLE IF NOT EXISTS book_order(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "member_id VARCHAR," +
                "status INT," +
                "return_date VARCHAR," +
                "borrow_date VARCHAR," +
                "FOREIGN KEY (member_id) REFERENCES member(id))"
        );

        db.execSQL("CREATE TABLE IF NOT EXISTS order_detail(" +
                "order_id INT," +
                "book_id VARCHAR," +
                "FOREIGN KEY (order_id) REFERENCES book_order(id)," +
                "FOREIGN KEY (book_id) REFERENCES book(isbn_id))"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}