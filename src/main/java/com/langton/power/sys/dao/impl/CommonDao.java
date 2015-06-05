package com.langton.power.sys.dao.impl;

import org.springframework.stereotype.Repository;

import com.langton.power.sys.dao.BaseDao;

@Repository
public class CommonDao extends BaseDao {
    
    public void save(Object bean){
        super.getTemplate().save(bean);
    }
}
