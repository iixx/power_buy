package com.langton.power.sys.web.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSONObject;
import com.langton.power.sys.service.CoreService;
import com.opensymphony.xwork2.ActionContext;

@Controller @Scope("prototype")
public class CoreAction {
    private HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);
    HttpSession session = request.getSession();
    
    @Resource
    private CoreService coreService;
    
    public void writeResponse(String result){
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
    
    /**
     * 主界面左侧导航栏
     */
    public void naviTree(){
//        JSONObject json = new JSONObject();
        String result = "[{\"groupId\":\"1\",\"groupName\":\"支付终端管理\",\"groupChilds\":[{\"name\":\"已有终端管理\",\"path\":\"/sys/currentTerminalList.action\"},{\"name\":\"添加新的终端\",\"path\":\"/sys/addTerminal.action\"}]},{\"groupId\":2,\"groupName\":\"统计报表\",\"groupChilds\":[{\"name\":\"终端状态报表\",\"path\":\"/sys/terminalsStatusReport.action\"},{\"name\":\"购电交易报表\",\"path\":\"/sys/powerTradeReport.action\"}]},{\"groupId\":3,\"groupName\":\"日志管理\",\"groupChilds\":[{\"name\":\"普通用户操作日志\",\"path\":\"/sys/userOperateLog.action\"},{\"name\":\"管理员操作日志\",\"path\":\"/sys/adminOperateLog.action\"}]},{\"groupId\":4,\"groupName\":\"超级管理员\",\"groupChilds\":[{\"name\":\"当前管理员列表\",\"path\":\"/sys/currentAdminList.action\"},{\"name\":\"添加新的管理员\",\"path\":\"/sys/addAdmin.action\"}]}]";
        writeResponse(result);
    }
    
    /**
     * 添加新的管理员
     */
    public void addNewAdmin(){
        String adminName = request.getParameter("adminName");
        String password = request.getParameter("password");
        JSONObject json = new JSONObject();
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

}
