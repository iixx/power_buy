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
import com.langton.power.sys.service.LoginService;
import com.opensymphony.xwork2.ActionContext;

@Controller @Scope("prototype")
public class LoginAction {
    private HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);
    HttpSession session = request.getSession();
    
    @Resource
    private LoginService loginService;
    
    private String adminName;
    private String password;
    
    
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
    
    public void login(){
        String adminId = loginService.getAdminByPwd(adminName, password);
        if(adminId != null){ //登陆通过
            JSONObject json = new JSONObject();
            json.put("login_result", "0");
            json.put("login_description", "登陆成功");
            json.put("admin_id", adminId);
            json.put("admin_name", adminName);
            writeResponse(json.toString());
        }else{
            JSONObject json = new JSONObject();
            json.put("login_result", "-1");
            json.put("login_description", "登陆失败,用户名或者密码错误");
            json.put("admin_name", adminName);
            writeResponse(json.toString());
        }
    }
    
    /**
     * 登陆
     */
    public void login_bak(){
        if(session.getAttribute("adminName") == null){// 未登陆
            String adminName = (String) request.getParameter("adminName");
            String password = (String) request.getParameter("password");
            if("admin".equals(adminName) && "123".equals(password)){
                JSONObject json = new JSONObject();
                json.put("login_result", "0");
                json.put("login_description", "登陆成功");
//                String ticket = coreService.genTicket();
//                json.put("ticket", ticket);
                session.setAttribute("adminName", adminName);
//                session.setAttribute("ticket", ticket);
                writeResponse(json.toString());
            }else{
                JSONObject json = new JSONObject();
                json.put("login_result", "-1");
                json.put("login_description", "登陆失败,用户名或者密码错误");
                writeResponse(json.toString());
            }
        }else{// 已登陆
            JSONObject json = new JSONObject();
            json.put("login_result", "1");
            json.put("login_description", "已登陆过");
            json.put("ticket", session.getAttribute("ticket"));
            writeResponse(json.toString());
        }
    }

    /**
     * 注销
     */
    public void logout_bak(){
        if(session.getAttribute("adminName") != null){
            JSONObject json = new JSONObject();
            json.put("logout_result", "0");
            json.put("logout_description", "注销成功");
            session.invalidate();
            writeResponse(json.toString());
        }else{
            JSONObject json = new JSONObject();
            json.put("logout_result", "-1");
            json.put("logout_description", "尚未登陆过");
            writeResponse(json.toString());
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
}
