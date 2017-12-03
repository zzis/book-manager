package com.cbook.zz.cbookmanager.Dao.Formatters;

import android.content.ContentValues;
import android.database.Cursor;
import android.support.annotation.Dimension;

import com.cbook.zz.cbookmanager.Dao.UnitOfWork;
import com.cbook.zz.cbookmanager.core.Dao.ISqliteFormatter;
import com.cbook.zz.cbookmanager.core.Dao.RepoType;
import com.cbook.zz.cbookmanager.core.model.Book;
import com.cbook.zz.cbookmanager.core.model.IEntity;
import com.cbook.zz.cbookmanager.core.model.Member;
import com.cbook.zz.cbookmanager.core.model.Transaction;
import com.cbook.zz.cbookmanager.core.model.TransactionDetail;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zz on 31/5/17.
 */

public class TransactionFormatter implements ISqliteFormatter<Transaction> {

    private UnitOfWork unitOfWork;

    public TransactionFormatter(UnitOfWork unitOfWork){
        this.unitOfWork = unitOfWork;
    }

    @Override
    public Transaction readEntity(Cursor cursor) {
        Transaction transaction = new Transaction(new Member(cursor.getString(1)));
        transaction.setReturnDate(cursor.getString(cursor.getColumnIndex("return_date")));
        transaction.setBorrowDate(cursor.getString(cursor.getColumnIndex("borrow_date")));
        transaction.setId(cursor.getInt(cursor.getColumnIndex("id")));
        transaction.setStatus(cursor.getInt(cursor.getColumnIndex("status")));
        transaction.setTransactionDetails(this.unitOfWork.getTransactionDetailRepo().getById(transaction));
        transaction.setMember((Member)this.unitOfWork.getRepository(RepoType.Member).getById(cursor.getString(cursor.getColumnIndex("member_id"))));
        return transaction;
    }

    @Override
    public ContentValues getContentValues(Transaction entity) {

        ContentValues cv = new ContentValues();
        cv.put("return_date", entity.getReturnDate());
        cv.put("status", entity.getStatus());
        cv.put("id", Integer.parseInt(entity.getId()));
        cv.put("borrow_date", entity.getBorrowDate());
        cv.put("member_id", entity.getMember().getId());
        cv.put("id", entity.getId());

        return cv;
    }

    @Override
    public String getWhereSentence() {
        return "where id = ?";
    }

    @Override
    public String getTableName() {
        return "book_order";
    }
}
