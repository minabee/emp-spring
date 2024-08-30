package com.example.adminexam.interceptor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/*
 * Spring boot Interceptor란?
 */
@Component
public class Interceptor implements HandlerInterceptor{

    @Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    	System.out.println("============ 인터셉터 요청 ============");
    	/** 인터셉터에서 요청올 때 세션 검사 로직*/
    	HttpSession session = request.getSession(); //세션 불러오기
    	String userName = String.valueOf(session.getAttribute("username")); // session 속 username 속성값 반환
    	if(userName.equals("null")) {
    		response.sendRedirect(request.getContextPath()+"/login"); //로그인창으로 강제 이동(Redirect)
    	}
        return true;
    }
    @Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
  
    }
}
