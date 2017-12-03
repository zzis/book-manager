package com.cbook.zz.cbookmanager.services;

import com.cbook.zz.cbookmanager.core.Dao.IRepository;
import com.cbook.zz.cbookmanager.core.Dao.IUnitOfWork;
import com.cbook.zz.cbookmanager.core.Dao.RepoType;
import com.cbook.zz.cbookmanager.core.model.Member;
import com.cbook.zz.cbookmanager.core.services.IMemberService;

import java.util.List;

/**
 * Created by zz on 31/5/17.
 */

public class MemberService implements IMemberService {
    private IUnitOfWork unitOfWork;

    public MemberService(IUnitOfWork unitOfWork){
        this.unitOfWork = unitOfWork;
    }

    @Override
    public void registerMember(Member member) {
        IRepository repository = this.unitOfWork.getRepository(RepoType.Member);
        repository.add(member);
    }

    @Override
    public void updateMemberInfo(Member member) {
        IRepository repository = this.unitOfWork.getRepository(RepoType.Member);
        repository.update(member);
    }

    @Override
    public void deleteMember(Member member) {
        this.unitOfWork.getRepository(RepoType.Member).delete(member);
    }

    @Override
    public List<Member> getAllMembers() {
        return this.unitOfWork.getRepository(RepoType.Member).getAll();
    }

    @Override
    public Member getMemberById(String memberId) {
        return (Member) this.unitOfWork.getRepository(RepoType.Member).getById(memberId);
    }

    @Override
    public boolean isExist(String memberId) {
        return this.unitOfWork.getRepository(RepoType.Member).getById(memberId) != null;
    }
}
