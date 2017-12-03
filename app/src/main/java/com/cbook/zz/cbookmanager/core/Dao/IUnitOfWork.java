package com.cbook.zz.cbookmanager.core.Dao;

import com.cbook.zz.cbookmanager.core.model.IEntity;

/**
 * Created by zz on 31/5/17.
 */

public interface IUnitOfWork {

    IRepository getRepository(RepoType repoType);


}
