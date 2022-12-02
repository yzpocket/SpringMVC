package com.common.interceptor;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.user.model.UserVO;

import lombok.extern.log4j.Log4j;
@Log4j
public class AdminCheckInterceptor extends HandlerInterceptorAdapter {
	//[1] 컨트롤러를 실행하기 전에 호출되는 메서드
	//작성 후 servlet-context interceptor부분에 추가해야 됨. 
	//			<beans:bean class="com.common.interceptor.AdminCheckInterceptor"/>

	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) throws Exception{
		log.info("Admin Check preHandle()");

		HttpSession ses=req.getSession();
		UserVO user=(UserVO)ses.getAttribute("loginUser");
		if(user!=null) {
			if(user.getStatus()!=9) {
				req.setAttribute("message", "관리자만 이용 가능합니다.");
				req.setAttribute("loc", req.getContextPath()+"/index");
				
				String view="/WEB-INF/views/msg.jsp";
				RequestDispatcher disp=req.getRequestDispatcher(view);
				disp.forward(req, res);
				return false; // 관리자가 아닌경우
			}else {
				return true; // 관리자인경우 9
			}
		}
		return false;// 로그인 안한경우
	}
}