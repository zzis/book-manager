package com.cbook.zz.cbookmanager.core.model;

/**
 * Created by zz on 31/5/17.
 */

public class TransactionDetail implements IEntity {
    private Transaction transaction;
    private Book book;

    public TransactionDetail(Transaction transaction, Book book) {
        this.transaction = transaction;
        this.book = book;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public Book getBook() {
        return book;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public void setBook(String bookId) {
        this.book = book;
    }

    @Override
    public String getId() {
        return null;
    }
}
