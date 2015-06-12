package com.langton.power.sys.web.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.StrutsStatics;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.langton.power.sys.service.AdminService;
import com.langton.power.sys.service.CoreService;
import com.opensymphony.xwork2.ActionContext;

@Controller @Scope("prototype")
public class CoreAction {
    HttpServletResponse response = (HttpServletResponse)ActionContext.getContext().get(StrutsStatics.HTTP_RESPONSE);
    
    @Resource
    private CoreService coreService;
    @Resource
    private AdminService adminService;
    
    private int adminId;
    
    /**
     * 主界面左侧导航栏
     */
    public void naviTree(){
        String result = adminService.naviTree(adminId);
//        String result = "[{\"groupId\":\"1\",\"groupName\":\"支付终端管理\",\"groupChilds\":[{\"name\":\"已有终端管理\",\"path\":\"/sys/currentTerminalList.action\"},{\"name\":\"添加新的终端\",\"path\":\"/sys/addTerminal.action\"}]},{\"groupId\":2,\"groupName\":\"统计报表\",\"groupChilds\":[{\"name\":\"终端状态报表\",\"path\":\"/sys/terminalsStatusReport.action\"},{\"name\":\"购电交易报表\",\"path\":\"/sys/powerTradeReport.action\"}]},{\"groupId\":3,\"groupName\":\"日志管理\",\"groupChilds\":[{\"name\":\"普通用户操作日志\",\"path\":\"/sys/userOperateLog.action\"},{\"name\":\"管理员操作日志\",\"path\":\"/sys/adminOperateLog.action\"}]},{\"groupId\":4,\"groupName\":\"超级管理员\",\"groupChilds\":[{\"name\":\"当前管理员列表\",\"path\":\"/sys/currentAdminList.action\"},{\"name\":\"添加新的管理员\",\"path\":\"/sys/addAdmin.action\"}]}]";
        writeResponse(result);
    }
    
    private void writeResponse(String result){
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

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }
}
