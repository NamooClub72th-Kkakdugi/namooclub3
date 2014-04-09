package com.namoo.club.web.controller.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.namoo.club.service.facade.UserService;
import com.namoo.club.service.factory.NamooClubServiceFactory;
import com.namoo.club.web.controller.shared.DefaultController;

@WebServlet("/user/join.do")
public class joinController extends DefaultController{

	private static final long serialVersionUID = 351807380794036703L;

	@Override
	protected void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		
		UserService service =  NamooClubServiceFactory.getInstance().getTownerService();
		service.registUser(name, email, password);
		
		redirect(req, resp, "/user/main.xhtml");
		
	}

	

}
