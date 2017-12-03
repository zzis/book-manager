package com.cbook.zz.cbookmanager.Dao;

import android.content.Context;

import com.cbook.zz.cbookmanager.Dao.Formatters.BookFormatter;
import com.cbook.zz.cbookmanager.Dao.Formatters.MemberFormatter;
import com.cbook.zz.cbookmanager.Dao.Formatters.TransactionFormatter;
import com.cbook.zz.cbookmanager.core.Dao.IRepository;
import com.cbook.zz.cbookmanager.core.Dao.IUnitOfWork;
import com.cbook.zz.cbookmanager.core.Dao.RepoType;
import com.cbook.zz.cbookmanager.core.model.Book;
import com.cbook.zz.cbookmanager.core.model.Member;
import com.cbook.zz.cbookmanager.core.model.Transaction;

/**
 * Created by zz on 31/5/17.
 */

public class UnitOfWork implements IUnitOfWork {

    private static UnitOfWork unitOfWork;

    private IRepository<Book> bookRepository;
    private IRepository<Member> memberRepository;
    private IRepository<Transaction> transactionRepository;
    private TransactionDetailRepository transactionDetailRepository;


    public static UnitOfWork getInstance(Context context){
        if(unitOfWork == null){
            unitOfWork = new UnitOfWork(context);
        }
        return unitOfWork;
    }

    private UnitOfWork(Context context){
        this.bookRepository = new Repository<>(this, context, new BookFormatter());
        this.memberRepository = new Repository<>(this, context, new MemberFormatter());
        this.transactionRepository = new Repository<>(this, context, new TransactionFormatter(this));
        this.transactionDetailRepository = new TransactionDetailRepository(this, context);
    }

    @Override
    public IRepository getRepository(RepoType repoType) {
        switch (repoType){
            case Book:
                return this.bookRepository;
            case Member:
                return this.memberRepository;
            case Transaction:
                return this.transactionRepository;
            case TransactionDetail:
                return this.transactionDetailRepository;
        }
        return null;
    }

    public TransactionDetailRepository getTransactionDetailRepo(){
        return this.transactionDetailRepository;
    }

}
