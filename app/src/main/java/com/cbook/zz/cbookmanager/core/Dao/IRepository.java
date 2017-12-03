package com.cbook.zz.cbookmanager.core.Dao;

import com.cbook.zz.cbookmanager.core.model.IEntity;

import java.util.List;

/**
 * Created by zz on 31/5/17.
 */

public interface IRepository <T extends IEntity>{

    void add(T entity);
    void delete(T entity);
    void update(T entity);

    List<T> getAll();
    T getById(String id);
    RepoType getRepoType();
    IUnitOfWork getUnitOfWork();
}
