package com.cbook.zz.cbookmanager.Dao.Formatters;

import android.content.ContentValues;
import android.database.Cursor;

import com.cbook.zz.cbookmanager.core.Dao.ISqliteFormatter;
import com.cbook.zz.cbookmanager.core.model.Book;

/**
 * Created by zz on 31/5/17.
 */

public class BookFormatter implements ISqliteFormatter<Book> {
    @Override
    public Book readEntity(Cursor cursor) {
         Book book = new Book(
                cursor.getString(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3),
                cursor.getInt(4)
        );
        return book;
    }

    @Override
    public ContentValues getContentValues(Book entity) {

        ContentValues cv = new ContentValues();
        cv.put("isbn_id", entity.getISBNId());
        cv.put("book_title", entity.getBookTitle());
        cv.put("author", entity.getAuthor());
        cv.put("price", entity.getPrice());
        cv.put("status", entity.getStatus());

        return cv;
    }

    @Override
    public String getWhereSentence() {
        return " where isbn_id = ?";
    }

    @Override
    public String getTableName() {
        return "book";
    }
}
