package com.langton.power.sys.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.langton.power.sys.bean.AdminBean;
import com.langton.power.sys.bean.Right;
import com.langton.power.sys.dao.BaseDao;

@Repository
public class AdminRightDao extends BaseDao {

    /**
     * 修改管理员的权限 
     * @param adminId
     * @param ids
     */
    public void changeAdminRight(int adminId, String... ids) {
        Session session = super.getCurrentSession();
        AdminBean admin = (AdminBean) session.get(AdminBean.class, adminId);
        List<Right> rights = new ArrayList<Right>();
        for (String id : ids) {
            Right right = new Right();
            right.setId(Integer.parseInt(id));
            rights.add(right);
        }
        admin.setRights(rights);
    }

    /**
     * 列出所有权限
     * @return 
     */
    public List<Right> listRights() {
        Query query = getCurrentSession().createQuery("from Right");
        @SuppressWarnings("unchecked")
        List<Right> list = query.list();
        return list;
    }

    /**
     * 查看某个管理员的权限
     * @param adminId
     * @return
     */
    public List<Right> loadRight(int adminId) {
        AdminBean adminBean = getTemplate().load(AdminBean.class, adminId);
        List<Right> rights = adminBean.getRights();
        return rights;
    }

}
