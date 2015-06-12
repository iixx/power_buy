package com.langton.power.sys.web.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.langton.power.sys.bean.AdminBean;
import com.langton.power.sys.bean.Right;
import com.langton.power.sys.service.AdminService;
import com.langton.power.sys.service.CoreService;
import com.opensymphony.xwork2.ActionContext;

/**
 * 超级管理员功能
 *
 */
@Controller @Scope("prototype")
public class SuAdminAction {

    @Resource
    private CoreService coreService;
    @Resource
    private AdminService AdminService;
    
    private String adminName;
    private String password;
    
    private int pageNum;
    private int pageSize;
    
    private int adminId;
    private String rightIds;// 由英文逗号分隔的数字 例如  3   4,5  1,3,2,4
    
    /**
     * 列出所有管理员
     */
    public void listAdmin(){
        if(pageNum <= 0){ //pageNum从1开始
            pageNum = 1;
        }
        if(pageSize <= 0){
            pageSize = 15;
        }
        List<AdminBean> list = coreService.listAdmin(pageNum - 1, pageSize);
        String jsonString = JSON.toJSONString(list);
        writeResponse(jsonString);
    }
    
    /**
     * 添加新的管理员
     */
    public void addNewAdmin(){
        JSONObject json = new JSONObject(true);
        if (adminName == null || password == null
                || "".equals(adminName.trim()) || "".equals(password.trim())) {
            json.put("operate_result", false);
            json.put("result_description", "名称或密码不能为空值");
            writeResponse(json.toString());
            return;
        }else{
            try {
                coreService.addNewAdmin(adminName, password);
                json.put("operate_result", true);
                json.put("result_description", "新增管理员成功");
                writeResponse(json.toString());
                return;
            } catch (Exception e) {
                e.printStackTrace();
                json.put("operate_result", false);
                json.put("result_description", "系统异常, 新增管理员失败, 请重试");
                writeResponse(json.toString());
                return;
            }
            
        }
    }
    
    /**
     * 删除指定管理员
     */
    public void deleteAdmin() {
        JSONObject json = new JSONObject(true);
        if (adminId == 0) {
            json.put("delete_admin_result", "failed");
            json.put("description", "没有指定管理员ID");
        } else {
            coreService.deleteAdmin(adminId);
            json.put("delete_admin_result", "success");
        }
        writeResponse(json.toString());
    }
    
    /**
     * 列出所有权限
     */
    public void listRight(){
        List<Right> rights = AdminService.listRights();
        String jsonString = JSON.toJSONString(rights);
        writeResponse(jsonString);
    }
    
    /**
     * 列出指定管理员的权限
     */
    public void loadRight(){
        if(adminId != 0){
            List<Right> rights = AdminService.loadRight(adminId);
            JSONArray jsonArray = JSON.parseArray(JSON.toJSONString(rights));
            JSONObject json = new JSONObject();
            json.put("adminId", adminId);
            json.put("rights", jsonArray);
            writeResponse(json.toString());
        }
    }
    
    /**
     * 修改管理员权限
     */
    public void changeRight(){
        JSONObject json = new JSONObject();
        if(adminId != 0){
            if(rightIds != null){
                String[] ids = rightIds.split(",");
                AdminService.changeAdminRights(adminId, ids);
            }else{
                AdminService.changeAdminRights(adminId);
            }
            json.put("change_right_result", "success");
        }else{
            json.put("change_right_result", "failed");
            json.put("description", "未指定adminId");
        }
        writeResponse(json.toString());
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


    public String getAdminName() {
        return adminName;
    }

    public String getPassword() {
        return password;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPageNum() {
        return pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public String getRightIds() {
        return rightIds;
    }

    public void setRightIds(String rightIds) {
        this.rightIds = rightIds;
    }
}
