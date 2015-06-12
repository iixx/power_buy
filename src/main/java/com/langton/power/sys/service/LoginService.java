package com.langton.power.sys.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.langton.power.sys.dao.impl.LoginDao;

@Service
public class LoginService {
    
    @Resource
    LoginDao dao;

    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public String getAdminByPwd(String adminName, String password) {
        String result = dao.find(adminName, password);
        return result;
    }
}
