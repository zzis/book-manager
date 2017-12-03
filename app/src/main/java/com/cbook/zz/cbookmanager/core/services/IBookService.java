package com.cbook.zz.cbookmanager.core.services;

import com.cbook.zz.cbookmanager.core.model.Book;
import com.cbook.zz.cbookmanager.services.BookService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zz on 31/5/17.
 */

public interface IBookService {

    List<Book> getAllBooks();

    Book getBookById(String bookId);

    void updateBookInfo(Book book);

    void addBook(Book book);

    void deleteBook(Book book);

    boolean isExist(String bookId);
}
