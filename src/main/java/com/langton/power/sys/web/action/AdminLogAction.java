package com.langton.power.sys.web.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.langton.power.sys.bean.AdminLogBean;
import com.langton.power.sys.service.CoreService;
import com.opensymphony.xwork2.ActionContext;

@Controller @Scope("prototype")
public class AdminLogAction {

    @Resource
    private CoreService coreService;
    
    private String adminId;
    private String adminName;
    private String operateType;
    private String details;

    private int pageNum; //第几页
    private int pageSize; //每页数量
    private String orderBy; //排序
    private String isDesc; //是否降序
    
    /**
     * 记录admin操作日志
     */
    public void addAdminLog(){
        JSONObject json = new JSONObject();
        if(adminId==null || adminName == null || operateType == null){
            json.put("admin_log_result", "failed");
            json.put("admin_log_description", "不能有空值");
        }else{
            coreService.addAdminLog(adminId, adminName, operateType, details);
            json.put("admin_log_result", "success");
        }
        writeResponse(json.toString());
    }
    
    /**
     * 返回admin日志总条数, 用于分页
     */
    public void amountAdminLog(){
        int count = coreService.countAdminLog();
        JSONObject json = new JSONObject();
        json.put("amount_admin_log", count);
        writeResponse(json.toString());
        
    }
    
    /**
     * 列出admin操作日志
     */
    public void listAdminLog(){
        if(pageNum <= 0){ //pageNum从1开始
            pageNum = 1;
        }
        if(pageSize <= 0){
            pageSize = 15;
        }
        if(orderBy == null){
            orderBy = "id";
        }
        boolean flag = false;
        if("true".equalsIgnoreCase(isDesc)){
            flag = true;
        }
        List<AdminLogBean> list = coreService.listAdminLog(pageNum - 1, pageSize, orderBy, flag);
        String jsonString = JSON.toJSONString(list);
        writeResponse(jsonString);
    }
    
    
    private void writeResponse(String result){
        HttpServletResponse response = (HttpServletResponse)ActionContext.getContext().get(org.apache.struts2.StrutsStatics.HTTP_RESPONSE);
        response.setContentType("text/html;charset=UTF-8");
        try {
            PrintWriter writer = response.getWriter();
            writer.write(result);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getAdminId() {
        return adminId;
    }

    public String getAdminName() {
        return adminName;
    }

    public String getOperateType() {
        return operateType;
    }

    public String getDetails() {
        return details;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public String getIsDesc() {
        return isDesc;
    }

    public void setIsDesc(String isDesc) {
        this.isDesc = isDesc;
    }
}
