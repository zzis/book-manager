package com.cbook.zz.cbookmanager.core.Dao;

import android.content.ContentValues;
import android.database.Cursor;

import com.cbook.zz.cbookmanager.core.model.IEntity;

/**
 * Created by zz on 31/5/17.
 */

public interface ISqliteFormatter<T extends IEntity> {
    T readEntity(Cursor cursor);
    ContentValues getContentValues(T entity);
    String getWhereSentence();
    String getTableName();
}
