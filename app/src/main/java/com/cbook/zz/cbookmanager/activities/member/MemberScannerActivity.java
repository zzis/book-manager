package com.cbook.zz.cbookmanager.activities.member;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.cbook.zz.cbookmanager.Dao.UnitOfWork;
import com.cbook.zz.cbookmanager.R;
import com.cbook.zz.cbookmanager.activities.book.BookFormActivity;
import com.cbook.zz.cbookmanager.activities.book.BookScannerActivity;
import com.cbook.zz.cbookmanager.core.Dao.IUnitOfWork;
import com.cbook.zz.cbookmanager.core.services.IBookService;
import com.cbook.zz.cbookmanager.core.services.IMemberService;
import com.cbook.zz.cbookmanager.services.BookService;
import com.cbook.zz.cbookmanager.services.MemberService;
import com.google.zxing.Result;
import com.google.zxing.client.result.ParsedResult;
import com.mylhyl.zxing.scanner.OnScannerCompletionListener;
import com.mylhyl.zxing.scanner.ScannerView;

public class MemberScannerActivity extends AppCompatActivity {

    public static final String EXTRA_LASER_LINE_MODE = "laser_line_mode";
    public static final String EXTRA_RETURN_SCANNER_RESULT = "return_scanner_result";
    public static final int REQUEST_CODE_SCANNER = 188;
    public static final int EXTRA_LASER_LINE_MODE_0 = 0;
    public static final int EXTRA_LASER_LINE_MODE_1 = 1;
    public static final int EXTRA_LASER_LINE_MODE_2 = 2;
    public static final int APPLY_READ_EXTERNAL_STORAGE = 0x111;
    private ScannerView scannerView;

    private IMemberService memberService;
    private IUnitOfWork unitOfWork;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_scanner);
        this.unitOfWork = UnitOfWork.getInstance(this);
        this.memberService = new MemberService(this.unitOfWork);

        this.scannerView = (ScannerView) findViewById(R.id.member_scanner_view);
        this.scannerView.setOnScannerCompletionListener(new OnScannerCompletionListener() {
            @Override
            public void OnScannerCompletion(Result rawResult, ParsedResult parsedResult, Bitmap barcode) {

                if(memberService.isExist(rawResult.getText())){
                    Toast.makeText(getApplicationContext(), rawResult.getText() + "用户已存在", Toast.LENGTH_LONG).show();
                    return;
                }

                Intent intent = new Intent(MemberScannerActivity.this, MemberFormActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("member_id", rawResult.getText());
                intent.putExtras(bundle);
                startActivity(intent);
                finish();
            }
        });
        this.scannerView.setMediaResId(R.raw.beep);


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
