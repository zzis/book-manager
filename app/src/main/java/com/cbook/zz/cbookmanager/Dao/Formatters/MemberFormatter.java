package com.cbook.zz.cbookmanager.Dao.Formatters;

import android.content.ContentValues;
import android.database.Cursor;

import com.cbook.zz.cbookmanager.core.Dao.ISqliteFormatter;
import com.cbook.zz.cbookmanager.core.model.Member;

/**
 * Created by zz on 31/5/17.
 */

public class MemberFormatter implements ISqliteFormatter<Member> {
    @Override
    public Member readEntity(Cursor cursor) {
        Member member = new Member(
                cursor.getString(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3),
                cursor.getString(4)
        );
        return member;
    }

    @Override
    public ContentValues getContentValues(Member entity) {

        ContentValues cv = new ContentValues();
        cv.put("name", entity.getName());
        cv.put("phone", entity.getPhone());
        cv.put("address", entity.getAddress());
        cv.put("email", entity.getEmail());
        cv.put("id", entity.getId());

        return cv;
    }

    @Override
    public String getWhereSentence() {
        return " where id = ?";
    }

    @Override
    public String getTableName() {
        return "member";
    }
}
