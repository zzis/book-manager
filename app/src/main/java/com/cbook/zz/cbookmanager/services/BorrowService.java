package com.cbook.zz.cbookmanager.services;

import com.cbook.zz.cbookmanager.core.Dao.IUnitOfWork;
import com.cbook.zz.cbookmanager.core.Dao.RepoType;
import com.cbook.zz.cbookmanager.core.model.Book;
import com.cbook.zz.cbookmanager.core.model.Member;
import com.cbook.zz.cbookmanager.core.model.Transaction;
import com.cbook.zz.cbookmanager.core.model.TransactionDetail;
import com.cbook.zz.cbookmanager.core.services.IBorrowService;

/**
 * Created by zz on 31/5/17.
 */

public class BorrowService implements IBorrowService{
    private IUnitOfWork unitOfWork;

    public BorrowService(IUnitOfWork unitOfWork){
        this.unitOfWork = unitOfWork;
    }

    @Override
    public void addToCart(Book book, Transaction transaction) {
        transaction.addBookToCart(book);
    }

    @Override
    public Transaction startTransaction(Member member) {
        Transaction transaction = new Transaction(member);
        return transaction;
    }

    @Override
    public void checkOut(Transaction transaction) {
        this.unitOfWork.getRepository(RepoType.Transaction).add(transaction);

        for(TransactionDetail detail : transaction.getTransactionDetails()){
            this.unitOfWork.getRepository(RepoType.TransactionDetail).add(detail);
        }
    }
}
