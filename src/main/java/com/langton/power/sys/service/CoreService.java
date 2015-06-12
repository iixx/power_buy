package com.langton.power.sys.service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.langton.power.sys.bean.AdminBean;
import com.langton.power.sys.bean.AdminLogBean;
import com.langton.power.sys.dao.impl.CommonDao;

@Service
public class CoreService {
    
    @Resource
    private CommonDao dao;

    /**
     * 生成ticket
     */
    public String genTicket(){
        String ticket = UUID.randomUUID().toString();
        ticket = ticket.replaceAll("-", "");
        return ticket;
    }

    /**
     * 新增admin
     * @param adminName
     * @param password
     */
    @Transactional
    public void addNewAdmin(String adminName, String password) {
        AdminBean bean = new AdminBean();
        bean.setAdminName(adminName);
        bean.setPassword(password);
        bean.setType(AdminBean.Type.NORMAL);
        dao.save(bean);
        
    }
    
    /**
     * 删除管理员
     * @param adminId
     */
    @Transactional
    public void deleteAdmin(int adminId){
        AdminBean bean = new AdminBean();
        bean.setId(adminId);
        dao.delete(bean);
    }
    
    /**
     * 添加管理员相关日志
     */
    public void addAdminLog(String adminId, String adminName, String operateType, String operateDetails){
        AdminLogBean bean = new AdminLogBean();
        Date date = new Date();
        bean.setOperateDate(date);
        bean.setOperateTime(date);
        bean.setOperatorId(adminId);
        bean.setOperatorName(adminName);
        bean.setOperateType(operateType);
        bean.setOperateDetails(operateDetails);
        dao.save(bean);
    }
    
    /**
     * 
     * @param offset 起始值为0
     * @param max 最大数量
     * @param orderBy 按哪个字段排序
     * @param isDesc 是否降序
     * @return 
     */
    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public List<AdminLogBean> listAdminLog(int offset, int max, String orderBy, boolean isDesc){
        List<AdminLogBean> list = dao.find(AdminLogBean.class, offset, max, null, orderBy, isDesc);
        return list;
    }
    
    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public int countAdminLog(){
        int count = dao.count(AdminLogBean.class);
        return count;
    }

    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public List<AdminBean> listAdmin(int offset, int max) {
        List<AdminBean> list = dao.find(AdminBean.class, offset, max,
                "type = '" + AdminBean.Type.NORMAL + "'", "id", false, "id",
                "adminName", "type");
        return list;
    }
    
    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public int countAdmin(){
        int count = dao.count(AdminBean.class);
        return count;
    }

    
}
