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

@Entity
@Table(name = "log_admin_records")
public class AdminLogBean {

    private int id;

    private String operatorId; //操作者ID
    private String operatorName; //操作者名称
    private Date operateTime; //操作时间
    private Date operateDate; //操作日期
    private String operateType; //操作类型 如 登陆/注销/新增管理员/管理支付终端
    private String operateDetails; //操作详情

    public static final class OperateType {
        public static final String LOGIN = "登陆";
        public static final String LOGOUT = "注销";
        public static final String TEMINAL_ENABLE = "终端开通";
        public static final String TEMINAL_DISABLE = "终端停用";
        public static final String VIEW_LOG_ADMIN = "管理员日志查看";
        public static final String VIEW_LOG_USER = "用户日志查看";
        public static final String VIEW_REPORT_TEMINAL_STATUS = "终端状态报表查看";
        public static final String VIEW_REPORT_TRADES = "购电交易报表查看";
        public static final String SUPER_ADD_ADMIN = "新增管理员";
        public static final String SUPER_DELETE_ADMIN = "删除管理员";
        public static final String SUPER_MODIFY_AUTH = "修改管理员权限";
        //...
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }

    @Column(length = 10)
    public String getOperatorId() {
        return operatorId;
    }

    @Column(length = 30)
    public String getOperatorName() {
        return operatorName;
    }

    @Column(length = 50)
    public String getOperateType() {
        return operateType;
    }

    @Column
    public String getOperateDetails() {
        return operateDetails;
    }

    @Column
    @Temporal(TemporalType.DATE)
    public Date getOperateDate() {
        return operateDate;
    }

    @Column
    @Temporal(TemporalType.TIME)
    public Date getOperateTime() {
        return operateTime;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setOperateTime(Date operateTime) {
        this.operateTime = operateTime;
    }

    public void setOperateDate(Date operateDate) {
        this.operateDate = operateDate;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    public void setOperateDetails(String operateDetails) {
        this.operateDetails = operateDetails;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }

}
