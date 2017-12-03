package com.cbook.zz.cbookmanager.activities.order;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.cbook.zz.cbookmanager.Dao.UnitOfWork;
import com.cbook.zz.cbookmanager.R;
import com.cbook.zz.cbookmanager.activities.book.BookFormActivity;
import com.cbook.zz.cbookmanager.activities.book.BookScannerActivity;
import com.cbook.zz.cbookmanager.activities.member.MemberManagementActivity;
import com.cbook.zz.cbookmanager.core.Dao.IUnitOfWork;
import com.cbook.zz.cbookmanager.core.model.Member;
import com.cbook.zz.cbookmanager.core.services.IBookService;
import com.cbook.zz.cbookmanager.core.services.IMemberService;
import com.cbook.zz.cbookmanager.services.BookService;
import com.cbook.zz.cbookmanager.services.MemberService;
import com.google.zxing.Result;
import com.google.zxing.client.result.ParsedResult;
import com.mylhyl.zxing.scanner.OnScannerCompletionListener;
import com.mylhyl.zxing.scanner.ScannerView;

public class BorrowBookScanMemberActivity extends AppCompatActivity {

    private ScannerView scannerView;

    private IMemberService memberService;
    private IUnitOfWork unitOfWork;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borrow_book_scan_member);
        this.unitOfWork = UnitOfWork.getInstance(this);
        this.memberService = new MemberService(this.unitOfWork);

        this.scannerView = (ScannerView) findViewById(R.id.borrow_book_member_scanner_view);
        this.scannerView.setOnScannerCompletionListener(new OnScannerCompletionListener() {
            @Override
            public void OnScannerCompletion(Result rawResult, ParsedResult parsedResult, Bitmap barcode) {

                if(!memberService.isExist(rawResult.getText())){
//                    Toast.makeText(getApplicationContext(), rawResult.getText() + "会员不存在 请先注册会员", Toast.LENGTH_LONG).show();
                    dialog(rawResult.getText());
                    return;
                }

                Intent intent = new Intent(BorrowBookScanMemberActivity.this, OrderDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("member_id", rawResult.getText());
                intent.putExtras(bundle);
                startActivity(intent);
                finish();
            }
        });
        this.scannerView.setMediaResId(R.raw.beep);


    }

    protected void dialog(String memberId) {
        AlertDialog.Builder builder = new AlertDialog.Builder(BorrowBookScanMemberActivity.this);
        Member member = memberService.getMemberById(memberId);

        builder.setMessage("会员不存在 请先注册会员");
        builder.setTitle("错误信息");

        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
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

    @Override
    protected void onResume() {
        this.scannerView.onResume();
        super.onResume();
    }

    @Override
    protected void onPause() {
        this.scannerView.onPause();
        super.onPause();
    }
}
