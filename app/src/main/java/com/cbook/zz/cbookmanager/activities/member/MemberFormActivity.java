package com.cbook.zz.cbookmanager.activities.member;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.cbook.zz.cbookmanager.Dao.UnitOfWork;
import com.cbook.zz.cbookmanager.R;
import com.cbook.zz.cbookmanager.activities.book.BookFormActivity;
import com.cbook.zz.cbookmanager.activities.book.BookScannerActivity;
import com.cbook.zz.cbookmanager.core.Dao.IUnitOfWork;
import com.cbook.zz.cbookmanager.core.model.Book;
import com.cbook.zz.cbookmanager.core.model.Member;
import com.cbook.zz.cbookmanager.core.services.IBookService;
import com.cbook.zz.cbookmanager.core.services.IMemberService;
import com.cbook.zz.cbookmanager.services.BookService;
import com.cbook.zz.cbookmanager.services.MemberService;

public class MemberFormActivity extends AppCompatActivity {

    private String memberId;

    private EditText memberNameView;
    private EditText memberPhoneView;
    private TextView memberIdView;
    private EditText memberEmailView;
    private EditText memberAddressView;

    private IMemberService memberService;
    private IUnitOfWork unitOfWork;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_form);

        this.memberIdView = (TextView) findViewById(R.id.member_id);
        this.memberNameView = (EditText) findViewById(R.id.member_name);
        this.memberPhoneView = (EditText) findViewById(R.id.member_phone);
        this.memberEmailView = (EditText) findViewById(R.id.member_email);
        this.memberAddressView = (EditText) findViewById(R.id.member_address);

        Bundle bundle = this.getIntent().getExtras();
        this.memberId = bundle.getString("member_id");

        this.memberIdView.setText(this.memberId);
        this.unitOfWork = UnitOfWork.getInstance(this);
        this.memberService = new MemberService(this.unitOfWork);
    }

    /*
    on confirm button click
     */
    public void confirm(View view){
        Member member = new Member(this.memberId,
                this.memberNameView.getText().toString(),
                this.memberPhoneView.getText().toString(),
                this.memberAddressView.getText().toString(),
                this.memberEmailView.getText().toString()
        );
        this.memberService.registerMember(member);
        Intent intent = new Intent(MemberFormActivity.this, MemberManagementActivity.class);
        this.startActivity(intent);
        this.finish();
    }
}
