package com.cbook.zz.cbookmanager.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.cbook.zz.cbookmanager.core.Dao.ISqliteFormatter;
import com.cbook.zz.cbookmanager.core.Dao.IUnitOfWork;
import com.cbook.zz.cbookmanager.core.Dao.RepoType;
import com.cbook.zz.cbookmanager.core.model.Book;
import com.cbook.zz.cbookmanager.core.model.Transaction;
import com.cbook.zz.cbookmanager.core.model.TransactionDetail;
import com.cbook.zz.cbookmanager.util.DBHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zz on 1/6/17.
 */

public class TransactionDetailRepository extends Repository<TransactionDetail>{

    public TransactionDetailRepository(IUnitOfWork unitOfWork, Context context){
        super(unitOfWork, context, null);

    }

    public List<TransactionDetail> getById(Transaction transaction){
        Cursor cursor = this.db.rawQuery("select * from order_detail where order_id = ?", new String[]{transaction.getId()});
        List<TransactionDetail> list = new ArrayList<>();
        while (cursor.moveToNext()){
            list.add(new TransactionDetail(transaction, (Book) this.getUnitOfWork().getRepository(RepoType.Book).getById(cursor.getString(1))));
        }
        return list;
    }

    public void add(TransactionDetail transactionDetail){
        ContentValues cv = new ContentValues();
        cv.put("order_id", transactionDetail.getTransaction().getId());
        cv.put("book_id", transactionDetail.getBook().getId());
        this.db.insert("order_detail", null, cv);
    }

    public void delete(TransactionDetail transactionDetail){
        this.db.delete("order_detail", "order_id = ? and book_id = ?", new String[]{transactionDetail.getTransaction().getId(), transactionDetail.getBook().getId()});
    }
}
