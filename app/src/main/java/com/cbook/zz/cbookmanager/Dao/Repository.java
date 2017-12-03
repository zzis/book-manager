package com.cbook.zz.cbookmanager.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.cbook.zz.cbookmanager.core.Dao.IRepository;
import com.cbook.zz.cbookmanager.core.Dao.ISqliteFormatter;
import com.cbook.zz.cbookmanager.core.Dao.IUnitOfWork;
import com.cbook.zz.cbookmanager.core.Dao.RepoType;
import com.cbook.zz.cbookmanager.core.model.IEntity;
import com.cbook.zz.cbookmanager.util.DBHelper;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;

/**
 * Created by zz on 31/5/17.
 */

public class Repository<T extends IEntity> implements IRepository<T> {

    protected DBHelper helper;
    protected SQLiteDatabase db;
    private ISqliteFormatter formatter;

    private IUnitOfWork unitOfWork;

    public Repository(IUnitOfWork unitOfWork, Context context, ISqliteFormatter formatter) {
        this.helper = new DBHelper(context);
        db = helper.getWritableDatabase();
        this.unitOfWork = unitOfWork;
        this.formatter = formatter;
    }

    @Override
    public void add(T entity) {
        this.db.insert(this.formatter.getTableName(), null, this.formatter.getContentValues(entity));
    }

    @Override
    public void delete(T entity) {
        this.db.delete(this.formatter.getTableName(), this.formatter.getWhereSentence(), new String[]{entity.getId()});
    }

    @Override
    public void update(T entity) {
        this.db.update(
                this.formatter.getTableName(),
                this.formatter.getContentValues(entity),
                this.formatter.getWhereSentence(),
                new String[]{entity.getId()}
        );
    }

    @Override
    public List<T> getAll() {
        Cursor cursor = this.db.rawQuery("select * from " + this.formatter.getTableName(), null);
        List<T> list = new ArrayList<>();
        while (cursor.moveToNext()){
            list.add((T)this.formatter.readEntity(cursor));
        }
        return list;
    }

    @Override
    public T getById(String id) {
        Cursor cursor = this.db.rawQuery("select * from " + this.formatter.getTableName() + this.formatter.getWhereSentence(), new String[]{id});
        if(cursor.moveToNext()){
            return (T)this.formatter.readEntity(cursor);
        } else{
            return null;
        }

    }

    @Override
    public RepoType getRepoType() {
        return null;
    }

    @Override
    public IUnitOfWork getUnitOfWork() {
        return this.unitOfWork;
    }
}
