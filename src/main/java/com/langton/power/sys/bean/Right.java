package com.langton.power.sys.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "right_info")
public class Right {

    private int id;

    private String rightName;
    private String rightPath;
    private String rightGroup;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }

    @Column(length = 30)
    public String getRightName() {
        return rightName;
    }

    @Column(length = 100)
    public String getRightPath() {
        return rightPath;
    }

    @Column(length = 30)
    public String getRightGroup() {
        return rightGroup;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setRightName(String rightName) {
        this.rightName = rightName;
    }

    public void setRightPath(String rightPath) {
        this.rightPath = rightPath;
    }

    public void setRightGroup(String rightGroup) {
        this.rightGroup = rightGroup;
    }

}
