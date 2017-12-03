package com.cbook.zz.cbookmanager.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.cbook.zz.cbookmanager.R;
import com.cbook.zz.cbookmanager.activities.book.BookListActivity;
import com.cbook.zz.cbookmanager.activities.book.BookScannerActivity;
import com.cbook.zz.cbookmanager.activities.member.MemberManagementActivity;
import com.cbook.zz.cbookmanager.activities.order.BorrowBookScanBookActivity;
import com.cbook.zz.cbookmanager.activities.order.BorrowBookScanMemberActivity;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void scanBook(View view){
        if (ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            //权限还没有授予，需要在这里写申请权限的代码
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{Manifest.permission.CAMERA}, 60);
        } else {
            //权限已经被授予，在这里直接写要执行的相应方法即可
            Intent intent = new Intent(MainActivity.this, BookScannerActivity.class);
            startActivity(intent);
        }
    }

    public void goToBookList(View view){
        Intent intent = new Intent(MainActivity.this, BookListActivity.class);
        startActivity(intent);
    }

    public void memberManagement(View view){
        Intent intent = new Intent(MainActivity.this, MemberManagementActivity.class);
        startActivity(intent);
    }

    public void orderManagement(View view){

    }

    public void borrowBook(View view){

        if (ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            //权限还没有授予，需要在这里写申请权限的代码
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{Manifest.permission.CAMERA}, 60);
        } else {
            //权限已经被授予，在这里直接写要执行的相应方法即可
            Intent intent = new Intent(MainActivity.this, BorrowBookScanMemberActivity.class);
            startActivity(intent);
        }
    }



}
