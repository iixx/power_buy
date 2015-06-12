package com.langton.power.sys.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.langton.power.sys.dao.BaseDao;

@Repository
public class CommonDao extends BaseDao {
    
    public void save(Object bean){
        super.getTemplate().save(bean);
    }
    
    public void delete(Object bean){
        HibernateTemplate t = super.getTemplate();
        t.delete(bean);
    }
    
    /**
     * 
     */
    @SuppressWarnings("unchecked")
    public <T> T findById(Class<T> clazz, String idFieldName, int id){
        String className = clazz.getSimpleName();
        Session session = super.getCurrentSession();
        String hql = "from " + className + " o where o." + idFieldName + "=" + id;
        Query query = session.createQuery(hql);
        return (T) query.uniqueResult();
    }
    
    /**
     * 查询指定表共多少条记录, 用于分页
     * @param clazz
     * @return
     */
    public <T> int count(Class<T> clazz){
        String className = clazz.getSimpleName();
        String hql = "select count(o) from " + className + " o ";
        Session session = getCurrentSession();
        Query query = session.createQuery(hql);
        Long uniqueResult = (Long) query.uniqueResult();
        return uniqueResult.intValue();
    }
    
    /**
     * 分页查找
     * @param clazz 哪一个类
     * @param offset 起始记录 从0开始
     * @param max 最大记录
     * @param orderBy 以哪一列排序
     * @param isDesc 是否降序
     * @param selectCols 指定返回哪些列 默认返回所有
     * @return 
     */
    public <T> List<T> find(Class<T> clazz, int offset, int max, String where, String orderBy, boolean isDesc, String... selectCols){
        String className = clazz.getSimpleName();
//        String hql = " from AdminLogBean o order by o.id desc ";
        String hql = "";
        if(selectCols.length > 0){
            hql += "select new " + className + "(";
            boolean flag = false;
            for(String col : selectCols){
                if(flag){
                    hql += ",";
                }
                hql += " o." + col;
                flag = true;
            }
            hql += ")";
        }
        hql += " from " + className + " o ";
        if(where != null){
            hql += " where " + where;
        }
        if(orderBy != null){
            hql += " order by o." + orderBy;
        }
        if(isDesc){// 降序
            hql += " desc ";
        }
        Session session = getCurrentSession();
        Query query = session.createQuery(hql);
        query.setFirstResult(offset);
        query.setMaxResults(max);
        @SuppressWarnings("unchecked")
        List<T> list = query.list();
        return list;
    }
}
