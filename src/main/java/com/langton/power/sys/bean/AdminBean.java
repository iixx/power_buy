package com.langton.power.sys.bean;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * 管理员信息
 * 
 */
@Entity
@Table(name = "admin")
public class AdminBean {

    private int id;
    private String adminName;
    private String password;
    private String type;
    
    private List<Right> rights;
    
    public AdminBean(){}
    public AdminBean(int id, String adminName, String type){
        this.id = id;
        this.adminName = adminName;
        this.type = type;
    }


    public static class Type {
        public static final String SUPER = "superadmin";
        public static final String NORMAL = "normaladmin";
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }

    @Column(length = 30)
    public String getAdminName() {
        return adminName;
    }

    @Column(length = 30)
    public String getPassword() {
        return password;
    }

    @Column(length = 30)
    public String getType() {
        return type;
    }
    
    @ManyToMany @JoinTable(name="admin_right")
    public List<Right> getRights() {
        return rights;
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
    
    public void setRights(List<Right> rights) {
        this.rights = rights;
    }

}
