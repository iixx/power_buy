package com.langton.power.sys.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity @Table(name="teminal")
public class TeminalBean {

    private int id;
    private String teminalSerialNumber; //终端序列号
    private String status; //终端当前状态
    private String teminalVersion; //终端版本
    private String softwareVersion; //终端软件版本

    public static final class Status {
        public static final String IMPORTED = "入库";
        public static final String ENABLED = "开通";
        public static final String DISABLED = "停用";
    }

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    public int getId() {
        return id;
    }

    @Column(length=36)
    public String getTeminalSerialNumber() {
        return teminalSerialNumber;
    }

    @Column(length=10)
    public String getStatus() {
        return status;
    }

    @Column(length=20)
    public String getTeminalVersion() {
        return teminalVersion;
    }

    @Column(length=20)
    public String getSoftwareVersion() {
        return softwareVersion;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTeminalSerialNumber(String teminalSerialNumber) {
        this.teminalSerialNumber = teminalSerialNumber;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setTeminalVersion(String teminalVersion) {
        this.teminalVersion = teminalVersion;
    }

    public void setSoftwareVersion(String softwareVersion) {
        this.softwareVersion = softwareVersion;
    }
    
    

}
