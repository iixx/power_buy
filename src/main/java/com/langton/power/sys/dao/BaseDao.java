package com.langton.power.sys.dao;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateTemplate;

public abstract class BaseDao {

    @Resource
    protected SessionFactory sessionFactory;
    private HibernateTemplate template;

    protected final Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    protected final HibernateTemplate getTemplate() {
        if (template == null) {
            template = new HibernateTemplate(sessionFactory);
        }
        return template;
    }
}
