package com.cbook.zz.cbookmanager.core.services;

import com.cbook.zz.cbookmanager.core.model.Member;

import java.util.List;

/**
 * Created by zz on 31/5/17.
 */

public interface IMemberService {
    void registerMember(Member member);
    void updateMemberInfo(Member member);
    void deleteMember(Member member);
    List<Member> getAllMembers();
    Member getMemberById(String memberId);
    boolean isExist(String memberId);
}
