package com.langton.power.sys.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginFilter implements Filter {
	
	public void doFilter(ServletRequest req, ServletResponse rsp,
			FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) rsp;
		HttpSession session = request.getSession();

		if(null == session.getAttribute("adminName") 
		        && ! request.getRequestURI().endsWith("/sys/login.jsp")
		        && ! request.getRequestURI().endsWith("/sys/login.action")){
			response.sendRedirect(request.getContextPath() + "/sys/login.jsp");
		} else {
		    filterChain.doFilter(req, rsp);
		}
	}

    @Override
    public void destroy() {
        
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
        
    }
}