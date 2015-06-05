package com.langton.power.sys.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity @Table(name="log_admin")
public class AdminLogBean {
    
    private int adminLogId;
    
    private String operator;            //操作者
    private Date operateTime;           //操作时间
    private String operateType;         //操作类型 如 登陆/注销/新增管理员/管理支付终端
    private String operateDetails;      //操作详情
    
    public static final class OperateType{
        public static final String LOGIN = "登陆";
        public static final String LOGOUT = "注销";
        public static final String TEMINAL_ENABLE = "终端开通";
        public static final String TEMINAL_DISABLE = "终端停用";
        
        
    }
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    public int getAdminLogId() {
        return adminLogId;
    }
    public void setAdminLogId(int adminLogId) {
        this.adminLogId = adminLogId;
    }
    
    @Column
    @Temporal(TemporalType.TIME)
    public Date getOperateTime() {
        return operateTime;
    }
    public void setOperateTime(Date operateTime) {
        this.operateTime = operateTime;
    }
    
    @Column(length=30)
    public String getOperator() {
        return operator;
    }
    public void setOperator(String operateUser) {
        this.operator = operateUser;
    }
    
    @Column(length=50)
    public String getOperateType() {
        return operateType;
    }
    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }
    
    @Column
    public String getOperateDetails() {
        return operateDetails;
    }
    public void setOperateDetails(String operateDetails) {
        this.operateDetails = operateDetails;
    }
    
}
