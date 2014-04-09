package com.namoo.club.web.controller.shared;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class DefaultController extends HttpServlet {

	private static final long serialVersionUID = 5419831456544653632L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//
		if (this.getClass().getAnnotation(LoginRequired.class) != null) {
			//
			if (req.getSession().getAttribute("loginUser") == null) {
				redirect(req, resp, "/user/main.xhtml");
				return;
			}
		}
		process(req, resp);
	}

	protected abstract void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;
	
	protected void redirect(HttpServletRequest req, HttpServletResponse resp, String url)  throws ServletException, IOException {
		//
		resp.sendRedirect(req.getContextPath() + url);
	}

}
