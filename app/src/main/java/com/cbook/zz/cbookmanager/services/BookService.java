package com.cbook.zz.cbookmanager.services;

import com.cbook.zz.cbookmanager.core.Dao.IUnitOfWork;
import com.cbook.zz.cbookmanager.core.Dao.RepoType;
import com.cbook.zz.cbookmanager.core.model.Book;
import com.cbook.zz.cbookmanager.core.services.IBookService;

import java.util.List;

/**
 * Created by zz on 29/5/17.
 */

public class BookService implements IBookService{

    private IUnitOfWork unitOfWork;

    public BookService(IUnitOfWork unitOfWork){
        this.unitOfWork = unitOfWork;
    }



    public List<Book> getAllBooks(){
        return this.unitOfWork.getRepository(RepoType.Book).getAll();
    }

    public Book getBookById(String bookId){
        return (Book) this.unitOfWork.getRepository(RepoType.Book).getById(bookId);
    }

    @Override
    public void updateBookInfo(Book book) {
        this.unitOfWork.getRepository(RepoType.Book).update(book);
    }

    @Override
    public void addBook(Book book) {
        this.unitOfWork.getRepository(RepoType.Book).add(book);
    }

    @Override
    public void deleteBook(Book book) {
        this.unitOfWork.getRepository(RepoType.Book).delete(book);
    }

    @Override
    public boolean isExist(String bookId) {
        return this.getBookById(bookId) != null;
    }
}
