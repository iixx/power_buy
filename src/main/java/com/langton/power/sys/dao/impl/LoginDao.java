package com.langton.power.sys.dao.impl;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.langton.power.sys.dao.BaseDao;

@Repository
public class LoginDao extends BaseDao {

    /**
     * 管理员登陆验证
     * @param adminName
     * @param password
     * @return
     */
    public String find(String adminName, String password) {
        String hql = "select o.id from AdminBean o where o.adminName = ? and o.password = ?";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter(0, adminName);
        query.setParameter(1, password);
        Object result = query.uniqueResult();
        if(result != null){
            return result.toString();
        }else{
            return null;
        }
        
    }
}
