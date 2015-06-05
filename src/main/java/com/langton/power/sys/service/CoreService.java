package com.langton.power.sys.service;

import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.langton.power.sys.bean.AdminBean;
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

    public void addNewAdmin(String adminName, String password) {
        AdminBean bean = new AdminBean();
        bean.setAdminName(adminName);
        bean.setPassword(password);
        bean.setType(AdminBean.Type.NORMAL);
        dao.save(bean);
        
    }
    
}
