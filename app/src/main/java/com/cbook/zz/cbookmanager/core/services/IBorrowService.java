package com.cbook.zz.cbookmanager.core.services;

import com.cbook.zz.cbookmanager.core.model.Book;
import com.cbook.zz.cbookmanager.core.model.Member;
import com.cbook.zz.cbookmanager.core.model.Transaction;

/**
 * Created by zz on 31/5/17.
 */

public interface IBorrowService {

    void addToCart(Book book, Transaction transaction);

    Transaction startTransaction(Member member);

    void checkOut(Transaction transaction);
}
