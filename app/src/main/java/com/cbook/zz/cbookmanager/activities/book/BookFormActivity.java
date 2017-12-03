package com.cbook.zz.cbookmanager.activities.book;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.cbook.zz.cbookmanager.Dao.UnitOfWork;
import com.cbook.zz.cbookmanager.R;
import com.cbook.zz.cbookmanager.core.Dao.IUnitOfWork;
import com.cbook.zz.cbookmanager.core.model.Book;
import com.cbook.zz.cbookmanager.core.services.IBookService;
import com.cbook.zz.cbookmanager.services.BookService;

public class BookFormActivity extends AppCompatActivity {

    private String bookTitle;
    private String price;
    private String ISBNId;
    private String author;

    private EditText bookTitleView;
    private EditText priceView;
    private TextView ISBNIdView;
    private EditText authorView;

    private IBookService bookService;
    private IUnitOfWork unitOfWork;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_form);

        this.bookTitleView = (EditText) findViewById(R.id.book_title);
        this.priceView = (EditText) findViewById(R.id.price);
        this.ISBNIdView = (TextView) findViewById(R.id.book_isbn_id);
        this.authorView = (EditText) findViewById(R.id.book_author);

        Bundle bundle = this.getIntent().getExtras();
        this.ISBNId = bundle.getString("book_isbn_id");

        this.ISBNIdView.setText(this.ISBNId);
        this.unitOfWork = UnitOfWork.getInstance(this);
        this.bookService = new BookService(this.unitOfWork);
    }

    /*
    on confirm button click
     */
    public void confirm(View view){
        Book book = new Book(this.ISBNId,
                this.bookTitleView.getText().toString(),
                this.authorView.getText().toString(),
                this.priceView.getText().toString(),
                1
        );
        this.bookService.addBook(book);
        Intent intent = new Intent(BookFormActivity.this, BookScannerActivity.class);
        this.startActivity(intent);
        this.finish();
    }
}
