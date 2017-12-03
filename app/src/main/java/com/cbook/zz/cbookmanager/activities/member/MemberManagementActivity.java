package com.cbook.zz.cbookmanager.activities.member;

import android.content.DialogInterface;
import android.content.Intent;
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
import com.cbook.zz.cbookmanager.activities.MainActivity;
import com.cbook.zz.cbookmanager.activities.book.BookListActivity;
import com.cbook.zz.cbookmanager.core.Dao.IUnitOfWork;
import com.cbook.zz.cbookmanager.core.model.Book;
import com.cbook.zz.cbookmanager.core.model.Member;
import com.cbook.zz.cbookmanager.core.services.IBookService;
import com.cbook.zz.cbookmanager.core.services.IMemberService;
import com.cbook.zz.cbookmanager.services.BookService;
import com.cbook.zz.cbookmanager.services.MemberService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MemberManagementActivity extends AppCompatActivity {

    private IMemberService service;
    private ListView listView;
    private IUnitOfWork unitOfWork;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_management);
        this.unitOfWork = UnitOfWork.getInstance(this);
        service = new MemberService(this.unitOfWork);
        listView = (ListView) this.findViewById(R.id.memberListView);

        List<Member> members = service.getAllMembers();
        List<HashMap<String, Object>> data = new ArrayList<HashMap<String,Object>>();
        for(Member member : members){
            HashMap<String, Object> item = new HashMap<String, Object>();
            item.put("name", member.getName());
            item.put("id", member.getId());
            data.add(item);
        }
        SimpleAdapter adapter = new SimpleAdapter(this, data, R.layout.member_list_item,
                new String[]{
                        "id",
                        "name"
                },
                new int[]{
                        R.id.member_list_item_member_id,
                        R.id.member_list_item_member_name,
                }
        );

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new MemberManagementActivity.ItemClickListener());

    }
    //获取点击事件
    private final class ItemClickListener implements AdapterView.OnItemClickListener {

        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            ListView listView = (ListView) parent;
            HashMap<String, Object> data = (HashMap<String, Object>) listView.getItemAtPosition(position);
            String memberId = data.get("id").toString();
            dialog(memberId);
        }
    }

    protected void dialog(String memberId) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MemberManagementActivity.this);
        Member member = service.getMemberById(memberId);

        builder.setMessage(
                "会员编号：" + member.getId() + "\n " +
                        "会员电话： " + member.getPhone() + "\n" +
                        "会员地址： " + member.getAddress() + "\n" +
                        "电子邮箱： " + member.getEmail() + "\n"
        );
        builder.setTitle("会员信息");

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

    public void addMember(View view){
        Intent intent = new Intent(MemberManagementActivity.this, MemberScannerActivity.class);
        startActivity(intent);
        this.finish();
    }

}
