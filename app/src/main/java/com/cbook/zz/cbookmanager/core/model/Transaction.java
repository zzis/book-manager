package com.cbook.zz.cbookmanager.core.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by zz on 31/5/17.
 */

public class Transaction implements IEntity {
    private int id;
    private Member member;
    private List<TransactionDetail> transactionDetails;
    private String returnDate;
    private String borrowDate;
    private int status;

    public void setStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;

    }

    public Transaction(Member member) {
        this.member = member;
        this.transactionDetails = new ArrayList<>();
        this.status = 1;
        this.borrowDate = new Date().toString();
    }

    public void setId(int id) {
        this.id = id;
    }

    public Transaction(Member member, List<TransactionDetail> transactionDetails) {
        this.member = member;
        this.transactionDetails = transactionDetails;

    }

    public Transaction(){
    }

    public void addBookToCart(Book book){
        this.transactionDetails.add(new TransactionDetail(this, book));
    }

    public void removeFromCart(Book book){
        for(TransactionDetail detail : this.transactionDetails){
            if(detail.getBook().getId() == book.getId()){
                transactionDetails.remove(detail);
                return;
            }
        }
    }

    public void returnBook(String date, Book book){
        this.transactionDetails.remove(book);
        this.returnDate = date;
    }

    public void borrowBook(String date, Book book){
        this.borrowDate = date;
        this.transactionDetails.add(new TransactionDetail(this, book));
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public void setTransactionDetails(List<TransactionDetail> transactionDetails) {
        this.transactionDetails = transactionDetails;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public void setBorrowDate(String borrowDate) {
        this.borrowDate = borrowDate;
    }

    public Member getMember() {
        return member;
    }

    public List<TransactionDetail> getTransactionDetails() {
        return transactionDetails;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public String getBorrowDate() {
        return borrowDate;
    }

    @Override
    public String getId() {
        return "" + this.id;
    }
}
