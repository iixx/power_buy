package com.langton.power.sys.dao;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;

public abstract class BaseDao {

    @Resource
    protected SessionFactory sessionFactory;
    private HibernateTemplate template;

    protected final SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    protected final HibernateTemplate getTemplate() {
        if (template == null) {
            template = new HibernateTemplate(getSessionFactory());
        }
        return template;
    }
}
