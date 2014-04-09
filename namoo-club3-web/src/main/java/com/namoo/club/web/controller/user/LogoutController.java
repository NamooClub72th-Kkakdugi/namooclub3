package com.namoo.club.web.controller.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.namoo.ns1.web.controller.shared.DefaultController;

@WebServlet("/user/logout.do")
public class LogoutController extends DefaultController {

	private static final long serialVersionUID = -3145502510607459335L;

	@Override
	protected void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//
		req.getSession().removeAttribute("loginUser");

		redirect(req, resp, "/user/main.xhtml");
	}


}
