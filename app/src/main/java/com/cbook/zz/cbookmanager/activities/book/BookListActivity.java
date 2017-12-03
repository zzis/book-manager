package com.cbook.zz.cbookmanager.activities.book;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.cbook.zz.cbookmanager.Dao.UnitOfWork;
import com.cbook.zz.cbookmanager.R;
import com.cbook.zz.cbookmanager.core.Dao.IUnitOfWork;
import com.cbook.zz.cbookmanager.core.services.IBookService;
import com.cbook.zz.cbookmanager.services.BookService;
import com.cbook.zz.cbookmanager.core.model.Book;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BookListActivity extends AppCompatActivity {

    private IBookService service;
    private ListView listView;
    private IUnitOfWork unitOfWork;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);
        this.unitOfWork = UnitOfWork.getInstance(this);
        service = new BookService(this.unitOfWork);
        listView = (ListView) this.findViewById(R.id.listView);

        List<Book> books = service.getAllBooks();
        List<HashMap<String, Object>> data = new ArrayList<HashMap<String,Object>>();
        for(Book book : books){
            HashMap<String, Object> item = new HashMap<String, Object>();
            item.put("isbn", book.getISBNId());
            item.put("title", book.getBookTitle());
//            item.put("author", book.getAuthor());
//            item.put("price", book.getPrice());
            data.add(item);
        }
        SimpleAdapter adapter = new SimpleAdapter(this, data, R.layout.book_list_item,
                new String[]{
                        "isbn",
                        "title"
//                        "author",
//                        "price"
                },
                new int[]{
                        R.id.list_item_isbn,
                        R.id.list_item_title,
//                        R.id.list_item_author,
//                        R.id.list_item_price
                }
        );

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new ItemClickListener());

    }
    //获取点击事件
    private final class ItemClickListener implements AdapterView.OnItemClickListener {

        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            ListView listView = (ListView) parent;
            HashMap<String, Object> data = (HashMap<String, Object>) listView.getItemAtPosition(position);
            String bookId = data.get("isbn").toString();
            Toast.makeText(getApplicationContext(), bookId, Toast.LENGTH_LONG).show();
            dialog(bookId);
        }
    }

    protected void dialog(String isbn) {
        AlertDialog.Builder builder = new AlertDialog.Builder(BookListActivity.this);
        Book book = service.getBookById(isbn);

        String status = book.getStatus() == 1 ? "在库" : "已借出";

        builder.setMessage(
                "书籍编码：" + book.getISBNId() + "\n " +
                        "书名： " + book.getBookTitle() + "\n" +
                        "作者： " + book.getAuthor() + "\n" +
                        "价格： " + book.getPrice() + "\n" +
                        "当前状态： " + status + "\n"
                        );
        builder.setTitle("书籍信息");

        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
//                BookListActivity.this.finish();
            }

        });

        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.create().show();
    }

}
