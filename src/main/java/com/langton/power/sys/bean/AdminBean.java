package com.langton.power.sys.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 管理员信息
 * 
 */
@Entity @Table(name="admin_info")
public class AdminBean {

    private int id;
    private String adminName;
    private String password;
    private String type;

    public static class Type {
        public static final String SUPER = "超级管理员";
        public static final String NORMAL = "普通管理员";
    }

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    public int getId() {
        return id;
    }

    @Column(length=30)
    public String getAdminName() {
        return adminName;
    }

    @Column(length=30)
    public String getPassword() {
        return password;
    }

    @Column(length=30)
    public String getType() {
        return type;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setType(String type) {
        this.type = type;
    }

    
}
